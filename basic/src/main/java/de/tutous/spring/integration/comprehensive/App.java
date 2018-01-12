package de.tutous.spring.integration.comprehensive;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@ComponentScan("de.tutous.spring.integration.comprehensive")
@EnableIntegration
@IntegrationComponentScan("de.tutous.spring.integration.comprehensive")
public class App {

	public static void main(String[] args) {
		create(args);
	}

	static GenericApplicationContext create(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		context.registerShutdownHook();
		return context;
	}
	
}
