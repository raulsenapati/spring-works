package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Spring JPA", description = "Play with Postgresql DB operations",
        contact = @Contact(url = "https://www.linkedin.com/in/rahulsenapati/", name = "Rahul Senapati")))
@ComponentScan("com.example.*")
@EntityScan("com.example.entity")
@EnableJpaRepositories("com.example.repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
