package com.example.hassan.dal.rpository;

import com.example.hassan.dal.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherMapRepository extends JpaRepository<WeatherEntity,Integer> {

    List<WeatherEntity> findByLocationNameAndHeight(String location, String height);

}
