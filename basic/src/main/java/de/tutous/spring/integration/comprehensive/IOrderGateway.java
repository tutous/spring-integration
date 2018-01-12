package de.tutous.spring.integration.comprehensive;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "orderGateway")
public interface IOrderGateway {

	/**
	 * Processes Order Request
	 *
	 * @param message
	 *            SI Message covering Order List payload and Batch Order Id header.
	 * @return operation result
	 */
	@Gateway(requestChannel = "orderGWDefaultRequestChannel")
	void processOrder(Message<List<Order>> message);

}
