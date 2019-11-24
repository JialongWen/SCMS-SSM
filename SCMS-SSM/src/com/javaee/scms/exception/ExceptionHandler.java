package com.javaee.scms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView mv = new ModelAndView();
		exception.printStackTrace();
		if(exception instanceof SystemException){
			StringBuilder message = new StringBuilder();
			message.append("<p>" + exception.getCause().getMessage() + "</p>");
			message.append("<p>请您与管理员联系，您也可以返回<a href='" + request.getContextPath() + "/home'>首页</a></p>");
			mv.addObject("message", message.toString());
			mv.setViewName("syserror");
		}if(exception instanceof BizzException){
			StringBuilder message = new StringBuilder();
			message.append("<p>" + exception.getMessage() + "</p>");
			message.append("<p>请您与管理员联系，您也可以返回<a href='" + request.getContextPath() + "/home'>首页</a></p>");
			mv.addObject("message", message.toString());
			mv.setViewName("bizzerror");
		}else{
			StringBuilder message = new StringBuilder();
			message.append("<p>发生了未知的异常</p>");
			message.append("<p>请您与管理员联系，您也可以返回<a href='" + request.getContextPath() + "/home'>首页</a></p>");
			mv.addObject("message", message.toString());
			mv.setViewName("syserror");
		}
		
		return mv;
	}

}
