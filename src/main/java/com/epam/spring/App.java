package com.epam.spring;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
	private ConsoleEventLogger consoleEventLogger;
	private Client client;

	public App(ConsoleEventLogger consoleEventLogger, Client client)
	{
		this.consoleEventLogger = consoleEventLogger;
		this.client = client;
	}

	public void logEvent(String message, Event event)
	{
		event.setMessage(message);
		event.setId(new Random().ints(0, 10_000).findFirst().getAsInt());
		consoleEventLogger.logEvent(event);
	}

	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		App app = applicationContext.getBean(App.class);
		app.logEvent("First call of logEvent()", applicationContext.getBean(Event.class));
		app.logEvent("Second call of logEvent()", applicationContext.getBean(Event.class));
	}
}
