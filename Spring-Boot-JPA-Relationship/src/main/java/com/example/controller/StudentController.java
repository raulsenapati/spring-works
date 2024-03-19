package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rahul
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("getAll")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("getFirstNameById/{id}")
    public String getFirstNameById(@PathVariable Long id) {
        return studentService.getFirstNameById(id);
    }

    @GetMapping("getLastNameById/{id}")
    public String getLastNameById(@PathVariable Long id) {
        return studentService.getLastNameById(id);
    }

    @PostMapping("create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }
}
