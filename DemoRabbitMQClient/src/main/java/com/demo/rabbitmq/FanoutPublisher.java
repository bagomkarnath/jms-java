package com.demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String message = "This message is for AC and Mobile";
		
		channel.basicPublish("Fanout-Exchange", "", null, message.getBytes());
		
		
		channel.close();
		connection.close();

	}

}
