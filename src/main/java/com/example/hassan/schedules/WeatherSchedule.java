package com.example.hassan.schedules;

import com.example.hassan.api.dto.InfoLocation;
import com.example.hassan.api.dto.WeatherOpenMapInputDto;
import com.example.hassan.api.facade.WeatherMapFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherSchedule {

    private final WeatherMapFacade facade;


//    @Scheduled
    public void startApplication() {

        List<InfoLocation> locations = List.of(
                new InfoLocation(35.6892, 51.3890, "Tehran", "1200"),
                new InfoLocation(34.0522, -118.2437, "Los Angeles", "305"),
                new InfoLocation(48.8566, 2.3522, "Paris", "35")
        );

        for (InfoLocation location : locations) {
            WeatherOpenMapInputDto inputDto = WeatherOpenMapInputDto.builder()
                    .lat(location.getLat())
                    .lon(location.getLon())
                    .locationName(location.getLocationName())
                    .height(location.getHeight())
                    .build();

            facade.getWeatherForecast(inputDto);
        }


    }
}
