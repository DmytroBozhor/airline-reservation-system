package com.dmytrobozhor.airlinereservationservice.util.aop.logging.controller;

import com.dmytrobozhor.airlinereservationservice.util.aop.LoggingAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect extends LoggingAspect {

    @Override
    @Before(value = "ControllerPointcutHolder.anyPublicMethodWithinPath()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        super.logBeforeAdvice(joinPoint);
    }

    @Override
    @AfterReturning(value = "ControllerPointcutHolder.anyPublicMethodWithinPath()")
    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        super.logAfterReturningAdvice(joinPoint);
    }

    @Override
    @AfterThrowing(value = "ControllerPointcutHolder.anyPublicMethodWithinPath()", throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        super.logAfterThrowingAdvice(joinPoint, throwable);
    }
}
