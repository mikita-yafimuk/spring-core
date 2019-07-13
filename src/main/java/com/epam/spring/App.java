package com.epam.spring;

public class App
{
	private ConsoleEventLogger consoleEventLogger;
	private Client client;

	public void logEvent(String message)
	{
		consoleEventLogger.logEvent(message.replaceAll(client.getId(), client.getFullName()));
	}

	public static void main(String[] args)
	{
		App app = new App();
		app.client = new Client("1", "Mikita Yafimuk");
		app.consoleEventLogger = new ConsoleEventLogger();
		app.logEvent("Some event for user 1");
	}
}
