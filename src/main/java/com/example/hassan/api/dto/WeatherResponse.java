package com.example.hassan.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class WeatherResponse {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private City city;

    @Data
    @NoArgsConstructor
    public static class WeatherData {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private double pop;
        private Sys sys;
        private String dt_txt;


    }

    @Data
    @NoArgsConstructor
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int sea_level;
        private int grnd_level;
        private int humidity;
        private double temp_kf;


    }

    @Data
    @NoArgsConstructor
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;


    }

    @Data
    @NoArgsConstructor
    public static class Clouds {
        private int all;


    }

    @Data
    @NoArgsConstructor
    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    @NoArgsConstructor
    public static class Sys {
        private String pod;

    }

    @Data
    @NoArgsConstructor
    public static class City {
        private int id;
        private String name;
        private Coord coord;
        private String country;
        private int population;
        private int timezone;
        private long sunrise;
        private long sunset;

    }

    @Data
    @NoArgsConstructor
    public static class Coord {
        private double lat;
        private double lon;


    }
}
