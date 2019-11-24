package com.javaee.scms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineNumListener implements HttpSessionListener{
	/**
	 * 监听Session创建事件执行的方法
	 * @event session的创建事件
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		/* Session创建时，application中的onlineNum加1*/
		ServletContext application = event.getSession().getServletContext();
		Integer onlineNum = (Integer)application.getAttribute("onlineNum");
		if(onlineNum == null){
			//这是session第1次被创建
			application.setAttribute("onlineNum", 1);
		}else{
			application.setAttribute("onlineNum", (onlineNum + 1)); 
		}
	}

	/**
	 * 监听Session销毁事件执行的方法
	 * @event Session的销毁事件
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		/* Session销毁时，application中的onlineNum减1*/
		ServletContext application = event.getSession().getServletContext();
		Integer onlineNum = (Integer)application.getAttribute("onlineNum");
		if(onlineNum > 0){
			application.setAttribute("onlineNum", (onlineNum - 1));
		}
	}

}
