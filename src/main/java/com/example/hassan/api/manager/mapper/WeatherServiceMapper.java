package com.example.hassan.api.manager.mapper;


import com.example.hassan.api.dto.WeatherOutPutDto;
import com.example.hassan.dal.entity.WeatherEntity;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface WeatherServiceMapper {


    WeatherOutPutDto getWeatherOutPutDtoFromEntity(WeatherEntity weatherEntity);



}
