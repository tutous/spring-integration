package de.tutous.spring.integration.comprehensive;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class AppInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

	public void initialize(GenericApplicationContext applicationContext) {
		applicationContext.setParent(App.create(new String[] {}));
	}

}
