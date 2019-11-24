<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        	<h1 class="page-header">代码一览</h1>
        	<div class="row placeholders">
        		<div class="col-md-4">
		        	<form action="">
					    <div class="input-group">
					      <input type="text" class="form-control" placeholder="按关键词查找">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
					      </span>
					    </div><!-- /input-group -->
		        	</form>
	        	</div>
        	</div>
			<div class="table-responsive">
	        	<table class="table table-bordered  table-striped">
	        		<tr>
	        			<th width="5%">序号</th>
	        			<th width="35%">代码文件</th>
	        			<th width="35%">代码说明</th>
	        			<th width="10%">上传者</th>
	        			<th width="15%">上传时间</th>
	        		</tr>
	        		
	        		<c:choose>
	        			<c:when test="${not empty codelist }">
	        				<c:forEach items="${codelist }" var="code" varStatus="status">
	        					<tr>
				        			<td align="center">${status.index + 1}</td>
				        			<td><a href="${pageContext.request.contextPath }/${code.filepath}">${code.codename }</a></td>
				        			<td style="text-indent: 2em;">${code.intro }</td>
				        			<td align="center">${code.owner.username }</td>
				        			<td align="center">
				        				<fmt:formatDate value="${code.addTime }" pattern="yyyy年MM月dd日 HH:mm"/>
				        			</td>
				        		</tr>
	        				</c:forEach>
	        			</c:when>
	        			<c:otherwise>
	        				<tr>
	        					<td colspan="5" align="center">暂无数据</td>
	        				</tr>
	        			</c:otherwise>
	        		</c:choose>
	        		
				</table>
				<nav aria-label="Page navigation" class="text-right">
				  <ul class="pagination">
				    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
    				<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
				    <li>
				      <a href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</div>
        </div>
      </div>
    </div>

    <jsp:include page="include/footer.jsp"></jsp:include>
  </body>
</html>
