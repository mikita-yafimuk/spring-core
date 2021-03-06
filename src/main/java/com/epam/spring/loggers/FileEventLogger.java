package com.epam.spring.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.spring.models.Event;

public class FileEventLogger implements EventLogger
{
	public static final Logger LOGGER = LogManager.getLogger();

	private String fileName;
	private File file;

	public FileEventLogger(String fileName)
	{
		this.fileName = fileName;
	}

	public void init() throws IOException
	{
		this.file = new File("src/main/resources/" + fileName);
		if (!file.canWrite())
		{
			LOGGER.fatal("Can't write to file");
			throw new IOException();
		}
	}

	@Override
	public void logEvent(Event event)
	{
		try
		{
//			Files.write(Paths.get("src/main/resources/" + fileName), event.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
			FileUtils.writeStringToFile(file, event.toString(), "UTF-8", true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
