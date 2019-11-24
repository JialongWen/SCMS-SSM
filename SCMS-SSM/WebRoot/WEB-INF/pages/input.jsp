<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>input</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/less06/user" method="post">
    	<label>用户名：</label><input type="text" name="username"/>
    	<label>密码：</label><input type="password" name="password"/>
    	<input type="submit" value="submit">
    </form>
  </body>
</html>
