package com.dmytrobozhor.airlinereservationservice.util.aop;

import org.aspectj.lang.annotation.Pointcut;

public interface AbstractPointcutHolder {

    @Pointcut(value = "execution(public * *(..))")
    default void anyPublicMethod() {
    }

    void withinPath();

}
