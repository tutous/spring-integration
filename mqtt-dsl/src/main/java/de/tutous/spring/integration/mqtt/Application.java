package de.tutous.spring.integration.mqtt;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
public class Application {

	private static final Log LOGGER = LogFactory.getLog(Application.class);

	/**
	 * Load the Spring Integration Application Context
	 *
	 * @param args
	 *            - command line arguments
	 */
	public static void main(final String... args) {

		LOGGER.info("\n========================================================="
				+ "\n                                                         "
				+ "\n          Welcome to Spring Integration!                 "
				+ "\n                                                         "
				+ "\n    For more information please visit:                   "
				+ "\n    https://spring.io/projects/spring-integration        "
				+ "\n                                                         "
				+ "\n=========================================================");

		LOGGER.info("\n========================================================="
				+ "\n                                                          "
				+ "\n    This is the MQTT Sample -                             "
				+ "\n                                                          "
				+ "\n    Please enter some text and press return. The entered  "
				+ "\n    Message will be sent to the configured MQTT topic,    "
				+ "\n    then again immediately retrieved from the Message     "
				+ "\n    Broker and ultimately printed to the command line.    "
				+ "\n                                                          "
				+ "\n=========================================================");

		SpringApplication.run(Application.class, args);

	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs("tcp://broker.hivemq.com:1883");
		return factory;
	}

	// publisher

	@Bean
	public IntegrationFlow mqttOutFlow() {
		return IntegrationFlows //
				.from(CharacterStreamReadingMessageSource.stdin(), e -> e.poller(Pollers.fixedDelay(1000))) //
				.transform(p -> p + " sent to MQTT") //
				.handle(mqttOutbound()) //
				.get();
	}

	@Bean
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(UUID.randomUUID().toString(),
				mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("topic/weather/info");
		return messageHandler;
	}

	// consumer

	@Bean
	public IntegrationFlow mqttInFlow() {
		return IntegrationFlows.from(mqttInbound())//
				.transform(p -> p + ", received from MQTT")//
				.handle(messageHandler())//
				.get();
	}

	@Bean
	public MessageHandler messageHandler() {
		MessageHandler handler = new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println(message.getPayload());
			}
		};
		return handler;
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = //
				new MqttPahoMessageDrivenChannelAdapter(UUID.randomUUID().toString(), mqttClientFactory(),
						"topic/weather/info");
		adapter.setCompletionTimeout(1000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		return adapter;
	}

}