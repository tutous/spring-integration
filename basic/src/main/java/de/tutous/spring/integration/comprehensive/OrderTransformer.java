package de.tutous.spring.integration.comprehensive;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

import de.tutous.spring.integration.comprehensive.DomesticOrder.Region;
import de.tutous.spring.integration.comprehensive.InternationalOrder.DeliveryOption;

@MessageEndpoint
public class OrderTransformer {

	 /**
     * Transforms Order request to Domestic Order obj.
     *
     * @param order
     *            request
     * @return Domestic Order obj
     */
    @Transformer(inputChannel = "orderRouterDomesticOutputChannel", 
                    outputChannel = "orderTransformerOutputChannel")
    public DomesticOrder transformDomesticOrder(Order order) {
        return new DomesticOrder(order, Region.fromValue(order.getRegionIndex()));
    }

    /**
     * Transforms Order request to International Order obj.
     *
     * @param order
     *            request
     * @return International Order obj
     */
    @Transformer(inputChannel = "orderRouterInternationalOutputChannel", 
                    outputChannel = "orderTransformerOutputChannel")
    public InternationalOrder transformInternationalOrder(Order order) {
        return new InternationalOrder(order, getDeliveryOption(order.getDeliveryDayCommitment()));
    }

    /**
     * Get delivery option by delivery day commitment.
     *
     * @param deliveryDayCommitment delivery day commitment
     * @return delivery option
     */
    private DeliveryOption getDeliveryOption(int deliveryDayCommitment) {
        if (deliveryDayCommitment == 1) {
            return DeliveryOption.NEXT_FLIGHT;
        } else if (deliveryDayCommitment == 2) {
            return DeliveryOption.PRIORITY;
        } else if (deliveryDayCommitment > 2 && deliveryDayCommitment < 5) {
            return DeliveryOption.ECONOMY;
        } else {
            return DeliveryOption.STANDART;
        }
    }
}
