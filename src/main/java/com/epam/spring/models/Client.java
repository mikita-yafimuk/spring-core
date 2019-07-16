package com.epam.spring.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client
{
	private String id;
	private String fullName;
	@Value("${greeting}")
	private String greeting;

	public Client(@Value("${id}") String id,@Value("${fullName}") String fullName)
	{
		this.id = id;
		this.fullName = fullName;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getId()
	{
		return id;
	}

	public String getFullName()
	{
		return fullName;
	}

	public String getGreeting()
	{
		return greeting;
	}

	public void setGreeting(String greeting)
	{
		this.greeting = greeting;
	}
}
