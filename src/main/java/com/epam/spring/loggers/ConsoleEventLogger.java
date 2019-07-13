package com.epam.spring.loggers;

import com.epam.spring.models.Event;

public class ConsoleEventLogger implements EventLogger
{
	@Override
	public void logEvent(Event event)
	{
		System.out.println(event.toString());
	}
}
