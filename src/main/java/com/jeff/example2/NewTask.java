package com.jeff.example2;

import java.io.IOException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv)
                      throws java.io.IOException {
    try {

      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

      String message = "this is a test!";
      for (int i = 0; i< 10; i++) {
        channel.basicPublish( "", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes());
      }

      System.out.println(" [x] Sent '" + message + "'");

      channel.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}