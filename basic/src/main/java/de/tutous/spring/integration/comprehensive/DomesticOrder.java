package de.tutous.spring.integration.comprehensive;

import java.util.Arrays;

public class DomesticOrder extends Order {

	public enum Region {

		NORTH(1), SOUTH(2), EAST(3), WEST(4);

		private int value;

		private Region(int value) {
			this.value = value;
		}

		public static Region fromValue(int value) {
			return Arrays.stream(Region.values()).filter(region -> region.value == value).findFirst().get();
		}
	}

	private final Region region;

	public DomesticOrder(Order order, Region region) {
		super(order);
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}
}
