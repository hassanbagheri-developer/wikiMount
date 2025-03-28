package com.example.hassan.config;

import com.example.hassan.config.jwt.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityAgent {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
