package de.tutous.spring.integration.comprehensive;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

public class AppConfig {

	/**
	 * Creates a new orderGWDefaultRequest Channel and registers to BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderGWDefaultRequestChannel() {
		return new DirectChannel();
	}

	/**
	 * Creates a new orderSplitterOutput Channel and registers to BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderSplitterOutputChannel() {
		return new DirectChannel();
	}

	/**
	 * Creates a new orderFilterOutput Channel and registers to BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderFilterOutputChannel() {
		return new DirectChannel();
	}

	/**
	 * Creates a new orderRouterDomesticOutput Channel and registers to BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderRouterDomesticOutputChannel() {
		return new DirectChannel();
	}

	/**
	 * Creates a new orderRouterInternationalOutput Channel and registers to
	 * BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderRouterInternationalOutputChannel() {
		return new DirectChannel();
	}

	/**
	 * Creates a new orderTransformerOutput Channel and registers to BeanFactory.
	 *
	 * @return direct channel
	 */
	@Bean
	public MessageChannel orderTransformerOutputChannel() {
		return new DirectChannel();
	}

}
