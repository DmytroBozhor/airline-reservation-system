package com.dmytrobozhor.airlinereservationservice.util.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    private LocalDateTime start;

    @Before(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.ServicePointcutHolder.allServiceMethod()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        start = LocalDateTime.now();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.debug("Service: " + methodName + " - start. Args count - " + args.length);
    }

    @AfterReturning(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.ServicePointcutHolder.allServiceMethod()")
    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        long result = Duration.between(LocalDateTime.now(), start).toMillis();
        log.debug("Service: " + methodName + " - end. Execution time: " + result);
    }

    @AfterThrowing(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.ServicePointcutHolder.allServiceMethod()",
            throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        log.debug("Service: " + methodName + " - throws exception.", throwable);
    }

}
