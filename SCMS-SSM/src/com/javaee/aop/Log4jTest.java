package com.javaee.aop;

import org.apache.log4j.Logger;

public class Log4jTest {
	public static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		logger.info("test test test");
	}
}
