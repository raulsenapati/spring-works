package com.demo.rabbitmq.headers;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.*;

/**
 * @author rahul
 */
public class HeadersPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and TV";
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put(ITEM_1, "mobile");
        headersMap.put(ITEM_2, "television");

        BasicProperties br = new BasicProperties();
        br = br.builder()
                .headers(headersMap)
                .build();


//        String[] messages = {"First", "Second", "Third", "Fourth"};
//        for (String message : messages)
//            channel.basicPublish("", QUEUE_1, null, message.getBytes());
        channel.basicPublish(HEADERS_EXCHANGE, "", br, message.getBytes());

        channel.close();
        connection.close();
    }
}
