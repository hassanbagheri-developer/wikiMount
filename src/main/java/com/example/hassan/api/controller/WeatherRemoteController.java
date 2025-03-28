package com.example.hassan.api.controller;

import com.example.hassan.api.dto.*;
import com.example.hassan.api.facade.WeatherMapFacade;
import com.example.hassan.service.WeatherService;
import com.example.hassan.service.remote.UserService1;
import com.example.hassan.service.remote.UserService2;
import com.example.hassan.service.remote.WeatherMapRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/public/weather")
public class WeatherRemoteController {

    private final WeatherMapFacade facade;

    public WeatherRemoteController(WeatherMapFacade facade) {
        this.facade = facade;
    }


    @GetMapping("/getWeatherForecast")
    public List<WeatherOutPutDto> getWeatherForecast(WeatherOpenMapInputDto inputDto) {
        return facade.getWeatherForecast(inputDto);
    }

    @GetMapping("/getWeatherForecast/{locationName}/{height}")
    public List<WeatherOutPutDto> getWeatherForecastByLocationName(WeatherOpenMapInputDto inputDto) {
        return facade.getWeatherForecastByLocationName(inputDto);
    }


}
