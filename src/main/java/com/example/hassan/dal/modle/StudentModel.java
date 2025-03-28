package com.example.hassan.dal.modle;

import com.example.hassan.api.manager.validation.ValidDate;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentModel {
    private String firstName;
    private String lastName;
    @Email(message = "{email.not.valid}")
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime deleteDate;

    // filed for filter
    @ValidDate(message = "Invalid format for createDateTo, expected yyyy-MM-dd HH:mm:ss")
    private String createDateFrom;

    @ValidDate(message = "Invalid format for createDateTo, expected yyyy-MM-dd HH:mm:ss")
    private String createDateTo;
}
