package net.zoungrana.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityAspect {
    @Before("execution(* net.zoungrana.model.NeuralNetwork.fit(..))"
            + " || execution(* net.zoungrana.model.NeuralNetwork.stop(..))"
            + " || execution(* net.zoungrana.model.NeuralNetwork.train(..))"
            + " || execution(* net.zoungrana.model.NeuralNetwork.predict(..))"
            + " || execution(* net.zoungrana.model.NeuralNetwork.decorateLayer(..))")
    public void requireOperator(JoinPoint point) {
        if (NetworkAccess.currentRole() != NetworkAccess.Role.OPERATOR) {
            throw new SecurityException("Accès refusé à " + point.getSignature().getName()
                    + "() : rôle OPERATOR requis.");
        }
    }
}
