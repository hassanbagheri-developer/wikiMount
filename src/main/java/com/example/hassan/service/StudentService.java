package com.example.hassan.service;

import com.example.hassan.api.dto.StudentOutputDto;
import com.example.hassan.api.manager.mapper.StudentMapper;
import com.example.hassan.dal.entity.Student;
import com.example.hassan.dal.modle.StudentModel;
import com.example.hassan.dal.rpository.StudentRepository;
import com.example.hassan.dal.rpository.StudentSpecification;
import com.example.hassan.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MessageSource messageSource;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, MessageSource messageSource) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.messageSource = messageSource;
    }

    public StudentOutputDto createStudent(StudentModel studentModel) {
        Student student = studentMapper.toEntity(studentModel);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public Page<StudentOutputDto> getAllStudents(Pageable pageable) {
        return  studentRepository.findAll(pageable).map(studentMapper::toDto);

    }

    public StudentOutputDto getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("student.not.found", messageSource));
        return studentMapper.toDto(student);
    }

    public StudentOutputDto updateStudent(int id, StudentModel studentModel) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("student.not.found", messageSource));


        student.setFirstName(studentModel.getFirstName());
        student.setLastName(studentModel.getLastName());
        student.setEmail(studentModel.getEmail());

        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("student.not.found", messageSource));
        studentRepository.delete(student);
    }

    public Page<StudentOutputDto> filterStudents(StudentModel filterDto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(StudentSpecification.filterByDto(filterDto), pageable).map(studentMapper::toDto);
    }
}
