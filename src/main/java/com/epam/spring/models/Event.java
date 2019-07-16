package com.epam.spring.models;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Event
{
	public static final Logger LOGGER = LogManager.getLogger();

	private int id;
	private String message;
	private Date date;
	private DateFormat dateFormat;

	public Event(Date date, DateFormat dateFormat)
	{
		this.date = date;
		this.dateFormat = dateFormat;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "Event{" +
				"id=" + id +
				", message='" + message + '\'' +
				", date=" + dateFormat.format(date) +
				"}\n";
	}

	public static boolean isDay()
	{
		int hourOfDay = LocalTime.now().getHour();
		LOGGER.debug("Hour of the day: " + hourOfDay);
		return hourOfDay >= 8 && hourOfDay <= 17;
	}
}
