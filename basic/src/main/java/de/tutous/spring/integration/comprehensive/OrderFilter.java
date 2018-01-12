package de.tutous.spring.integration.comprehensive;

import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class OrderFilter {

	private static final long ORDER_WEIGHT_LIMIT = 10;

	/**
	 * Checks weight of order and filters if it exceeds limit.
	 *
	 * @param Order
	 *            message
	 * @return check result
	 */
	@Filter(inputChannel = "orderSplitterOutputChannel", outputChannel = "orderFilterOutputChannel", discardChannel = "orderFilterDiscardChannel")
	public boolean filterIfOrderWeightExceedsLimit(Order order) {
		return order.getWeight() <= ORDER_WEIGHT_LIMIT;
	}

}
