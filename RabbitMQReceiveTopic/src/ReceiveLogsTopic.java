import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Just modified code from:
 * https://www.rabbitmq.com/tutorials/tutorial-five-java.html
 * 
 * @author Thang Le
 *
 */
public class ReceiveLogsTopic {

	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();

		// maybe to disable ssl if you test in localhost
		//factory.useSslProtocol();
		factory.setConnectionTimeout(60 * 1000);
		factory.setUri("amqp://guest:guest@localhost:5672/%2F");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		String queueName = channel.queueDeclare().getQueue();

		String bindingKey1 = "*.error";
		String bindingKey2 = "*.warn";

		String routKey[] = { bindingKey1, bindingKey2 };

		for (String bindingKey : routKey) {
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
		}

		System.out.println(" [*] Waiting for messages from sender....");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}