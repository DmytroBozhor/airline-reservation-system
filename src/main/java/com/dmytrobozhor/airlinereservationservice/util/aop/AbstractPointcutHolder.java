package com.dmytrobozhor.airlinereservationservice.util.aop;

import org.aspectj.lang.annotation.Pointcut;

public interface AbstractPointcutHolder {

    @Pointcut(value = "execution(public * *(..))")
    default void anyPubicMethod() {
    }

    @Pointcut(value = "execution(private * *(..))")
    default void anyPrivateMethod() {
    }

    @Pointcut(value = "anyPubicMethod() && withinPath()")
    default void anyPublicMethodWithinPath() {
    }

    @Pointcut(value = "anyPrivateMethod() && withinPath()")
    default void anyPrivateMethodWithinPath() {
    }

    @Pointcut
    void withinPath();

}
