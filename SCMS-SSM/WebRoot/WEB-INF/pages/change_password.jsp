<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<%@ include file="include/include.jsp" %>
    <title>代码管理系统</title>
	<link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7-dist/css/dashboard.css" rel="stylesheet">
  </head>

  <body>

    <jsp:include page="include/header.jsp"></jsp:include>

    <!-- Begin page content -->
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="include/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<h1 class="page-header">修改密码</h1>
        	<div class="row placeholders">
        		<div class="col-md-8">
		        	<form id="changepassword_form" class="form-horizontal" action="${pageContext.request.contextPath }/user/changePassword" method="post">
					    <input type="hidden" name="id" value="${sessionScope.loginUser.id }"/>
					    <div class="form-group">
					        <label for="oldpassword" class="col-sm-2 control-label">原密码</label>
					        <div class="col-sm-5">
					          <input type="password" class="form-control" name="oldpassword" id="oldpassword" placeholder="原密码">
					        </div>
					        <div class="col-sm-5">
					          <p id="oldpassword_info" class="text-warning text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;请输入您原有的密码</p>
					        </div>
					    </div>
		                <div class="form-group">
		                  <label for="password" class="col-sm-2 control-label">新密码</label>
		                  <div class="col-sm-5">
		                    <input type="password" class="form-control" name="password" id="password" placeholder="新密码">
		                  </div>
		                  <div class="col-sm-5">
		                    <p id="password_info" class="text-warning text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;请输入您要修改的密码</p>
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label for="repassword" class="col-sm-2 control-label">确认密码</label>
		                  <div class="col-sm-5">
		                    <input type="password" class="form-control" name="repassword" id="repassword" placeholder="确认密码">
		                  </div>
		                  <div class="col-sm-5">
		                    <p id="repassword_info" class="text-warning text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;请重复输入您要修改的密码以确认正确</p>
		                  </div>
		                </div>
					      
					    <div class="form-group">
					      <div class="col-sm-offset-2 col-sm-5">
					        <button type="submit" class="btn btn-success btn-block">提交修改</button>
					      </div>
					    </div>
					</form>
	        	</div>
        	</div>
			
			</div>
        </div>
      </div>
    </div>
    <jsp:include page="include/footer.jsp"></jsp:include>
    <script type="text/javascript">
    $(this).ready(function(){
    	var isOldPassword = false, isPassword = false, isRePassword = false;
    	
    	$("#oldpassword").blur(function(){
    		var value = $(this).val();
    		if(isBlank(value)){
    			//错误
    			//在oldpassword_info中显示错误信息
    			validateInfo("oldpassword_info", false, "原密码不可为空");
    			isOldPassword = false;
    		}else{
    			//正确
    			//在oldpassword_info中显示正确的信息
    			validateInfo("oldpassword_info", true, "");
    			isOldPassword = true;
    		}
    	});
    	
    	$("#password").blur(function(){
    		var value = $(this).val();
    		if(isBlank(value)){
    			//错误
    			//在oldpassword_info中显示错误信息
    			validateInfo("password_info", false, "新密码不可为空");
    			isPassword = false;
    		}else{
    			//正确
    			//在oldpassword_info中显示正确的信息
    			validateInfo("password_info", true, "");
    			isPassword = true;
    		}
    	});
    	

    	$("#repassword").blur(function(){
    		var value = $(this).val();
    		var newpassword = $("#password").val();
    		if(isBlank(value)){
    			//错误
    			//在oldpassword_info中显示错误信息
    			validateInfo("repassword_info", false, "重复密码不可为空");
    			isRePassword = false;
    		}else if(value != newpassword){
    			validateInfo("repassword_info", false, "重复密码必须与新密码一致");
    			isRePassword = false;
    		}else{
    			//正确
    			//在oldpassword_info中显示正确的信息
    			validateInfo("repassword_info", true, "");
    			isRePassword = true;
    		}
    	});
    	
    	$("#changepassword_form").submit(function(){
    		if(isOldPassword && isPassword && isRePassword){
    			$(this).submit();
    		}else{
    			alert("请先正确填写表单！");
    			return false;
    		}
    	});
    });
    
    
    function validateInfo(boxid, isSuccess, message){
    	var p = $("#" + boxid);
		p.removeClass("text-warning");
		p.removeClass("text-danger");
		p.removeClass("text-success");
		p.empty();
		var icon;
		if(isSuccess){
			p.addClass("text-success");
			icon = $("<span class='glyphicon glyphicon-ok-circle'></span>");
		}else{
			p.addClass("text-danger");
			icon = $("<span class='glyphicon glyphicon-remove-circle'></span>");
		}
		var msg = $("<span>&nbsp;" + message + "</span>");
		p.append(icon);
		p.append(msg);
    }
    </script>
    
  </body>
</html>
