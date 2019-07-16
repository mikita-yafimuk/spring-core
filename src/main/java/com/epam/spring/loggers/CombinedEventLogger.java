package com.epam.spring.loggers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.spring.models.Event;

@Component
public class CombinedEventLogger implements EventLogger
{
	private List<EventLogger> eventLoggers;

	@Autowired
	public CombinedEventLogger(@Qualifier("combinedLoggers") List<EventLogger> eventLoggers)
	{
		this.eventLoggers = eventLoggers;
	}

	@Override
	public void logEvent(Event event)
	{
		eventLoggers.forEach((value) -> value.logEvent(event));
	}
}
