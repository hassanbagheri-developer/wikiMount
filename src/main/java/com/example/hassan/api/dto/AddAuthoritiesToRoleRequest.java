package com.example.hassan.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AddAuthoritiesToRoleRequest {

    private String roleName;
    private Set<String> authorityNames;

}
