package com.example.hassan.api.controller;

import com.example.hassan.api.dto.UserAplicationOutPutDto;
import com.example.hassan.api.facade.UserFacade;
import com.example.hassan.config.SecurityAgent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/users")
public class UsersController {

    private final UserFacade userFacade;

    public UsersController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UserAplicationOutPutDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/userDetails")
    public String userDetails(Model model) {
        model.addAttribute("user", SecurityAgent.getCurrentUser());
        return "userDetails";
    }
}
