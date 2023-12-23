package com.dmytrobozhor.airlinereservationservice.util.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class ServicePointcutHolder implements PointcutHolder {

    @Pointcut(value = "execution(public * *(..))")
    public void anyPubicMethodPointcut() {
    }

    @Override
    @Pointcut(value = "within(com.dmytrobozhor.airlinereservationservice.service..*)")
    public void withinPackage() {
    }

    @Pointcut(value = "anyPubicMethodPointcut() && withinPackage()")
    public void allServiceMethod() {
    }

}
