package com.javaee.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


/**
 * 经纪人（通知 Advice）
 */
@Aspect
class Agent{
	
	@Before("execution(* com.javaee.aop.*.sing(..))")
	public void sign(JoinPoint joinPoint){
		//joinPoint.getTarget() 获取目标对象
		System.out.println(joinPoint.getTarget());
		//joinPoint.getSignature() 获取切点方法
		System.out.println(joinPoint.getSignature());
		
		System.out.println("签合同....");
	}
	
	@After("execution(* com.javaee.aop.*.sing(..))")
	public void collect(JoinPoint joinPoint){
		System.out.println("结账......");
	}
	
	@Around("execution(* com.javaee.aop.*.sing(..))")
	public void around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("化妆.....");
		pjp.proceed();
		System.out.println("卸妆.....");
	}
}
