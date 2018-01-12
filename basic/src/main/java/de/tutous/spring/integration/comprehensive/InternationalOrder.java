package de.tutous.spring.integration.comprehensive;

public class InternationalOrder extends Order {

	public enum DeliveryOption {
		NEXT_FLIGHT, PRIORITY, ECONOMY, STANDART
	}

	private final DeliveryOption deliveryOption;

	public InternationalOrder(Order order, DeliveryOption deliveryOption) {
		super(order);
		this.deliveryOption = deliveryOption;
	}

	public DeliveryOption getDeliveryOption() {
		return deliveryOption;
	}

}
