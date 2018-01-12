package de.tutous.spring.integration.comprehensive;

public class OrderBuilder {

	private final Order order;

	private OrderBuilder(Order order) {
		this.order = order;
	}

	public OrderBuilder setRegionIndex(Integer regionIndex) {
		order.setRegionIndex(regionIndex);
		return this;
	}

	public OrderBuilder setDeliveryDayCommitment(Integer deliveryDayCommitment) {
		order.setDeliveryDayCommitment(deliveryDayCommitment);
		return this;
	}

	public static OrderBuilder create(Order order) {
		return new OrderBuilder(order);
	}

	public Order build() {
		return order;
	}

}
