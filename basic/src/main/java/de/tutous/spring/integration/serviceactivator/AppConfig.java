package de.tutous.spring.integration.serviceactivator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableIntegration
@ComponentScan(basePackages = "de.tutous.spring.integration.serviceactivator")
public class AppConfig {

	private PollableChannel outputChannel;

	private SubscribableChannel inputChannel;

	@Bean
	@Qualifier("inputChannel")
	public SubscribableChannel inputChannel() {
		if (inputChannel == null) {
			inputChannel = new InputChannel();
		}
		return inputChannel;
	}

	@Bean
	@Qualifier("outputChannel")
	public PollableChannel outputChannel() {
		if (outputChannel == null) {
			outputChannel = new OutputChannel();
		}
		return outputChannel;
	}

}
