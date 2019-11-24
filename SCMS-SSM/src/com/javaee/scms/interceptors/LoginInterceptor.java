package com.javaee.scms.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录检查拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	public static List<String> URLS = null;
	static{
		URLS = new ArrayList<String>();
		URLS.add("/codes");
		URLS.add("/admin");
		URLS.add("/code");
	}
	/**
	 * 执行请求之前进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		String method = request.getMethod();
		if(method.equalsIgnoreCase("GET") && URLS.contains(url)){
			if(request.getSession().getAttribute("loginUser") == null){
				//未登录状态
				//重定向到未登录错误页面
				response.sendRedirect("not_login");
				return false;
			}else{
				//已登录
				return true;
			}
		}else{
			return true;
		}
	}
	
}
