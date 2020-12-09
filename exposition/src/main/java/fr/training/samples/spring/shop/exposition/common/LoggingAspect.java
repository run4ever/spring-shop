package fr.training.samples.spring.shop.exposition.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger Logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * fr.training.samples.spring.shop.application..*.*(..))")
    private void monPointCut(){

    }

    @Before("monPointCut()")
    public void log(final JoinPoint jp){
        Logger.info(jp.getSignature().toLongString());
        final Object[] args = jp.getArgs();
        final StringBuilder sb = new StringBuilder();
        for(final Object object : args){
            sb.append(object).append(",");
        }
        Logger.info("Arguments de la méthode "+sb.toString());
    }

    @Around("monPointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        Logger.info("Elapse time " + (end - start));
        return obj;
    }


}
