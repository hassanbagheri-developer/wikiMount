package com.example.hassan.api.controller;

import com.example.hassan.api.dto.UserRequest;
import com.example.hassan.api.dto.UserResponse;
import com.example.hassan.service.remote.UserService1;
import com.example.hassan.service.remote.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/TestRemoteController")
public class TestRemoteController {

    private final UserService1 userService1;
    private final UserService2 userService2;

    @Autowired
    public TestRemoteController(UserService1 userService1, UserService2 userService2) {
        this.userService1 = userService1;
        this.userService2 = userService2;
    }

    @PostMapping("/createUser2")
    public Mono<UserResponse> createUser2(@RequestBody UserRequest userRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return userService2.createUser(userRequest,httpHeaders);
    }

    @PostMapping("/createUser1")
    public UserResponse createUser1(@RequestBody UserRequest userRequest) {
        return userService1.createUser(userRequest);
    }
}
