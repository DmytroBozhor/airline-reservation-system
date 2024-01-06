package com.dmytrobozhor.airlinereservationservice.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class AbstractLoggingAspect {

    private LocalDateTime start;

    public void logBeforeAdvice(JoinPoint joinPoint) {
        start = LocalDateTime.now();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        log.debug(LogColorConstants.ANSI_GREEN + className + ": "
                + methodName + " - start. Args count: " + args.length + LogColorConstants.ANSI_RESET);
    }

    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        long result = Duration.between(start, LocalDateTime.now()).toMillis();
        log.debug(LogColorConstants.ANSI_BLUE + className + ": "
                + methodName + " - end. Execution time: " + result + LogColorConstants.ANSI_RESET);
    }

    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        log.debug(LogColorConstants.ANSI_CYAN + className + ": "
                + methodName + " - throws {} exception." +
                LogColorConstants.ANSI_RESET, throwable.getClass().getSimpleName());
    }

}
