package com.demo.rabbitmq.realtime;

import com.demo.response.RealTimeResponse;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.QUEUE_1;

/**
 * @author rahul
 */
public class RealTimeConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            Gson gson = new Gson();
            String json = new String(delivery.getBody());
            RealTimeResponse realTimeResponse = gson.fromJson(json, RealTimeResponse.class);
            System.out.println("Message received = " + realTimeResponse);
        };
        channel.basicConsume(QUEUE_1, true, deliverCallback, consumerTag -> {
        });
    }
}
