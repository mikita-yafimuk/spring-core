package com.epam.spring;

import java.text.DateFormat;
import java.util.Date;

public class Event
{
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
				'}';
	}
}
