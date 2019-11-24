<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>edit</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/less06/user/${user.id}" method="POST">
    	<input type="hidden" name="_method" value="PUT"/>
    	<input type="hidden" name="id" value="${user.id}"/>
    	<label>用户名：</label><input type="text" name="username" value="${user.username }"/>
    	<label>密码：</label><input type="password" name="password" value="${user.password }"/>
    	<label>角色：</label><input type="text" name="role" value="${user.role }"/>
    	<input type="hidden" name="registerTime" value="${user.registerTime}"/>
    	<input type="submit" value="submit">
    </form>
  </body>
</html>
