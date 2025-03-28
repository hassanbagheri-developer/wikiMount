package com.example.hassan.api.controller;

import com.example.hassan.api.dto.StudentOutputDto;
import com.example.hassan.dal.modle.StudentModel;
import com.example.hassan.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@Slf4j
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyAuthority('api.students.createStudent') ")
    public ResponseEntity<StudentOutputDto> createStudent(@RequestBody StudentModel studentModel) {
        StudentOutputDto studentOutputDto = studentService.createStudent(studentModel);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyAuthority('api.students.getAllStudents') ")
    public ResponseEntity<Page<StudentOutputDto>> getAllStudents(Pageable pageable) {
        Page<StudentOutputDto> students = studentService.getAllStudents(pageable);
        log.info("*********** test log ********");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('getAllStudents.{id}.getStudentById') ")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        StudentOutputDto studentOutputDto = studentService.getStudentById(id);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('api.students.{id}.updateStudent') ")
    public ResponseEntity<StudentOutputDto> updateStudent(@PathVariable int id, @RequestBody StudentModel studentModel) {
        StudentOutputDto studentOutputDto = studentService.updateStudent(id, studentModel);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('api.students.{id}.deleteStudent') ")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/filter")
    @PreAuthorize(value = "hasAnyAuthority('api.students.filter.filterStudents') ")
    public Page<StudentOutputDto> filterStudents(@Valid @RequestBody StudentModel filterDto,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return studentService.filterStudents(filterDto, page, size);
    }
}
