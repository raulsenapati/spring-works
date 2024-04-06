package com.demo.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.TOPIC_EXCHANGE;

/**
 * @author rahul
 */
public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and AC";

        channel.basicPublish(TOPIC_EXCHANGE, "tv.mobile.ac", null, message.getBytes());

        channel.close();
        connection.close();

    }

}
