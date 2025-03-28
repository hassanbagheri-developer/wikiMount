package com.example.hassan.service.remote;

import com.example.hassan.api.dto.*;
import com.example.hassan.dal.entity.WeatherEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;


@Service
public class WeatherMapRemoteService {

    private final WebClient webClient;

    public WeatherMapRemoteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api.openweathermap.org/data/2.5/forecast").build();
    }

    public WeatherResponse getWeatherForecast(WeatherOpenMapInputDto input) {
        Double lat=input.getLat();
        Double lon=input.getLon();
        WeatherResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", input.getLat())
                        .queryParam("lon", input.getLon())
                        .queryParam("appid", input.getAppid())
                        .queryParam("cnt", input.getCnt())
                        .queryParam("units", input.getUnits())
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();

        if (response == null || response.getList() == null || response.getList().isEmpty()) {
            throw new RuntimeException("Invalid response from weather API");
        }
        return response;



    }

}
