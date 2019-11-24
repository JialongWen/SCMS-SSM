<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页</title>
  </head>
  
  <body>
  	<h1><a href="home">代码管理系统首页</a></h1>
  	<hr>
    <h1><a href="hello">Say Hello!</a></h1>
    <hr>
    <h1><a href="less04/testRequestMapping">Test Request Mapping</a></h1>
    <hr>
    <form action="less04/testRequestMappingMethodPost" method="post">
    	<input type="text" name="username"/>
    	<input type="submit" value="Test Post Request">
    </form>
    <hr>
    <h1><a href="less04/test/request/ant">Test Request Ant1</a></h1>
    <hr>
    <h1><a href="less04/test/request/ant/2">Test Request Ant2</a></h1>
    <hr>
    <h1><a href="less04/test/request/param?username=allen&password=123456&rank=2">Test Request Param</a></h1>
    <hr>
    <form action="less04/test/request/param" method="post">
    	<input type="text" name="username"/>
    	<input type="password" name="password"/>
    	<input type="text" name="rank"/>
    	<input type="submit" value="Test Request Param">
    </form>
    <hr>
    <h1><a href="less04/test/request/api?username=allen&password=123456&rank=2">Test Request API</a></h1>
    <hr>
    <h1><a href="less06/users">用户管理</a></h1>
  </body>
</html>
