package com.example.hassan.api.controller;

import com.example.hassan.api.dto.StudentOutputDto;
import com.example.hassan.dal.modle.StudentModel;
import com.example.hassan.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public/students")
public class PublicStudentController {

    private final StudentService studentService;

    @Autowired
    public PublicStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentOutputDto> createStudent(@RequestBody @Validated StudentModel studentModel) {
        StudentOutputDto studentOutputDto = studentService.createStudent(studentModel);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<StudentOutputDto>> getAllStudents(Pageable pageable) {
        Page<StudentOutputDto> students = studentService.getAllStudents(pageable);
        log.info("*********** test log ********");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        StudentOutputDto studentOutputDto = studentService.getStudentById(id);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@PathVariable int id, @RequestBody StudentModel studentModel) {
        StudentOutputDto studentOutputDto = studentService.updateStudent(id, studentModel);
        return new ResponseEntity<>(studentOutputDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/filter")
    public Page<StudentOutputDto> filterStudents(@Valid @RequestBody StudentModel filterDto,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return studentService.filterStudents(filterDto, page, size);
    }
}
