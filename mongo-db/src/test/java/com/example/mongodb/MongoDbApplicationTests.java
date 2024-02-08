package com.example.mongodb;

import com.example.controller.StudentController;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class MongoDbApplicationTests {

    @Autowired
    StudentController controller;

    @Autowired
    StudentService service;

    @Test
    void contextLoads() {
        assertThat(controller, not(nullValue()));
        assertThat(service, not(nullValue()));
    }

}
