package com.example.hassan.api.manager.mapper;

import com.example.hassan.api.dto.StudentOutputDto;
import com.example.hassan.api.dto.UserAplicationOutPutDto;
import com.example.hassan.api.dto.UserApplicationInputDto;
import com.example.hassan.config.jwt.entity.User;
import com.example.hassan.dal.modle.UserAplicationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserApplicationMapper {



    @Mapping(target = "id", ignore = true)
    User getEntityFromModel(UserAplicationModel model);

    UserAplicationModel getModelFromEntity(User entity);

    UserAplicationOutPutDto getOutputDtoFromEntity(User entity);

    StudentOutputDto getOutputDtoFromModel(UserAplicationModel model);

    UserAplicationModel getModelFromInputDto(UserApplicationInputDto inputDto);

}
