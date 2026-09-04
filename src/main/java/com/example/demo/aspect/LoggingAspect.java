package com.example.demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning("execution(* com.example.demo.service.*.delete*(..))")
    public void logDeleteSuccess(JoinPoint joinPoint){

        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
        serviceName = serviceName.replace("Service","");

        Object[] args = joinPoint.getArgs();

        Long id = (Long) args[0];

        System.out.println(serviceName + ": entity with id " + id + " deleted.");
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

        System.out.println(serviceName + ": entity with id " + id +
                " could not be deleted. Error: " + ex.getMessage());
    }

}
