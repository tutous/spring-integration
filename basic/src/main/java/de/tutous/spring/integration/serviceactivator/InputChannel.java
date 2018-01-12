package de.tutous.spring.integration.serviceactivator;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

public class InputChannel implements SubscribableChannel {

	private MessageHandler handler;

	public boolean send(Message<?> message, long timeout) {
		handler.handleMessage(message);
		return true;
	}

	public boolean unsubscribe(MessageHandler handler) {
		this.handler = null;
		return true;
	}

	public boolean subscribe(MessageHandler handler) {
		this.handler = handler;
		return true;
	}
}
