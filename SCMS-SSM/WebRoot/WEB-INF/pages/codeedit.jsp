<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="include/include.jsp" %>
    <title>代码管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7-dist/css/dashboard.css">
  </head>

  <body>
    <jsp:include page="include/header.jsp"></jsp:include>

    <!-- Begin page content -->
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="include/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<h1 class="page-header">代码上传</h1>
        	<div class="row placeholders">
        		<div class="col-md-8">
		        	<form action="${pageContext.request.contextPath }/code/${code.id}" class="form-horizontal" method="POST">
					  <input type="hidden" name="_method" value="PUT"/>
					  <input type="hidden" name="id" value="${code.id }"/>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">代码文件</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="codename" name="codename" placeholder="代码名称" value="${code.codename }">
					    </div>
					    <div class="col-sm-5">
					      <p class="text-warning text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;代码名称</p>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-2 control-label">代码说明</label>
					    <div class="col-sm-5">
					      <textarea class="form-control" id="intro" name="intro" placeholder="代码说明" rows="5"  style="resize:none;">${code.intro }</textarea>
					    </div>
					    <div class="col-sm-5">
					      <p class="text-danger text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;代码说明不可为空！</p>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-5">
					      <button type="submit" class="btn btn-success btn-block">提交</button>
					    </div>
					  </div>
					</form>
	        	</div>
        	</div>
			</div>
        </div>
      </div>
	<jsp:include page="include/footer.jsp"></jsp:include>

  </body>
</html>
