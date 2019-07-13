package com.epam.spring;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.spring.loggers.EventLogger;
import com.epam.spring.models.Client;
import com.epam.spring.models.Event;

public class App
{
	private EventLogger eventLogger;
	private Client client;

	public App(EventLogger eventLogger, Client client)
	{
		this.eventLogger = eventLogger;
		this.client = client;
	}

	public void logEvent(String message, Event event)
	{
		event.setMessage(message);
		event.setId(new Random().ints(0, 10_000).findFirst().getAsInt());
		eventLogger.logEvent(event);
	}

	public static void main(String[] args)
	{
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		App app = applicationContext.getBean(App.class);

		app.logEvent("First call of logEvent()", applicationContext.getBean(Event.class));
		app.logEvent("Second call of logEvent()", applicationContext.getBean(Event.class));
		app.logEvent("Third call of logEvent()", applicationContext.getBean(Event.class));
		app.logEvent("Fourth call of logEvent()", applicationContext.getBean(Event.class));
		app.logEvent("Fifth call of logEvent()", applicationContext.getBean(Event.class));

		applicationContext.close();
	}
}
