package com.empire.shipmanagement.infraestructure.adapter.in.rest.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class NegativeValueLoggingAspect {

    @Before("execution(* *(.., @com.empire.shipmanagement.infraestructure.adapter.in.rest.aspect.LogIfNegative (*), ..))")
    public void logIfNegativeValue(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Number) {
                Number numberArg = (Number) arg;
                if (numberArg.longValue() < 0) {
                    log.warn("Se intentó introducir un valor negativo durante el comando: {}", joinPoint.getSignature().getName());
                }
            }
        }
    }
}

