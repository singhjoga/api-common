package com.thetechnovator.api.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationContextEvent>{
	private static final Logger LOG = LoggerFactory.getLogger(StartupListener.class);
	@Autowired
	private CommonAppInitializer commonInitializer;

	@Autowired
	private CommonAppInitializer appInitializer;
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			LOG.info("Initializing application...");
			commonInitializer.onStart();
			appInitializer.onStart();
			LOG.info("Application initialization completed");
		}if (event instanceof ContextClosedEvent) {
			LOG.info("Closing application");
			commonInitializer.onStop();
			appInitializer.onStop();
		}if (event instanceof ContextStartedEvent) {
			LOG.info("ContextStartedEvent");
		}if (event instanceof ContextStoppedEvent) {
			LOG.info("ContextStoppedEvent");
		}			
	}

}
