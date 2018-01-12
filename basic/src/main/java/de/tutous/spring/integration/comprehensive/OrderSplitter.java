package de.tutous.spring.integration.comprehensive;

import java.util.List;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;

@MessageEndpoint
public class OrderSplitter {

	/**
	 * Splits Order List.
	 *
	 * @param message
	 *            SI Message covering Order List payload and Order Cargo Id header.
	 * @return cargo list
	 */
	@Splitter(inputChannel = "orderGWDefaultRequestChannel", outputChannel = "orderSplitterOutputChannel")
	public List<Order> splitCargoList(Message<List<Order>> message) {
		return message.getPayload();
	}

}
