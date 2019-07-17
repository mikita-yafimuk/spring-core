package com.epam.spring;

import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.spring.loggers.EventLogger;
import com.epam.spring.models.Client;
import com.epam.spring.models.Event;
import com.epam.spring.models.EventType;

@Component
public class App
{
	private final static Logger LOGGER = LogManager.getLogger();

	private EventLogger defaultLogger;
	private Client client;
	private Map<EventType, EventLogger> loggers;

	@Autowired
	public App(@Qualifier("consoleEventLogger") EventLogger defaultLogger, Client client, @Qualifier("loggersMap") Map<EventType, EventLogger> loggers)
	{
		this.defaultLogger = defaultLogger;
		LOGGER.info(client.getGreeting());
		this.client = client;
		this.loggers = loggers;
	}

	public void logEvent(String message, Event event, EventType eventType)
	{
		event.setMessage(message);
		event.setId(new Random().ints(0, 10_000).findFirst().getAsInt());
		EventLogger logger = loggers.get(eventType);
		if (logger == null)
		{
			logger = defaultLogger;
		}
		logger.logEvent(event);
	}

	public static void main(String[] args)
	{
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		App app = applicationContext.getBean(App.class);

		app.logEvent("First call of logEvent() with INFO (file)", applicationContext.getBean(Event.class), EventType.INFO);
		app.logEvent("Second call of logEvent() with ERROR (console + file)", applicationContext.getBean(Event.class), EventType.ERROR);
		app.logEvent("Third call of logEvent() with null (default - console)", applicationContext.getBean(Event.class), null);

		applicationContext.close();
	}
}
