package com.example.RabbitMQ.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rahul
 */
@Data
@AllArgsConstructor
public class Person implements Serializable {

    private Long id;

    private String name;
}
