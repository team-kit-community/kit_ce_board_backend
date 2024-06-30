package com.creativedesignproject.kumoh_board_backend.Auth.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.creativedesignproject.kumoh_board_backend.Auth.controller.*.*(..))")
    public void loggerBefore() {
        System.out.println("AuthController 함수들 실행 전");
    }

    @After("execution(* com.creativedesignproject.kumoh_board_backend.Auth.controller.*.*(..))")
    public void loggerAfter() {
        System.out.println("AuthController 함수들 실행 후");
    }

    @Around("execution(* com.creativedesignproject.kumoh_board_backend.Auth.controller.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beforeTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행 시작 : "
                + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();

        long afterTimeMillis = System.currentTimeMillis() - beforeTimeMillis;
        System.out.println("[UserController] 실행 완료: " + afterTimeMillis + "밀리초 소요"
                + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        return result;
    }
}
