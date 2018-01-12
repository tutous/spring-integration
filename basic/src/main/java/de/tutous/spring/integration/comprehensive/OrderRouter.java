package de.tutous.spring.integration.comprehensive;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import de.tutous.spring.integration.comprehensive.Order.ShippingType;

@MessageEndpoint
public class OrderRouter {

	/**
	 * Determines order request' s channel in the light of shipping type.
	 *
	 * @param order
	 *            message
	 * @return channel name
	 */
	@Router(inputChannel = "orderFilterOutputChannel")
	public String route(Order order) {
		if (order.getShippingType() == ShippingType.DOMESTIC) {
			return "orderRouterDomesticOutputChannel";
		} else if (order.getShippingType() == ShippingType.INTERNATIONAL) {
			return "orderRouterInternationalOutputChannel";
		}
		return "nullChannel";
	}

}
