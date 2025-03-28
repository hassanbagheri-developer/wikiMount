package com.example.hassan.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // Set the status code to 403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Set the response content type to JSON
        response.setContentType("application/json");

        // Write the error message
        response.getWriter().write("{\"message\": \"Access Denied\"}");
    }
}
