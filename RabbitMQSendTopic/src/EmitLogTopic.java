import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Just modified code from:
 * https://www.rabbitmq.com/tutorials/tutorial-five-java.html
 * 
 * @author Thang Le
 *
 */
public class EmitLogTopic {

	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			
			// maybe to disable ssl if you test in localhost
			//factory.useSslProtocol();
			factory.setConnectionTimeout(60 * 1000);
			factory.setUri("amqp://guest:guest@localhost:5672/%2F");

			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

			String routingKey1 = "*.error";
			String routingKey2 = "*.warn";
			String message1 = "hello world from log error";
			String message2 = "hello world from log warning";

			channel.basicPublish(EXCHANGE_NAME, routingKey1, null, message1.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + routingKey1 + "':'" + message1 + "'");

			channel.basicPublish(EXCHANGE_NAME, routingKey2, null, message2.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + routingKey2 + "':'" + message2 + "'");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

}