package com.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Mongo DB", description = "Play with Mongo DB operations",
        contact = @Contact(url = "https://www.linkedin.com/in/rahulsenapati/", name = "Rahul Senapati")))
@EnableMongoRepositories("com.example.repository")
@ComponentScan("com.example.*")
public class MongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbApplication.class, args);
    }

}
