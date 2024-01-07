package com.dmytrobozhor.airlinereservationservice.util.aop.logging;

import com.dmytrobozhor.airlinereservationservice.util.aop.AbstractLoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggingAspect extends AbstractLoggingAspect {

    @Override
    @Before(value = "ServicePointcutHolder.anyPublicMethodWithinPath()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        super.logBeforeAdvice(joinPoint);
    }

    @Override
    @AfterReturning(value = "ServicePointcutHolder.anyPublicMethodWithinPath()")
    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        super.logAfterReturningAdvice(joinPoint);
    }

    /*@Override
    @AfterThrowing(value = "ServicePointcutHolder.anyPublicMethodWithinPath()", throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        super.logAfterThrowingAdvice(joinPoint, throwable);
    }*/

}
