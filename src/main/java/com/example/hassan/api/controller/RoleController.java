package com.example.hassan.api.controller;

import com.example.hassan.api.dto.AddAuthoritiesToRoleRequest;
import com.example.hassan.config.jwt.entity.Role;
import com.example.hassan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add-authorities")
    public ResponseEntity<Role> addAuthoritiesToRole(@RequestBody AddAuthoritiesToRoleRequest request) {
        Role updatedRole = roleService.addAuthoritiesToRole(request.getRoleName(), request.getAuthorityNames());
        return ResponseEntity.ok(updatedRole);
    }
}
