package com.epam.spring.loggers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.spring.models.Event;

public class CachedFileEventLogger extends FileEventLogger
{
	public static final Logger LOGGER = LogManager.getLogger();

	private int cacheSize;
	private List<Event> cache;

	public CachedFileEventLogger(int cacheSize, String fileName)
	{
		super(fileName);
		this.cacheSize = cacheSize;
		cache = new ArrayList<>();
	}

	@Override
	public void logEvent(Event event)
	{
		cache.add(event);
		LOGGER.debug(String.format("Added %s event to cache", cache.size()));

		if (cache.size() == cacheSize)
		{
			LOGGER.debug("Writing event to file and clear cache...");
			cache.forEach(super::logEvent);
			cache.clear();
		}
	}

	public void flush()
	{
		LOGGER.debug("Flush cashed events to file and clear cache...");
		if (!cache.isEmpty())
		{
			cache.forEach(super::logEvent);
		}
	}
}
