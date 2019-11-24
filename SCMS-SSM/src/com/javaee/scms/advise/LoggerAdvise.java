package com.javaee.scms.advise;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.javaee.scms.annotation.LoggerMessage;
import com.javaee.scms.pojo.User;

@Component("loggerAdvise")
@Aspect
public class LoggerAdvise {
	
	public static Logger logger = Logger.getLogger(LoggerAdvise.class);
	
	@Around("execution(* com.javaee.scms.web.controller.*.*(..))")
	public Object logger(ProceedingJoinPoint pjp) throws Throwable{
		String message = getMessage(pjp);
		String username = null;
		String methodName = pjp.getSignature().getName();
		Object obj = null;
		if(methodName.equals("logout")){
			//before
			username = getUserName();
			obj = pjp.proceed();
		}else{
			//after
			obj = pjp.proceed();
			username = getUserName();
		}
		if(message != null){
			if(username == null){
				username = "";
			}
			//System.out.println(username + " " + message);
			logger.info(username + " " + message);
		}
		return obj;
	}

	private String getUserName() {
		//session.getAttribute("loginUser");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser != null){
			return loginUser.getUsername();
		}
		return null;
	}

	private String getMessage(JoinPoint jp) {
		
		Signature st = jp.getSignature();
		String methodname = st.getName();
		
		for(Method method : st.getDeclaringType().getMethods()){
			if(method.getName().equals(methodname)){
				if(method.isAnnotationPresent(LoggerMessage.class)){
					LoggerMessage loggerMessage = method.getAnnotation(LoggerMessage.class);
					return loggerMessage.message();
				}
			}
		}
		return null;
	}
}
