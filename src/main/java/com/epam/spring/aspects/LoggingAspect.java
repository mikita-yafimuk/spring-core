package com.epam.spring.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{
	public static final Logger LOGGER = LogManager.getLogger();

	@Pointcut("execution(* *.logEvent(..))")
	private void allLogEventMethods()
	{

	}

	@Pointcut("allLogEventMethods() && within(*.*.*.*.File*Logger)")
	private void fileLoggerLogEventMethods()
	{

	}

	@Before("allLogEventMethods()")
	private void logBefore(JoinPoint joinPoint)
	{
		LOGGER.info("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName() +
				" " + joinPoint.getSignature().getName());
	}

	@Before("fileLoggerLogEventMethods()")
	private void logBeforeFileLogger(JoinPoint joinPoint)
	{
		LOGGER.info("BEFORE File Event Logger: " + joinPoint.getTarget().getClass().getSimpleName() +
				" " + joinPoint.getSignature().getName());
	}
}
