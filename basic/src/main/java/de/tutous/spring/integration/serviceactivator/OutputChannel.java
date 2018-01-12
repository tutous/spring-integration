package de.tutous.spring.integration.serviceactivator;

import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

public class OutputChannel implements PollableChannel {

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

}
