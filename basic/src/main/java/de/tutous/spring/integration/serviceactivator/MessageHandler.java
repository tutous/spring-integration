package de.tutous.spring.integration.serviceactivator;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

	@ServiceActivator(inputChannel = "inputChannel", outputChannel = "outputChannel", sendTimeout = "1000")
	public Message handle(String name) {
		return new Message("Hello " + name);
	}

}
