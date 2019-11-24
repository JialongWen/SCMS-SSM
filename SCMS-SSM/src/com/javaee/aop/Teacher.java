package com.javaee.aop;
/**
 * 目标 Target
 */
public class Teacher implements Singable{
	String name;

	public void setName(String name) {
		this.name = name;
	}

	public Teacher() {
		super();
	}

	/* 切点 pointcut */
	@Override
	public void sing() {
		System.out.println(this.name + "老师唱歌");
	}
	
}
