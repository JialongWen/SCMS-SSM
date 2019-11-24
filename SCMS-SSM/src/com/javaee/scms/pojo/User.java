package com.javaee.scms.pojo;

import java.sql.Timestamp;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Integer role;
	private Timestamp registerTime;
	public User() {
		super();
	}
	public User(Integer id, String username, String password, Integer role,
			Timestamp registerTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.registerTime = registerTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", role=" + role + ", regsiterTime="
				+ registerTime + "]";
	}
}
