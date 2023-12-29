package com.dmytrobozhor.airlinereservationservice.util.aop.logging.controller;

import com.dmytrobozhor.airlinereservationservice.util.aop.AbstractLoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect extends AbstractLoggingAspect {

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

    /*@Override
    @AfterThrowing(value = "ControllerPointcutHolder.anyPublicMethodWithinPath()",
            throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        super.logAfterThrowingAdvice(joinPoint, throwable);
    }*/

}
