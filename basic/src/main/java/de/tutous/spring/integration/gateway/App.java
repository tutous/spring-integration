package de.tutous.spring.integration.gateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
@ComponentScan(basePackages = "de.tutous.spring.integration.annotations")
@IntegrationComponentScan
public class App {

	public static void main(String[] args) {
		create(args);
	}

	static GenericApplicationContext create(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		context.registerShutdownHook();
		return context;
	}

	@MessagingGateway(name = "echoGateway")
	public interface EchoGateway {

		@Gateway(requestChannel = "requestChannel")
		public String echo(String message);

	}

	@MessagingGateway(name = "messageGateway")
	public interface MessageGateway {

		@Gateway(replyChannel = "replyChannel")
		public String getMessage();

	}

	@Bean
	@Qualifier("requestChannel")
	public DirectChannel requestChannel() {
		return new DirectChannel();
	}

//	@Bean
//	public IntegrationFlow replyChannelEchoFlow() {
//		return IntegrationFlows.from("replyChannel")//
//				.transform((String s) -> s.toLowerCase())//
//				.get();
//	}

	@Bean
	public IntegrationFlow requestChannelEchoFlow() {
		return IntegrationFlows.from("requestChannel")//
				.transform((String s) -> s.toUpperCase())//
				.get();
	}

	@Bean
	@Qualifier("replyChannel")
	public MessageChannel replyChannel() {
		return new PollableChannel() {

			private Message<?> message;

			public boolean send(Message<?> message, long timeout) {
				this.message = message;
				return true;
			}

			public Message<?> receive(long timeout) {
				return this.message;
			}

			public Message<?> receive() {
				return this.message;
			}
		};
	}

}
