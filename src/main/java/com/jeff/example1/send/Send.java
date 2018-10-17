package com.jeff.example1.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

  private final static String QUEUE_NAME = "hello2";

  public static void main(String[] argv) throws Exception {
    for (int i=0; i<=100; i++){
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.queueDeclare(QUEUE_NAME, true, false, false, null);
      String message = "Hello World123456!";
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
      System.out.println(" [x] Sent '" + message + "'");

      channel.close();
      connection.close();
    }
  }
}