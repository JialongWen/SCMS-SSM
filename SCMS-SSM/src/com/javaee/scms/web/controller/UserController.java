package com.javaee.scms.web.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.scms.annotation.LoggerMessage;
import com.javaee.scms.exception.BizzException;
import com.javaee.scms.exception.SystemException;
import com.javaee.scms.pojo.User;
import com.javaee.scms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@RequestMapping("/login")
	@LoggerMessage(message = "用户登录")
	public String login(Model model, User loginUser, HttpSession session) throws SystemException{
		/* 登录的控制逻辑 */
		System.out.println(loginUser);
		
		//UserService service = new UserService();
		
		Map<String, Object> result;
		try {
			result = userService.isLogin(loginUser);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		
		Boolean isLogin = (Boolean)result.get("isLogin");
		
		session.removeAttribute("message");
		
		//允许登录
		if(isLogin){
			loginUser = (User)result.get("loginUser");
			session.setAttribute("loginUser", loginUser);
			return "redirect:/home";
		//不允许登录
		}else{
			String message = (String)result.get("message");
			session.setAttribute("message", message);
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping("/logout")
	@LoggerMessage(message = "退出登录")
	public String logout(Model model, HttpSession session){
		session.removeAttribute("loginUser");
		session.removeAttribute("message");
		return "redirect:/home";
	}
	
	@ModelAttribute
	public void getUser(User user, Map<String, Object> map) throws SystemException{
		//去数据库取出对应id的user对象
		if(user.getId() != null){
			//去数据库取user
			//UserService userService = new UserService();
			try {
				user = userService.findById(user.getId());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SystemException(e);
			}
		}
		map.put("user", user);
	}
	
	@RequestMapping("/changePassword")
	@LoggerMessage(message = "修改密码")
	public ModelAndView changePassword(@ModelAttribute("user") User user, HttpSession session, 
			@RequestParam("oldpassword") String oldpassword) throws SystemException, BizzException{
		ModelAndView mv = new ModelAndView();
		
		User loginUser = (User)session.getAttribute("loginUser");
		
		//先从数据库取出对应id的user对象，作为入参
		//入参的user对象的password值来自于表单填写的新密码
		
		//UserService userService = new UserService();
		Map<String, Object> result;
		try {
			result = userService.changePassword(oldpassword, user, loginUser);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (BizzException e) {
			e.printStackTrace();
			throw e;
		}
		Boolean isSuccess = (Boolean)result.get("isSuccess");
		String message = (String)result.get("message");
		mv.addObject("message", message);
		if(isSuccess){
			mv.setViewName("bizzdone");
		}else{
			mv.setViewName("bizzerror");
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/exsist/{username}")
	public Map<String, Object> isUsernameExsit(@PathVariable("username") String username) throws SystemException{
		System.out.println(username);
		Map<String, Object> result = new HashMap<String, Object>();
		//UserService userService = new UserService();
				
		try {
			result.put("isExsist", userService.isUsernameExsist(username));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return result;
	}
	
	@RequestMapping("/register")
	@LoggerMessage(message = "用户注册")
	public ModelAndView register(User registerUser) throws SystemException{
		ModelAndView mv = new ModelAndView();
		registerUser.setRole(2);
		registerUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		
		//UserService userService = new UserService();
		Map<String, Object> result;
		try {
			result = userService.register(registerUser);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		Boolean isSuccess = (Boolean)result.get("isSuccess");
		String message = (String)result.get("message");
		if(isSuccess){
			mv.setViewName("bizzdone");
		}else{
			mv.setViewName("bizzerror");
		}
		mv.addObject("message", message);
		
		return mv;
	}
}
