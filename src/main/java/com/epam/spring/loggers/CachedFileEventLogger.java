package com.epam.spring.loggers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.spring.models.Event;

@Component
public class CachedFileEventLogger extends FileEventLogger
{
	public static final Logger LOGGER = LogManager.getLogger();

	private int cacheSize;
	private List<Event> cache;

	public CachedFileEventLogger(@Value("${cacheSize}") int cacheSize, @Value("${loggingFileName}") String fileName)
	{
		super(fileName);
		this.cacheSize = cacheSize;
		cache = new ArrayList<>();
	}

	@Override
	public void logEvent(Event event)
	{
		cache.add(event);
		LOGGER.info(String.format("Added %s event to cache", cache.size()));

		if (cache.size() == cacheSize)
		{
			LOGGER.info("Writing event to file and clear cache...");
			cache.forEach(super::logEvent);
			cache.clear();
		}
	}

	@PreDestroy
	public void flush()
	{
		LOGGER.info("Flush cashed events to file and clear cache...");
		if (!cache.isEmpty())
		{
			cache.forEach(super::logEvent);
		}
	}
}
