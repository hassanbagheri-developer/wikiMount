package com.example.hassan.api.facade;

import com.example.hassan.api.dto.WeatherOpenMapInputDto;
import com.example.hassan.api.dto.WeatherOutPutDto;
import com.example.hassan.api.manager.mapper.WeatherServiceMapper;
import com.example.hassan.dal.entity.WeatherEntity;
import com.example.hassan.service.WeatherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherMapFacade {

    private final WeatherService weatherService;
    private final WeatherServiceMapper mapper;

    public WeatherMapFacade(WeatherService weatherService, WeatherServiceMapper mapper) {
        this.weatherService = weatherService;
        this.mapper = mapper;
    }

    public List<WeatherOutPutDto> getWeatherForecast(WeatherOpenMapInputDto inputDto) {
        List<WeatherEntity> weatherForecast = weatherService.getWeatherForecast(inputDto);
        return weatherForecast.stream().map(mapper::getWeatherOutPutDtoFromEntity).toList();
    }

    public List<WeatherOutPutDto> getWeatherForecastByLocationName(WeatherOpenMapInputDto inputDto) {
        List<WeatherEntity> weatherEntityList = weatherService.getWeatherForecastByLocationName(inputDto);
        return weatherEntityList.stream().map(mapper::getWeatherOutPutDtoFromEntity).toList();
    }
}
