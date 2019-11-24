package com.javaee.scms.web;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaee.scms.pojo.User;

@Controller
@RequestMapping(value="/less04")
public class Less04Controller {
	/**
	 * @RequestMapping注解
	 * 可以使用在方法上，必须的：
	 * value属性：指定请求的URL
	 * 
	 * 也可以使用在类上，可选的：
	 * value属性：指定主要的URL分段
	 * 
	 * 如果在类上进行了RequestMapping的注解
	 * 则先匹配类上的URL，然后再来匹配方法上的
	 * 如果在类上没有进行RequestMapping注解
	 * 则只匹配方法上的。
	 * 
	 * method属性：用于指定匹配的请求方法
	 * 取值为RequestMethod的常量
	 * GET:get请求
	 * POST:post请求
	 * 
	 * 通配符：
		?：匹配文件名中的一个字符
		*：匹配文件名中的任意字符
		**： ** 匹配多层路径
	      占位符：
		{XXX}：匹配URL中的某段，XXX为为该段取的名称，在方法中可以使用@pathvariable注解绑定，根据名称获取URL中该段的值。

	 */
	@RequestMapping(value="/testRequestMapping")
	public String testRequestMapping(Model model){
		System.out.println("Test Request Mapping!");
		return "success";
	}
	@RequestMapping(value="/testRequestMappingMethodPost",
			method=RequestMethod.POST,
			params={"username=allen","!password"},
			headers={"Accept-Language=zh-CN,zh;q=0.8"})
	public String testRequestMappingMethodPost(Model model){
		System.out.println("Test Post Request!");
		return "success";
	}
	
	@RequestMapping(value="/**/ant")
	public String testRequestAnt1(Model model){
		System.out.println("Test Request Ant!");
		return "success";
	}
	
	/**
	 * @PathVariable注解
	 * 用于匹配URL路径中的变量
	 * 注释在方法的参数上，将路径中的占位符的值注入给方法的指定参数
	 * 而且会进行简单的类型转换
	 */
	@RequestMapping(value="/**/ant/{page}")
	public String testRequestAnt2(Model model, @PathVariable("page") Integer page){
		System.out.println("Test Request Ant2!");
		System.out.println(page);
		return "success";
	}
	
	
	/**
	 * @RequestParam 注解用于获取请求中的参数，并注入到方法参数中。
		该注解用于方法参数上。
		value属性：用于指定参数名称。
		required属性：用于指定参数是否必须项，true为必须。
		defaultValue属性：用于指定参数的默认值。
	 */
	@RequestMapping(value="/test/request/param")
	public String testRequestParam(Model model, User user){
		System.out.println("Test Request Param!");
		System.out.println(user);
		user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		//userDao.add(user);
		return "success";
	}
	
	@RequestMapping(value="/test/request/api")
	public void testRequestAPI(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Writer out) throws IOException{
		System.out.println("Test Request API!");
		
		String username = request.getParameter("username");
		System.out.println(username);
		session.setAttribute("username", username);
		
		out.write("success");
		
	}
	
	
	
}
