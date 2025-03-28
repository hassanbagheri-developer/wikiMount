package com.example.hassan.service;

import com.example.hassan.api.dto.UserApplicationInputDto;
import com.example.hassan.api.dto.UserTokenInputDto;
import com.example.hassan.api.dto.UserTokenOutputDto;
import com.example.hassan.api.manager.mapper.UserApplicationMapper;
import com.example.hassan.config.jwt.JwtService;
//import com.example.hassan.config.jwt.TokenService;
import com.example.hassan.config.jwt.entity.Role;
import com.example.hassan.dal.entity.Token;
import com.example.hassan.dal.modle.UserAplicationModel;
import com.example.hassan.dal.rpository.RoleRepository;
import com.example.hassan.dal.rpository.TokenRepository;
import com.example.hassan.dal.rpository.UserApplicationRepository;
import com.example.hassan.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserAplicationService implements UserDetailsService {

    @Value("${jwt.expireDate}")
    private long expireDate;

    private final UserApplicationRepository repository;
    private final RoleRepository roleRepository;
    private final MessageSource messageSource;
    private final JwtService jwtService;
    private final UserApplicationMapper mapper;
    private final TokenRepository tokenRepository;
    private final UserApplicationRepository userApplicationRepository;
//    private final TokenService tokenService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.hassan.config.jwt.entity.User userApplication = repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("user.not.found", messageSource));

        List<SimpleGrantedAuthority> authorities = userApplication.getRoles().stream()
                .flatMap(role -> role.getAuthorities().stream())
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(userApplication.getUsername(), userApplication.getPassword(), authorities);
    }

    public UserTokenOutputDto login(UserTokenInputDto inputDto) {
        UserDetails userDetails = loadUserByUsername(inputDto.getUsername());
        String token = jwtService.generateToken(userDetails);
//        saveTokenToDb(token,userDetails); // save in dataBase
//        saveTokenInRedis(token,userDetails);
        return new UserTokenOutputDto(token);
    }

    public void register(UserApplicationInputDto userApplication) {
        UserAplicationModel model = mapper.getModelFromInputDto(userApplication);
        model.setEnabled(true);
        Set<String> rolles = new HashSet<>();
        rolles.add("ROLE_USER");
        Role  userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("role.not.found",messageSource));
        Set<Role> userRoles = new HashSet<>(List.of(userRole));
        model.setRoles(userRoles);
            repository.save(mapper.getEntityFromModel(model));
    }

    private void saveTokenInDb(String token, UserDetails userDetails) {
        Optional<com.example.hassan.config.jwt.entity.User> userApplication = userApplicationRepository.findByUsername(userDetails.getUsername());
        Token saveToken = Token.builder()
                .token(token)
                .expired(Boolean.FALSE)
                .revoked(Boolean.FALSE)
                .user(userApplication.get())
//                .user((UserApplication) userDetails)
                .build();
        tokenRepository.save(saveToken);
    }

    private void saveTokenInRedis(String token, UserDetails userDetails) {
        Optional<com.example.hassan.config.jwt.entity.User> userApplication = userApplicationRepository.findByUsername(userDetails.getUsername());
        Token saveToken = Token.builder()
                .token(token)
                .expired(Boolean.FALSE)
                .revoked(Boolean.FALSE)
                .user(userApplication.get())
//                .user((UserApplication) userDetails)
                .build();
//        tokenService.saveToken("authToken:" + saveToken, token, expireDate);
    }


    public List<com.example.hassan.config.jwt.entity.User> getAllUsers() {
        return repository.findAll();
    }
}
