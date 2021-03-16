package com.demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class HeadersConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody());
			System.out.println(consumerTag + " Message recived = " + message);
		};
		
		channel.basicConsume("Sev1", true, deliverCallback, consumerTag -> {});
		channel.basicConsume("Sev2", true, deliverCallback, consumerTag -> {});
		channel.basicConsume("Repair", true, deliverCallback, consumerTag -> {});
		channel.basicConsume("Restart", true, deliverCallback, consumerTag -> {});

	}

}
