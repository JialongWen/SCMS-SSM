package com.javaee.scms.web;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.scms.exception.SystemException;
import com.javaee.scms.pojo.User;
import com.javaee.scms.service.UserService;

@Controller
@RequestMapping("/less06")
public class Less06Controller {
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView list() throws SystemException{
		//查询全部用户操作，跳转到list页面
		ModelAndView mv = new ModelAndView();
		UserService userService = new UserService();
		List<User> userlist;
		try {
			userlist = userService.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		
		mv.setViewName("list");
		mv.addObject("userlist", userlist);
		return mv;
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String read(){
		//跳转到新增输入页面
		return "input";
	}
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String create(User newUser) throws SystemException{
		//执行新增操作，重定向到查询全部/users
		System.out.println(newUser);
		UserService userService = new UserService();
		newUser.setRole(2);
		newUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		try {
			userService.register(newUser);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return "redirect:/less06/users";
	}
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Integer id, Map<String, Object> map) throws SystemException{
		//跳转到修改输入页面
		UserService userService = new UserService();
		User user;
		try {
			user = userService.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		map.put("user", user);
		return "edit";
	}
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public String update(User user) throws SystemException{
		//执行修改操作，重定向到查询全部/users
		System.out.println(user);
		UserService userService = new UserService();
		try {
			userService.edit(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return "redirect:/less06/users";
	}
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) throws SystemException{
		//执行删除操作
		UserService userService = new UserService();
		try {
			userService.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return "redirect:/less06/users";
	}
}
