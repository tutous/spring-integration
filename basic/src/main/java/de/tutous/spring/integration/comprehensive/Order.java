package de.tutous.spring.integration.comprehensive;

public class Order {

	public enum ShippingType {
		DOMESTIC, INTERNATIONAL
	}

	private String number;
	private String name;
	private Long weight;
	private ShippingType shippingType;
	private Integer regionIndex;
	private Integer deliveryDayCommitment;

	public Order(String number, String name, Long weight, ShippingType shippingType) {
		super();
		this.number = number;
		this.name = name;
		this.weight = weight;
		this.shippingType = shippingType;
	}

	public Order(Order order) {
		super();
		this.number = order.getNumber();
		this.name = order.getName();
		this.weight = order.getWeight();
		this.shippingType = order.getShippingType();
		this.regionIndex = order.getRegionIndex();
		this.deliveryDayCommitment = order.getDeliveryDayCommitment();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public ShippingType getShippingType() {
		return shippingType;
	}

	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}

	public Integer getRegionIndex() {
		return regionIndex;
	}

	public void setRegionIndex(Integer regionIndex) {
		this.regionIndex = regionIndex;
	}

	public Integer getDeliveryDayCommitment() {
		return deliveryDayCommitment;
	}

	public void setDeliveryDayCommitment(Integer deliveryDayCommitment) {
		this.deliveryDayCommitment = deliveryDayCommitment;
	}

	@Override
	public String toString() {
		return "Order [number=" + number + ", name=" + name + ", weight=" + weight + ", shippingType=" + shippingType
				+ ", regionIndex=" + regionIndex + ", deliveryDayCommitment=" + deliveryDayCommitment + "]";
	}

}
