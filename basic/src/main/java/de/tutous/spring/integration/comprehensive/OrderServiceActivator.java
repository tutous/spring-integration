package de.tutous.spring.integration.comprehensive;

import java.util.logging.Logger;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;

@MessageEndpoint
public class OrderServiceActivator {

	private final Logger logger = Logger.getLogger(OrderServiceActivator.class.getName());

	/**
	 * Gets processed domestic and international order request(s) and logs.
	 *
	 * @param orderMessage
	 *            domestic/international order message
	 * @param batchId
	 *            message header shows order batch id
	 */
	@ServiceActivator(inputChannel = "orderTransformerOutputChannel")
	public void getOrder(Order order, @Header("ORDER_BATCH_ID") long batchId) {
		logger.info("\n Message in Batch[" + batchId + "] is received with payload : " + order + "\n");
	}
}
