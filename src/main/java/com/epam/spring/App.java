package com.epam.spring;

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

	public void logEvent(String message)
	{
		consoleEventLogger.logEvent(message.replaceAll(client.getId(), client.getFullName()));
	}

	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		App app = applicationContext.getBean(App.class);
		app.logEvent("Some event for user 1");
		app.logEvent("Some event for user 2");
	}
}
