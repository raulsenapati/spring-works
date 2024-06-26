package com.demo.rabbitmq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.QUEUE_1;

/**
 * @author rahul
 */
public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //String message = "First message from RabbitMQ";

        String[] messages = {"First", "Second", "Third", "Fourth"};
        for (String message : messages)
            channel.basicPublish("", QUEUE_1, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
