package com.dmytrobozhor.airlinereservationservice.util.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class GeneralPointcutHolder {

    @Pointcut(value = "execution(public * *(..))")
    public void anyPubicMethod() {
    }

    @Pointcut(value = "within(com.dmytrobozhor.airlinereservationservice.service..* " +
            "|| com.dmytrobozhor.airlinereservationservice.web.controller..*)")
    public void withinPackage() {
    }

    @Pointcut(value = "anyPubicMethod() && withinPackage()")
    public void anyPublicMethodWithinPackage() {
    }

}
