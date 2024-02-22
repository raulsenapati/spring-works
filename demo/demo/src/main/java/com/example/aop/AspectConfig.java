package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author rahul
 */
@Slf4j
@Configuration
@Aspect
public class AspectConfig {

    /**
     * API with one param: /demo/api/student/getByFirstNameIn
     */
/* --Applies to all controllers and any number of parameters
 @Before(value = "execution(* com.example.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Inside Before Advice");
    }

    @Before(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    public void beforeAdvice(JoinPoint joinPoint, Object object) {
        log.info("Before Advice Request = {}", object);
    }

    @After(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    public void afterAdvice(JoinPoint joinPoint, Object object) {
        log.info("After Advice Request = {}", object);
    }

    @AfterReturning(value = "execution(* com.example.controller.*.*(..)) and args(object)",
            returning = "returningObject")
    public void afterReturningAdvice(JoinPoint joinPoint, Object object, Object returningObject) {
        log.info("After Returning Advice Request = {}, Response = {}", object, returningObject);
    }
*/
    @Around(value = "execution(* com.example.controller.*.*(..)) and args(object)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
        log.info("Around Advice Request = {}", object);
        Object returningObject = null;
        try {
            returningObject = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("Response = {}", returningObject);
        return returningObject;
    }
}
