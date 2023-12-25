package com.dmytrobozhor.airlinereservationservice.util.aop.logging.controller;

import com.dmytrobozhor.airlinereservationservice.util.aop.AbstractPointcutHolder;
import org.aspectj.lang.annotation.Pointcut;

public class ControllerPointcutHolder implements AbstractPointcutHolder {

    @Override
    @Pointcut(value = "within(com.dmytrobozhor.airlinereservationservice.web.controller..*)")
    public void withinPath() {
    }

    @Pointcut(value = "anyPublicMethod() && withinPath()")
    public void anyPublicMethodWithinPath() {
    }

}
