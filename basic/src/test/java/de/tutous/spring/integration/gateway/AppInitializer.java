package de.tutous.spring.integration.gateway;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import de.tutous.spring.integration.gateway.App;

public class AppInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

	public void initialize(GenericApplicationContext applicationContext) {
		applicationContext.setParent(App.create(new String[] {}));
	}

}
