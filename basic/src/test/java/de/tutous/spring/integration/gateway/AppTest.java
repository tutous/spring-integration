package de.tutous.spring.integration.gateway;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.tutous.spring.integration.gateway.App.EchoGateway;
import de.tutous.spring.integration.gateway.App.MessageGateway;
import de.tutous.spring.integration.serviceactivator.Message;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AppInitializer.class)
public class AppTest {

	@Autowired
	@Qualifier("replyChannel")
	private MessageChannel replyChannel;

	@Autowired
	@Qualifier("messageGateway")
	private MessageGateway messageGateway;

	@Autowired
	@Qualifier("echoGateway")
	private EchoGateway echoGateway;

	@Test
	public void test() {

		replyChannel.send(new GenericMessage<String>("Uwe"));

		String message = messageGateway.getMessage();

		Assert.assertThat(message, Matchers.equalTo("Uwe"));

		message = echoGateway.echo(message);

		Assert.assertThat(message, Matchers.equalTo("UWE"));
		
	}

}
