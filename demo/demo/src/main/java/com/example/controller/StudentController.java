package com.example.controller;

import com.example.entity.Student;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rahul
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Value("${application.name: Default Demo App}")
    private String applicationName;

    @GetMapping("/get")
    public Student getStudent() {
        //return applicationName;
        return Student.builder()
                .id(1l)
                .firstName("John")
                .lastName("Smith")
                .build();

    }

    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents() {
        var studentList = studentService.getAllStudents();
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }
}
