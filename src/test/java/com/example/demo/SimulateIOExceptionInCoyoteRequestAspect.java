package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@Aspect
public class SimulateIOExceptionInCoyoteRequestAspect {

    private static final Logger log = getLogger(SimulateIOExceptionInCoyoteRequestAspect.class);

    @Around("execution(* org.apache.coyote.Request.doRead(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var request = (org.apache.coyote.Request) joinPoint.getTarget();
        log.info("Throwing IOException from SimulateIOExceptionInCoyoteRequestAspect");
        throw new java.io.IOException("Simulated IOException from CoyoteRequestAspect handling uri " + request.requestURI() + ")");
    }

}
