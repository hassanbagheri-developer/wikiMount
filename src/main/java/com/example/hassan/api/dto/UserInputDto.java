package com.example.hassan.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
public class UserInputDto  {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String phone;
    private String bio;
    private String firstName;
    private String lastName;
    private String regin;
    private LocalDate birthday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime removedAt;
    private List<Long> favoriteActivityIds ;
    private List<Long> roleIds;

}
