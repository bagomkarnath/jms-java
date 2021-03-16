package com.demo.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("email.sev1.message", "This message is a Sev 1 message"); //Sev1
		messages.put("email.sev2.message", "This message is a Sev 2 message");	//Sev2
		messages.put("email.repair.machine", "This message is a Repair message");//repair
		messages.put("email.db.restart", "This message is a DB Restart message");//restart
		messages.put("email.server.restart", "This message is a Server Restart message");//restart
		messages.put("email.sev1.restart", "This message is a Sev 1 Server restart message"); //sev1, restart
		messages.put("email.sev1.repair.restart", "This message is a Sev 1  Repair and Restart message"); //restart, repair
		
		//	sev1 - 2, sev2 - 1, repair - 2, restart - 4
		
		for(Map.Entry<String, String> entry : messages.entrySet()) {
			channel.basicPublish("Topic-Exchange", entry.getKey(), null, entry.getValue().getBytes());
		}
		
		channel.close();
		connection.close();

	}

}
