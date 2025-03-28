package com.example.hassan.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentOutputDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime deleteDate;


}
