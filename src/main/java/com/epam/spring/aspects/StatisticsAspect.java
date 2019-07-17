package com.epam.spring.aspects;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsAspect
{
	public static final Logger LOGGER = LogManager.getLogger();
	private Map<Class<?>, Integer> counter = new HashMap<>();

	@AfterReturning("execution(* *.logEvent(..))")
	private void count(JoinPoint joinPoint)
	{
		Class<?> clazz = joinPoint.getTarget().getClass();
		if (!counter.containsKey(clazz))
		{
			counter.put(clazz, 0);
		}
		counter.put(clazz, counter.get(clazz) + 1);
	}

	@PreDestroy
	private void printCounter()
	{
		LOGGER.info("Counter: " + counter);
	}
}
