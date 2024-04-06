package com.demo.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.demo.utils.Constants.FANOUT_EXCHANGE;

/**
 * @author rahul
 */
public class FanoutPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String message = "Message For Mobile and AC";

        /*
         * basicPublish(String exchange,
         *              String routingKey, <-- can NOT be null
         *              AMQP.BasicProperties var,
         *              byte[] messageBytes)
         * */
        channel.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());

        channel.close();
        connection.close();
    }
}
