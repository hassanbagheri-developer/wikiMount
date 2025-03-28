package com.example.hassan.service.remote;

import com.example.hassan.api.dto.UserRequest;
import com.example.hassan.api.dto.UserResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService1 {

    public UserResponse createUser(UserRequest userRequest) {
        String url = "https://reqres.in/api/users";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserRequest> request = new HttpEntity<>(userRequest, headers);

        ResponseEntity<UserResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, UserResponse.class);

        return response.getBody();
    }
}
