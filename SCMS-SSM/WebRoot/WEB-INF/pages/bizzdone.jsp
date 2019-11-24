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
    		<div class="text-center">
    	       <h1 class="text-success"><span class="glyphicon glyphicon-ok-circle" style="position: relative; top:6px;"></span>&nbsp;${message}</h1>
            </div>
        </div>
      </div>
    </div>
	<jsp:include page="include/footer.jsp"></jsp:include>
  </body>
</html>
