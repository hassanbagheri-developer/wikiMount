package com.example.hassan.api.dto;

import com.example.hassan.dal.entity.FavoriteActivity;
import com.example.hassan.dal.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserAplicationOutPutDto {

    private int id;
    private String username;
//    private String password;
    private Boolean enabled;
    private String email;
    private String phone;
    private String bio;
    private String firstName;
    private String lastName;
    private String regin;
    private LocalDate birthday;
    private Set<FavoriteActivity> favoriteActivities = new HashSet<>();
    private Set<FavoriteActivity> roles = new HashSet<>();

}
