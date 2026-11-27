package net.zoungrana.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclarePrecedence;

@Aspect
@DeclarePrecedence("net.zoungrana.aspect.LoggingAspect, net.zoungrana.aspect.SecurityAspect")
public class LoggingAspect {
    @Before("execution(* net.zoungrana..*(..)) && !within(net.zoungrana.aspect..*)")
    public void trace(JoinPoint point) {
        if (Boolean.getBoolean("network.trace")) {
            System.out.println("[TRACE] " + point.getSignature().toShortString());
        }
    }
}
