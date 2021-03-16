package com.demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String messageTV = "This message is for TV";
		String messageAC = "This message is for AC";
		String messageMobile = "This message is for Mobile";
		
		channel.basicPublish("Direct-Exchange", "mobile", null, messageMobile.getBytes());
		channel.basicPublish("Direct-Exchange", "tv", null, messageTV.getBytes());
		channel.basicPublish("Direct-Exchange", "ac", null, messageAC.getBytes());
		
		channel.close();
		connection.close();
		
	}

}
