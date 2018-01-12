package de.tutous.spring.integration.comprehensive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.tutous.spring.integration.comprehensive.Order.ShippingType;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AppInitializer.class)
public class AppTest {

	@Autowired
	@Qualifier("orderGateway")
	private IOrderGateway orderGateway;

	@Test
	public void test() {

		getOrderBatchMap().forEach((batchId, orderList) -> orderGateway
				.processOrder(MessageBuilder.withPayload(orderList).setHeader("ORDER_BATCH_ID", batchId).build()));

	}

	private static Map<Integer, List<Order>> getOrderBatchMap() {
		Map<Integer, List<Order>> orderBatchMap = new HashMap();

		orderBatchMap.put(1, Arrays.asList(//
				OrderBuilder.create(new Order("1", "order one", new Long(10), ShippingType.DOMESTIC))//
						.setRegionIndex(1).build(), //
				OrderBuilder.create(new Order("2", "order two", new Long(10), ShippingType.INTERNATIONAL))//
						.setDeliveryDayCommitment(10).build(), //
				OrderBuilder.create(new Order("3", "order three", new Long(20), ShippingType.INTERNATIONAL))//
						.setDeliveryDayCommitment(10).build()));

		return orderBatchMap;

	}

}
