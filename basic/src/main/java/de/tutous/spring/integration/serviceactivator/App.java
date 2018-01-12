package de.tutous.spring.integration.serviceactivator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class App {

	public static void main(String[] args) {
		create(args);
	}

	static GenericApplicationContext create(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		return context;
	}

}
