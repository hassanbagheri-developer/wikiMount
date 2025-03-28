package com.example.hassan.config.jwt;

import com.example.hassan.dal.entity.Token;
import com.example.hassan.dal.rpository.TokenRepository;
import com.example.hassan.service.UserAplicationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserAplicationService userAplicationService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If there's no Authorization header or it doesn't start with Bearer, just continue
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7); // Extract the token

        if (jwtService.isExpire(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
            return;
        }

        String username = jwtService.extractUserName(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            Token token = tokenRepository.findByToken(jwt)
//                    .stream().filter(t -> !t.isExpired() && !t.isRevoked())
//                    .findFirst().orElse(null);
//
//            if (token == null) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
//                return;
//            }

            UserDetails userDetails = userAplicationService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}