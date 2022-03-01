package com.reposlayers.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("within(com.project2.controllers.*)")
    public void hookMethod(){
    }

    @Before("hookMethod")
    public void logBefore() {
        logger.info("This is from the BEFORE advice method in our Logging Aspect");
    }
    @After(value = "hookMethod()", argNames = "joinPoint")
    public void logAfter(JoinPoint joinPoint){
        logger.info(joinPoint.getSignature().getName() + "finishing execution");
    }
    @AfterReturning(value = "hookMethod()")
    public void logAfterReturning(){
        logger.warn("this will only happen after a method returns.");
    }
    @AfterThrowing("hookMethod()")
    public void logAfterThrowing(){
        logger.error("This only happens after a method throws an exception.");
    }
    @Around("hookMethod()")
    public Object aroundEvereything(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("This Starts before the method, then we can tell it to proceed");
        pjp.proceed();
        System.out.println("... and it continues after the method as well");
        return null;
    }

}
