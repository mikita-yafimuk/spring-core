package com.epam.spring.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.spring.models.Event;

@Component
public class ConsoleEventLogger implements EventLogger
{
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void logEvent(Event event)
	{
		LOGGER.info(event.toString());
	}
}
