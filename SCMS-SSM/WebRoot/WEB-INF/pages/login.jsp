<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="include/include.jsp" %>
    <title>代码管理系统</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">
  </head>

  <body>
    <div class="container">
      <form class="form-signin" action="user/login" method="post">
        <h2 class="form-signin-heading">用户登录</h2>
        
        <c:if test="${not empty message }">
	        <!-- 错误信息 -->
	        <p class="text-danger text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;${message }</p>
        </c:if>
        
        <label for="username" class="sr-only">用户名</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        <div style="margin-bottom: 20px;">
          <a href="home"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;回首页</a>
          <a href="register" class="pull-right">注册新用户</a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div> <!-- /container -->
  </body>
</html>
