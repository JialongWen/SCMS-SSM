package com.javaee.aop;
/**
 * 目标（Target）
 */
class Singer implements Singable{
	String name;

	public void setName(String name) {
		this.name = name;
	}

	public Singer() {
		super();
	}

	public Singer(String name) {
		super();
		this.name = name;
	}
	
	/* 业务方法 切点 pointcut */
	public void sing(){
		System.out.println(this.name + "唱歌......");
	}
}
