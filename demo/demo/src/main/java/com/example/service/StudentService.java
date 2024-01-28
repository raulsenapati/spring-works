package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rahul
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
