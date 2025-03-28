package com.example.hassan.dal.modle;

import com.example.hassan.config.jwt.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserAplicationModel {

    private int id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Boolean enabled = true;

}
