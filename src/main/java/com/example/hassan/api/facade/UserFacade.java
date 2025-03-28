package com.example.hassan.api.facade;

import com.example.hassan.api.dto.UserAplicationOutPutDto;
import com.example.hassan.api.dto.UserInputDto;
import com.example.hassan.api.manager.mapper.UserApplicationMapper;
import com.example.hassan.config.jwt.entity.User;
import com.example.hassan.service.UserAplicationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    private final UserAplicationService userAplicationService;
    private final UserApplicationMapper mapper;

    public UserFacade(UserAplicationService userAplicationService, UserApplicationMapper mapper) {
        this.userAplicationService = userAplicationService;
        this.mapper = mapper;
    }

    public List<UserAplicationOutPutDto> getAllUsers() {
        List<User> allUsers = userAplicationService.getAllUsers();
        return allUsers.stream().map(mapper::getOutputDtoFromEntity).toList();
    }

    public UserAplicationOutPutDto getUserById(Long id) {
         return mapper.getOutputDtoFromEntity(userAplicationService.getUserById(id));
    }

    public void deleteUser(Long id) {
        userAplicationService.deleteUser(id);
    }


    public UserAplicationOutPutDto updateUser(Long id, UserInputDto userDetails) {
        User newuser =userAplicationService.updateUser(id,userDetails);
        return mapper.getOutputDtoFromEntity(newuser);
    }
}
