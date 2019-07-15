package com.epam.spring.loggers;

import java.util.List;

import com.epam.spring.models.Event;

public class CombinedEventLogger implements EventLogger
{
	private List<EventLogger> eventLoggers;

	public CombinedEventLogger(List<EventLogger> eventLoggers)
	{
		this.eventLoggers = eventLoggers;
	}

	@Override
	public void logEvent(Event event)
	{
		eventLoggers.forEach((value) -> value.logEvent(event));
	}
}
