package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rahul
 * @project spring-works
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Value("${application.name: Default Demo App}")
    private String applicationName;

    @GetMapping("/get")
    public String getStudent() {
        return applicationName;
    }
}
