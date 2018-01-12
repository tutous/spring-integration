package de.tutous.spring.integration.serviceactivator;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import de.tutous.spring.integration.serviceactivator.App;

public class AppInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

	public void initialize(GenericApplicationContext applicationContext) {
		applicationContext.setParent(App.create(new String[] {}));
	}

}
