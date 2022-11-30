package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class CurrencyServiceAspect {
    @Pointcut("within(com.demo.service.*)")
    public void withinCurrencyServicePointCut() {}

    @Pointcut("@target(com.demo.annotation.Secured)")
    public void targetAnnotationCurrencyServicePointCut(){}

    @Pointcut("@within(com.demo.annotation.Secured)")
    public void withinAnnotationCurrencyServicePointCut() {}

    @Pointcut("@annotation(com.demo.annotation.Secured)")
    public void annotationCurrencyServicePointCut() {}

    @Pointcut("bean(currency)")
    public void beanCurrencyServicePointcut(){}

    @Pointcut("args(int,int)")
    public void argsCurrencyServicePointcut(){}

    @Pointcut("@args(com.demo.annotation.Validated)")
    public void argsAnnotationCurrencyServicePointcut(){}

//    @Before("withinCurrencyServicePointCut()")
    public void beforeCurrencyAdvice(JoinPoint joinPoint) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter before advice in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
    }

    //@Before("withinAnnotationCurrencyServicePointCut()")
    public void beforeWithinAnnotationCurrencyAdvice(JoinPoint joinPoint) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter before advice in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
    }

    //@Before("withinAnnotationCurrencyServicePointCut() && argsCurrencyServicePointcut()")
    public void argsBeanCurrencyAdvice(JoinPoint joinPoint) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter before advice in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
    }

    //@After("argsAnnotationCurrencyServicePointcut()")
    /*public void argsAnnotationCurrencyAdvice(JoinPoint joinPoint) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter after advice in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
    }*/

    //@AfterReturning(value = "argsAnnotationCurrencyServicePointcut()", returning = "country")
    public void argsAnnotationAfterReturningCurrencyAdvice(JoinPoint joinPoint, String country) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter after returning"+
                        "advice return value:: [%s] in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()),country, LocalDateTime.now())
        );
    }

    //@AfterThrowing(value = "argsAnnotationCurrencyServicePointcut()", throwing = "e")
    public void argsAnnotationAfterThrowingCurrencyAdvice(JoinPoint joinPoint, Throwable e) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter after throwing"+
                                "advice exception class is:: [%s] in [%s].",
                        className, joinPoint.getSignature().getName(),
                        Arrays.toString(joinPoint.getArgs()),
                        e.getClass().getSimpleName(),
                        LocalDateTime.now())
        );
    }

    @Around("targetAnnotationCurrencyServicePointCut()")
    public Object argsAnnotationCurrencyAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(
                String.format("%s'%s method is invoked with %s parameter before advice in [%s].",
                        className, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
        try {
            return joinPoint.proceed();
        }finally {
            System.out.println("After invoking method ......");
        }
    }


}
