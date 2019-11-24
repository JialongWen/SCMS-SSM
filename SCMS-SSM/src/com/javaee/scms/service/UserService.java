package com.javaee.scms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.javaee.scms.exception.BizzException;
import com.javaee.scms.mapper.UserMapper;
import com.javaee.scms.pojo.User;

@Component("userService")
public class UserService {
	@Autowired
	@Qualifier("userMapper")
	UserMapper userDao;
	public Map<String, Object> isLogin(User loginUser) throws SQLException {
		//登录的业务逻辑
		Map<String, Object> result = new HashMap<String, Object>();
		
		//从数据库查询出所有的User对象
		//UserDao userDao = new UserDao();
		List<User> userlist = userDao.findAllUser();
		//遍历所有的User对象，逐一比对 username和password
		
		Boolean isLogin = false;
		String message = null;
		
		for(User user : userlist){
			//如果username相符，则比对password
			if(user.getUsername().equals(loginUser.getUsername())){
				//如果password相符，则允许登录
				if(user.getPassword().equals(loginUser.getPassword())){
					isLogin = true;
					loginUser = user;
					break;
				}else{
				//如果password不相符，则不允许登录，密码错误
					isLogin = false;
					message = "密码错误！";
					break;
				}
			}
		}
		//如果全部比对完也没有相符的，则不允许登录
		if(isLogin==false&&message==null){
			message = "用户名错误！";
		}
		
		result.put("isLogin", isLogin);
		result.put("message", message);
		result.put("loginUser", loginUser);
		
		return result;
	}

	public Map<String, Object> changePassword(String oldpassword, User user, User loginUser) throws SQLException, BizzException {
		Map<String, Object> result = new HashMap<String, Object>();
		
		//将原密码（表单填写的）oldpassword 与旧密码（数据库中的）loginUser.getPassword()进行比对
		if(oldpassword.equals(loginUser.getPassword())){
			//一致：将入参的user更新到数据库中，去成功页面，显示成功信息
			
			//UserDao userDao = new UserDao();
			userDao.update(user);

			result.put("isSuccess", true);
			result.put("message", "修改密码成功！");
		}else{
			//否则：有错误信息，去失败页面，显示失败信息
			/*result.put("isSuccess", false);
			result.put("message", "原密码不正确，拒绝修改密码！");*/
			throw new BizzException("原密码不正确，拒绝修改密码！");
		}
		
		return result;
	}

	public User findById(Integer id) throws SQLException {
		//UserDao userDao = new UserDao();
		
		return userDao.findById(id);
	}

	public Boolean isUsernameExsist(String username) throws SQLException {
		//UserDao userDao = new UserDao();
		List<User> userlist = userDao.findByUsername(username);
		if(userlist.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	public Map<String, Object> register(User registerUser) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		//UserDao userDao = new UserDao();
		userDao.add(registerUser);
		result.put("isSuccess", true);
		result.put("message", registerUser.getUsername() + " 恭喜您，注册成功！");
		
		return result;
	}

	public List<User> queryAll() throws SQLException {
		//UserDao userDao = new UserDao();
		return userDao.findAllUser();
	}

	public void edit(User user) throws SQLException {
		//UserDao userDao = new UserDao();
		userDao.update(user);
	}

	public void delete(Integer id) throws SQLException {
		//UserDao userDao = new UserDao();
		userDao.delete(id);
	}


}
