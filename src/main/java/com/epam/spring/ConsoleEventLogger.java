package com.epam.spring;

public class ConsoleEventLogger implements EventLogger
{
	public void logEvent(String message)
	{
		System.out.println(message);
	}
}
