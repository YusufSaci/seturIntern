package com.example.demo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @AfterReturning("execution(* com.example.demo.service.*.delete*(..))")
    public void logDeleteSuccess(JoinPoint joinPoint){

        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
        serviceName = serviceName.replace("Service","");

        Object[] args = joinPoint.getArgs();

        Long id = (Long) args[0];

        log.info("{}: entity with id {} deleted.", serviceName, id);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.demo.service.*.delete*(..))"
            ,throwing = "ex"
    )
    public void logDeleteFailure(JoinPoint joinPoint,RuntimeException ex ){

        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
        serviceName = serviceName.replace("Service","");

        Object[] args = joinPoint.getArgs();

        Long id = (Long) args[0];

        log.error("{}: entity with id {} could not be deleted.", serviceName, id, ex);
    }

}
