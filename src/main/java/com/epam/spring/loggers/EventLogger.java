package com.epam.spring.loggers;

import com.epam.spring.models.Event;

public interface EventLogger
{
	void logEvent(Event event);
}
