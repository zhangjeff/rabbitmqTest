package com.jeff.example5;

import com.rabbitmq.client.*;

import java.io.IOException;

public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = "a.ttt.zzz";
            String message = "this is a topic test";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}