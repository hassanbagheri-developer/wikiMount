package com.example.hassan.config;

import com.example.hassan.config.jwt.JwtAuthFilter;
import com.example.hassan.exceptions.CustomAccessDeniedHandler;
import com.example.hassan.service.UserAplicationService;
import io.micrometer.core.ipc.http.HttpSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityBasicConfiguration {

    private final UserAplicationService userAplicationService;
    private final JwtAuthFilter jwtAuthFilter;


//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails users = User.withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(users, admin);
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request->request
//                        .requestMatchers("/login").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/oauth2/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/public/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/public/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/public/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler()))  // Ensure access denied handler is configured
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userAplicationService);
        return provider;
    }
}
