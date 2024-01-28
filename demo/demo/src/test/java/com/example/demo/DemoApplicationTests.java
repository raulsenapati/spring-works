package com.example.demo;

import com.example.controller.StudentController;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StudentController controller;

    @Autowired
    private StudentService service;

    @Test
    void contextLoads() {
        assertThat(controller, not(nullValue()));
        assertThat(service, not(nullValue()));
    }

}
