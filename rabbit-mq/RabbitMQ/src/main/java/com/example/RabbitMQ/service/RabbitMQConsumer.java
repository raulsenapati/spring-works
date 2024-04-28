package com.example.RabbitMQ.service;

import com.example.RabbitMQ.response.Person;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import static com.example.RabbitMQ.utils.Constants.AC;
import static com.example.RabbitMQ.utils.Constants.MOBILE;

/**
 * @author rahul
 */
@Service
@EnableRabbit
public class RabbitMQConsumer {

//    @RabbitListener(queues = {MOBILE, TV})
//    public void getMessage(Person person) {
//        System.out.println(person.toString());
//    }


    @RabbitListener(queues = {MOBILE, AC})
    public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput is = new ObjectInputStream(bis);
        Person person = (Person) is.readObject();
        is.close();
        bis.close();
        System.out.println(person.toString());
    }
}
