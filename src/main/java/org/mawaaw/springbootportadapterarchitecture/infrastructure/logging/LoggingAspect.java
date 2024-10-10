package org.mawaaw.springbootportadapterarchitecture.infrastructure.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Logger pour l'aspect de logging
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut pour les méthodes dans la couche application.service
    @Around("execution(* org.mawaaw.springbootportadapterarchitecture.application.service.*.*(..))")
    public Object logAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        logger.debug("Entrée dans la méthode : {}.{} avec les arguments : {}", className, methodName, joinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;
            logger.debug("Sortie de la méthode : {}.{} avec le résultat : {} (Temps d'exécution : {} ms)", className, methodName, result, timeTaken);
            return result;
        } catch (Throwable throwable) {
            long timeTaken = System.currentTimeMillis() - startTime;
            logger.error("Exception dans la méthode : {}.{} (Temps d'exécution : {} ms) - Exception : {}", className, methodName, timeTaken, throwable.getMessage(), throwable);
            throw throwable;
        }
    }
}