package com.example.hassan.service;

import com.example.hassan.api.dto.WeatherOpenMapInputDto;
import com.example.hassan.api.dto.WeatherOutPutDto;
import com.example.hassan.api.dto.WeatherResponse;
import com.example.hassan.dal.entity.WeatherEntity;
import com.example.hassan.dal.rpository.WeatherMapRepository;
import com.example.hassan.service.remote.WeatherMapRemoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherMapRemoteService weatherMapRemoteService;
    private final WeatherMapRepository weatherMapRepository;

    public WeatherService(WeatherMapRemoteService weatherMapRemoteService, WeatherMapRepository weatherMapRepository) {
        this.weatherMapRemoteService = weatherMapRemoteService;
        this.weatherMapRepository = weatherMapRepository;
    }

    public List<WeatherEntity> getWeatherForecast(WeatherOpenMapInputDto input) {
        WeatherResponse response = weatherMapRemoteService.getWeatherForecast(input);
        String cityName = response.getCity().getName();

        List<WeatherEntity> WeatherEntity = response.getList().stream()
                .map(weatherData -> mapToEntity(input.getLat(),
                        input.getLon(), cityName, weatherData, input.getLocationName(), input.getHeight()))
                .toList();


        return weatherMapRepository.saveAll(WeatherEntity);


    }

    private WeatherEntity mapToEntity(Double lat, Double lon, String cityName, WeatherResponse.WeatherData weatherData, String locationName, String height) {
        return WeatherEntity.builder()
                .lat(lat).lon(lon).cityName(cityName).dateTime(weatherData.getDt_txt())
                .temperature(weatherData.getMain().getTemp()).tempMax(weatherData.getMain().getTemp_max())
                .tempMin(weatherData.getMain().getTemp_min()).feelsLike(weatherData.getMain().getFeels_like())
                .windSpeed(weatherData.getWind().getSpeed()).weatherMain(weatherData.getWeather().getFirst().getMain())
                .weatherDescription(weatherData.getWeather().getFirst().getDescription())
                .height(height).locationName(locationName)
                .build();
    }

    public List<WeatherEntity> getWeatherForecastByLocationName(WeatherOpenMapInputDto inputDto) {
        return weatherMapRepository.findByLocationNameAndHeight(inputDto.getLocationName(), inputDto.getHeight());
    }
}

