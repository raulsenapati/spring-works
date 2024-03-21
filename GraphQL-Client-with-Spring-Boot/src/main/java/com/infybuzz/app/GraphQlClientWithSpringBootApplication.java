package com.infybuzz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.infybuzz.controller", "com.infybuzz.service"})
public class GraphQlClientWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQlClientWithSpringBootApplication.class, args);
    }

}
