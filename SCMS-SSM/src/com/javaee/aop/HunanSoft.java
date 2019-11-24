package com.javaee.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class HunanSoft {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		
		Singable jay = (Singable)context.getBean("jay");
		jay.sing();
		
		Singable zuo = (Singable)context.getBean("zuo");
		zuo.sing();
	}
}
