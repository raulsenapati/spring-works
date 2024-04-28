package com.example.RabbitMQ.controller;

import com.example.RabbitMQ.response.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import static com.example.RabbitMQ.utils.Constants.*;

/**
 * @author rahul
 */
@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testAPT(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        boolean enabled = false;
        if (enabled) {
            rabbitTemplate.convertAndSend(MOBILE, person);
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, "mobile", person);
            rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", person);
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, "tv.mobile.ac", person);
        }
        return enabled ? "Success" : "Disabled";
    }

    @GetMapping("/test-header/{name}")
    public String testHeaderAPT(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        byte[] byteMessage = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(person);
            out.flush();
            out.close();

            byteMessage = bos.toByteArray();
            bos.close();
        } catch (IOException io) {
            io.printStackTrace();
        }

        Message message = MessageBuilder.withBody(byteMessage)
                .setHeader("item1", "mobile")
                .setHeader("item2", "television")
                .build();

        rabbitTemplate.send(HEADERS_EXCHANGE, "", message);

        return "Success";
    }
}
