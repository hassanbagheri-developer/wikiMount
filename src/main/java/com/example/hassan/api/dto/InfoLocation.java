package com.example.hassan.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoLocation {
    private double lat;
    private double lon;
    private String locationName;
    private String height;
}
