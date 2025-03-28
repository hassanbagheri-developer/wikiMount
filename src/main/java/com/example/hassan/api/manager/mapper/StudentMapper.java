package com.example.hassan.api.manager.mapper;

import com.example.hassan.api.dto.StudentOutputDto;
import com.example.hassan.dal.entity.Student;
import com.example.hassan.dal.modle.StudentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    
    Student toEntity(StudentModel model);
    
    StudentOutputDto toDto(Student student);
    
}
