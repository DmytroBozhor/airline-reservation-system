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
public class LoggingAspect {

    private LocalDateTime start;

    @Before(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.GeneralPointcutHolder.anyPublicMethodWithinPackage()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        start = LocalDateTime.now();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        log.debug(className + ": " + methodName + " - start. Args count: " + args.length);
    }

    @AfterReturning(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.GeneralPointcutHolder.anyPublicMethodWithinPackage()")
    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        long result = Duration.between(start, LocalDateTime.now()).toMillis();
        log.debug(className + ": " + methodName + " - end. Execution time: " + result);
    }

    @AfterThrowing(value = "com.dmytrobozhor.airlinereservationservice.util" +
            ".aspects.pointcuts.GeneralPointcutHolder.anyPublicMethodWithinPackage()",
            throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        log.debug(className + ": " + methodName + " - throws exception.", throwable);
    }

}
