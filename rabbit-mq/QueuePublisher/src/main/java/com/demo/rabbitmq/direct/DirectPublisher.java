package com.demo.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.DIRECT_EXCHANGE;

/**
 * @author rahul
 */
public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String, String> mapKeyMessage = new HashMap<>();
        mapKeyMessage.put("mobile", "This is mobile");
        mapKeyMessage.put("tv", "This is tv");
        mapKeyMessage.put("ac", "This is ac");

        for (String key : mapKeyMessage.keySet()) {
            String message = mapKeyMessage.get(key);
            channel.basicPublish(DIRECT_EXCHANGE, key, null, message.getBytes());
        }
        channel.close();
        connection.close();

    }
}
