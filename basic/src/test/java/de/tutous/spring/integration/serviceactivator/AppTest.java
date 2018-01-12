package de.tutous.spring.integration.serviceactivator;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.tutous.spring.integration.serviceactivator.Message;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AppInitializer.class)
public class AppTest {

	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel input;

	@Autowired
	@Qualifier("outputChannel")
	private PollableChannel output;

	@Test
	public void test() {

		input.send(new GenericMessage<String>("Uwe"));

		Assert.assertThat(output.receive().getPayload(), //
				Matchers.notNullValue());

		Assert.assertThat(output.receive().getPayload().getClass().getName(), //
				Matchers.equalTo(Message.class.getName()));

		Assert.assertThat(((Message) output.receive().getPayload()).getMessage(), //
				Matchers.equalTo("Hello Uwe"));

	}

}
