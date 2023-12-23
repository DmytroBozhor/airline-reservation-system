package com.dmytrobozhor.airlinereservationservice.util.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class ControllerPointcutHolder implements PointcutHolder{
    @Override
    @Pointcut("within(com.dmytrobozhor.airlinereservationservice.web.controller..*)")
    public void withinPackage() {
    }
}
