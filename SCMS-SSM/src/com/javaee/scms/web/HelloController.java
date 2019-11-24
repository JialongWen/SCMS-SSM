package com.javaee.scms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller注解
 * 标识一个类为SpringMVC的控制器类
 */
@Controller
public class HelloController {
	
	/**
	 * @RequestMapping注解
	 * 标识一个请求URL和方法之间的映射关系
	 * 当该URL的请求到来时，SpringMVC将调用该方法来处理这个请求
	 */
	@RequestMapping(value="/hello")
	public String hello(Model model){
		model.addAttribute("msg", "Spring MVC 你好！");
		return "success";
	}
}
