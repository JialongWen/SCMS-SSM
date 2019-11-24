<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="include/include.jsp" %>
    <title>代码管理系统</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7-dist/css/register.css" rel="stylesheet">

  </head>

  <body>
    <div class="container">
      <form class="form-signin" action="${pageContext.request.contextPath }/user/register" method="post" id="register-form">
        <h2 class="form-signin-heading">用户注册</h2>
        <div id="errormsg-box"></div>
        <label for="username" class="sr-only">用户名</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        <label for="repassword" class="sr-only">重复密码</label>
        <input type="password" id="repassword" name="repassword" class="form-control" placeholder="重复密码" required>
        <div style="margin-bottom: 20px;">
          <a href="home"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;回首页</a>
          <a href="login" class="pull-right">已注册，去登录</a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>
    </div> <!-- /container -->
  </body>
<script type="text/javascript">
$(this).ready(function(){
	var isUsername=false, isPassword=false, isRepassword=false;
	$("#username").blur(function(){
		var value = $(this).val();
		var box = $("#errormsg-box");
		//box.empty();
		if(isBlank(value)){
			//为空
			isUsername = false;
			$(".error-1").remove();
			var p = $("<p class='error-1 text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;用户名不可为空！</p>");
			box.append(p);
		}else if(isExsit(value)){
			//占用
			isUsername = false;
			$(".error-1").remove();
			var p = $("<p class='error-1 text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;用户名已存在！</p>");
			box.append(p);
		}else{
			//OK
			$(".error-1").remove();
			isUsername = true;
		}
	});
	
	$("#password").blur(function(){
		var value = $(this).val();
		var box = $("#errormsg-box");
		//box.empty();
		if(isBlank(value)){
			//为空
			isPassword = false;
			$(".error-2").remove();
			var p = $("<p class='error-2 text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;密码不可为空！</p>");
			box.append(p);
		}else{
			//OK
			$(".error-2").remove();
			isPassword = true;
		}
	});
	

	$("#repassword").blur(function(){
		var value = $(this).val();
		var password = $("#password").val();
		var box = $("#errormsg-box");
		//box.empty();
		if(isBlank(value)){
			//为空
			isRepassword = false;
			$(".error-3").remove();
			var p = $("<p class='error-3 text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;重复密码不可为空！</p>");
			box.append(p);
		}else if(value!=password){
			isRepassword = false;
			$(".error-3").remove();
			var p = $("<p class='error-3 text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;重复密码与密码不一致！</p>");
			box.append(p);
		}else{
			//OK
			$(".error-3").remove();
			isRepassword = true;
		}
	});
	
	$("#register-form").submit(function(){
		if(isUsername&&isPassword&&isRepassword){
			$(this).submit();
		}else{
			alert("请正确填写表单再提交！");
		}
	});
});

function isExsit(value){
	var url = "user/exsist/" + value;
	var isExsist = false;
	$.ajax({
		url:url,
		dataType:'json',
		success:function(result){
			if(result.isExsist){
				isExsist = true;
			}else{
				isExsist = false;
			}
		},
		async:false
	});
	return isExsist;
}
</script>
</html>

