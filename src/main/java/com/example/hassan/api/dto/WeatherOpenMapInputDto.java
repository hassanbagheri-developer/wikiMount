package com.example.hassan.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WeatherOpenMapInputDto {
    private double lat;
    private double lon;
    private String locationName;
    private String height;
    private String appid;
    private int cnt;
    private String units;


}
