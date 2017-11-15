package com.activemq;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerTopic {

	public ConsumerTopic() {

	}

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

			// Consumer1 subscribes to customerTopic
			MessageConsumer consumer1 = session.createConsumer(topic);
			consumer1.setMessageListener(new ConsumerMessageListener("Consumer1"));

			// Consumer2 subscribes to customerTopic
			MessageConsumer consumer2 = session.createConsumer(topic);
			consumer2.setMessageListener(new ConsumerMessageListener("Consumer2"));

			// Consumer2 subscribes to customerTopic
			MessageConsumer consumer3 = session.createConsumer(topic);
			consumer3.setMessageListener(new ConsumerMessageListener("Consumer3"));

			Thread.sleep(10000);

		} finally {

			if (connection != null) {
				connection.close();
			}

			// broker.stop();

		}

	}

}
