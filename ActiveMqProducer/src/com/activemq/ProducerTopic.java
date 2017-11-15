package com.activemq;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTopic {

	public ProducerTopic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Note: ConsumerTopic class must startup first
	 * 
	 * @param args
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static void main(String[] args) throws URISyntaxException, Exception {

		// BrokerService broker = BrokerFactory.createBroker(new
		// URI("broker:(tcp://localhost:61616)"));
		// broker.start();

		Connection connection = null;
		try {
			// Producer

			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("TEST.FOO");

			// Publish
			String payload = "Important Task";

			Message msg = session.createTextMessage(payload);

			MessageProducer producer = session.createProducer(topic);

			System.out.println("Sending text '" + payload + "'");

			producer.send(msg);

			Thread.sleep(5000);

		} finally {

			if (connection != null) {
				connection.close();
			}

			// broker.stop();

		}

	}

}
