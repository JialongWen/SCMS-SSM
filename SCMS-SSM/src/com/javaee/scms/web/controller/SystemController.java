package com.javaee.scms.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {
	@RequestMapping("/{page}")
	public String home(Model model, @PathVariable("page") String page, HttpServletRequest request){
		System.out.println(request.getServletPath());
		return page;
	}
}
