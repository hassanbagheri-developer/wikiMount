package com.example.hassan.service.remote;

import com.example.hassan.api.dto.UserRequest;
import com.example.hassan.api.dto.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class UserService2 {

    private final WebClient webClient;

    public UserService2(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://reqres.in/api").build();
    }

    public Mono<UserResponse> createUser(UserRequest userRequest, HttpHeaders headers) {
        return webClient.post()
                .uri("/users")
                .headers(httpHeaders -> httpHeaders.setAll(headers.toSingleValueMap()))
                .body(Mono.just(userRequest), UserRequest.class)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .timeout(Duration.ofMillis(15_000))
                .log();
    }
}
