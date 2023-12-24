package com.dmytrobozhor.airlinereservationservice.util.aop.logging.service;

import com.dmytrobozhor.airlinereservationservice.util.aop.AbstractPointcutHolder;
import org.aspectj.lang.annotation.Pointcut;

public class ServicePointcutHolder implements AbstractPointcutHolder {
    @Override
    @Pointcut(value = "within(com.dmytrobozhor.airlinereservationservice.service..*)")
    public void withinPath() {
    }
}
