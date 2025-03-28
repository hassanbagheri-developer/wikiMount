package com.example.hassan.dal.entity;

import com.example.hassan.dal.enums.DurationDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Table(name = "weather")
@Data
@AllArgsConstructor
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    public WeatherEntity() {

    }
}