package com.shadow.OptiBankBuddy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BankingLogAspect {

 private static final Logger logger = LoggerFactory.getLogger(BankingLogAspect.class);
 
 
   @Pointcut("execution(* com.shadow.OptiBankBuddy.service.*(..))" )
   public void serviceLayer() {}
   
   @Pointcut("execution(* com.shadow.OptiBankBuddy.controller.*(..))" )
   public void controllerLayer() {}
   
   
   @Before("serviceLayer() || controllerLayer()")
	public void logBeforeMethodExecution(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Executing method: " + methodName);
	}
   
   @AfterReturning("serviceLayer() || controllerLayer()")
	public void logAfterMethodExecution(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Method executed: " + methodName);
	}
   
   @AfterThrowing(pointcut = "serviceLayer() || controllerLayer()", throwing = "error")
	public void logAfterMethodException(JoinPoint joinPoint, Exception error) {
		String methodName = joinPoint.getSignature().getName();
		logger.error("Exception in method: " + methodName + " with message: " + error.getMessage());
	}
 
		
}
