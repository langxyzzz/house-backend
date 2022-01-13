package com.langxy.house.annotation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆认证 aop
 *
 * @author langxy
 */
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
            String token = request.getHeader("token");
            if (token == null) {
                throw new RuntimeException("无token, 请重新登陆");
            }
            String userId = "";
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException e) {
                throw new RuntimeException("401");
            }

            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userId)).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
        }
        return joinPoint.proceed();
    }

    @AfterReturning("annotationPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {

    }
}
