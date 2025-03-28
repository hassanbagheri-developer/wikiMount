package com.example.hassan.api.controller;

import com.example.hassan.api.dto.UserAplicationOutPutDto;
import com.example.hassan.api.dto.UserInputDto;
import com.example.hassan.api.facade.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public UserAplicationOutPutDto getUserById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserAplicationOutPutDto> updateUser(@PathVariable Long id, @RequestBody UserInputDto userDetails) {
        return ResponseEntity.ok(userFacade.updateUser(id, userDetails));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
