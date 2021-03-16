package com.demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class JsonConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody());
			JSONObject json = new JSONObject(message);
			System.out.println("JSON Message recived = " + json.toString());
		};
		
		channel.basicConsume("Queue-1", true, deliverCallback, consumerTag -> {});

	}

}
