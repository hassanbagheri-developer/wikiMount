package com.example.hassan.dal.rpository;

import com.example.hassan.dal.entity.FavoriteActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteActivityRepository extends JpaRepository<FavoriteActivity,Long> {

}
