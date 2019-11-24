<%@ page pageEncoding="UTF-8" %>
<div class="col-sm-3 col-md-2 sidebar">
  <div style="padding:0px 0px 20px 0px;">
  <span class="glyphicon glyphicon-bell"></span>&nbsp;当前在线人数：${onlineNum }人
  </div>
  <ul class="nav nav-sidebar" style="font-size: 18px;">
    <li class="active"><a href="${pageContext.request.contextPath }/codes"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;代码一览 <span class="sr-only">(current)</span></a></li>
    <li><a href="${pageContext.request.contextPath }/admin"><span class="glyphicon glyphicon-cog"></span>&nbsp;代码管理</a></li>
    <li><a href="${pageContext.request.contextPath }/code"><span class="glyphicon glyphicon-upload"></span>&nbsp;代码上传</a></li>
  </ul>
</div>