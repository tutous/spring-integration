package de.tutous.spring.integration.comprehensive;

import java.util.logging.Logger;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;

@MessageEndpoint
public class DiscardedOrderMessage {

	private final Logger logger = Logger.getLogger(DiscardedOrderMessage.class.getName());

	/**
	 * Handles discarded domestic and international order request(s) and logs.
	 *
	 * @param order
	 *            domestic/international order message
	 * @param batchId
	 *            message header shows order batch id
	 */
	@ServiceActivator(inputChannel = "orderFilterDiscardChannel")
	public void handleDiscardedorder(Order order, @Header("ORDER_BATCH_ID") long batchId) {
		logger.info("\n Message in Batch[" + batchId + "] is received with Discarded payload : " + order + "\n");
	}

}
