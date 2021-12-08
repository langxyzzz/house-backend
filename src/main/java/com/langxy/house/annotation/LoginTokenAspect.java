package com.langxy.house.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LoginTokenAspect {

    public static final String TOKEN_KEY = "token";

    @Pointcut("@annotation(com.langxy.house.annotation.LoginToken)")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void beforePointCut(JoinPoint joinPoint) {

    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();
        }

        return joinPoint.proceed();
    }

    @AfterReturning("annotationPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {

    }
}
