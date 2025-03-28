package com.example.hassan.api.dto;

import com.example.hassan.dal.enums.DurationDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class WeatherOutPutDto {

    private double lat;
    private double lon;
    private String cityName;
    private double temperature;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private String weatherDescription;
    private String weatherMain;
    private double windSpeed;
    private String dateTime;
    private DurationDay durationDay;
    private String locationName;
    private String height;


}