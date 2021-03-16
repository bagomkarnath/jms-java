package com.demo.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeadersPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		String message1 = "This message is for Sev-1 and Repair";
		String message2 = "This message is for Sev-1 and Restart";
		String message3 = "This message is for Sev-2";
		
		Map<String, Object> headersMap1 = new HashMap<String, Object>();
		headersMap1.put("sev", "sev2");
		headersMap1.put("pri", "p2");
		
		BasicProperties br1 = new BasicProperties();
		br1 = br1.builder().headers(headersMap1).build();
		
		channel.basicPublish("Headers-Exchange", "", br1, message3.getBytes());
		
		Map<String, Object> headersMap2 = new HashMap<String, Object>();
		headersMap2.put("sev", "sev1");
		headersMap2.put("request", "restart");
		BasicProperties br2 = new BasicProperties();
		br2 = br2.builder().headers(headersMap2).build();
		
		channel.basicPublish("Headers-Exchange", "", br2, message2.getBytes());
		
		Map<String, Object> headersMap3 = new HashMap<String, Object>();
		headersMap3.put("sev", "sev1");
		headersMap3.put("request", "repair");
		BasicProperties br3 = new BasicProperties();
		br3 = br3.builder().headers(headersMap3).build();
		
		channel.basicPublish("Headers-Exchange", "", br3, message1.getBytes());
		
		channel.close();
		connection.close();

	}

}
