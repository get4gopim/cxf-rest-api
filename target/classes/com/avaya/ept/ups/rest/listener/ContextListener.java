package com.avaya.ept.ups.rest.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class ContextListener extends ContextLoaderListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOGGER.info("\n------------\nStarting UPS Web Service \n------------");
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		LOGGER.info("\n------------\nStopping UPS Web Service \n------------");
	}

}
