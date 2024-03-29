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
        	<h1 class="page-header">关于本系统</h1>
			<div>
	        	<p>
					本系统针对Web程序设计（JSP）课程的教学和实训所设计开发。
				</p>
				<p>					
					通过对本系统的开发，学生掌握：<br/>
				</p>
				<ul>
					<li>网站的HTML静态页面模型的制作</li>
					<li>使用MySQL根据网站需求设计和实现数据库</li>
					<li>掌握Servlet技术</li>
					<li>掌握Filter过滤器技术</li>
					<li>掌握Listener监听器技术</li>
					<li>掌握JSP技术</li>
					<li>掌握JSTL和EL</li>
					<li>JSP的MVC实现</li>
				</ul>
			</div>
        </div>
      </div>
    </div>
	<jsp:include page="include/footer.jsp"></jsp:include>

  </body>
</html>
