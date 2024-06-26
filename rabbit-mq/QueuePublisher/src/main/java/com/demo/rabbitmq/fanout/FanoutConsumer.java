package com.demo.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.AC;
import static com.demo.utils.Constants.MOBILE;

/**
 * @author rahul
 */
public class FanoutConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received = " + message);
        };
        channel.basicConsume(MOBILE, true, deliverCallback, consumerTag -> {
        });
        channel.basicConsume(AC, true, deliverCallback, consumerTag -> {
        });
    }
}
