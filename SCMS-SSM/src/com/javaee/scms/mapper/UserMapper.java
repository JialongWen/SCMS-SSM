package com.javaee.scms.mapper;

import java.sql.SQLException;
import java.util.List;

import com.javaee.scms.pojo.User;

public interface UserMapper {
	public List<User> findAllUser() throws SQLException;
	public void update(User user) throws SQLException;
	public User findById(Integer id) throws SQLException;
	public List<User> findByUsername(String username) throws SQLException;
	public void add(User registerUser) throws SQLException;
	public void delete(Integer id) throws SQLException;
}
