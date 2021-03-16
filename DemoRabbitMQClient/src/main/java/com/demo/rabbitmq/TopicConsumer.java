package com.demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody());
			System.out.println("Message recived = " + message);
		};
		
		channel.basicConsume("Email-Sev1", true, deliverCallback, consumerTag -> {});
		
		channel.basicConsume("Email-Sev2", true, deliverCallback, consumerTag -> {});
		
		channel.basicConsume("Email-restart", true, deliverCallback, consumerTag -> {});
		
		channel.basicConsume("Email-repair", true, deliverCallback, consumerTag -> {});

	}

}
