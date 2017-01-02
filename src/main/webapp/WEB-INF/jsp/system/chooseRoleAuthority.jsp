<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/metroStyle/metroStyle.css?v=<%=getServletContext().getAttribute("version") %>" rel="stylesheet">
<title>交易网后台首页</title>
<style>
.list-group-item{
	    padding: 5px 5px;
}
.list-group-item.active,
.list-group-item.active:hover,
.list-group-item.active:focus {
  z-index: 2;
  color: #fff;
  background-color: #4D4D4E;
  border-color: #4D4D4E;
}
</style>
</head>    
<body>   
<div class="row">
	<div class="col-md-3">
		<div class="panel panel-default">
				<div class="panel-heading">角色列表</div>
				<div class="panel-body hfit" style="height: 600px;overflow-y: auto;">
					<div class="list-group" id="div_rolelist_fqj">
<!-- 					  <a href="#" class="list-group-item">
					    超级管理员
					  </a>
					  <a href="#" class="list-group-item">系统管理员</a>
					  <a href="#" class="list-group-item">审计管理员</a>
					  <a href="#" class="list-group-item">权限管理员</a>
					  <a href="#" class="list-group-item">商标录入员</a> -->
					</div>
				</div>
		</div>
		
	</div>
	<div class="col-md-5">
		<div class="panel panel-default">
			<div class="panel-heading">菜单树<button type="button" class="btn btn-success btn-sm" onclick="saveRoleMenusList();" style="float: right;margin-top: -5px;">保存</button></div>
			<div class="panel-body hfit" style="overflow-x:auto;">
					<div>
						<ul id="orgtree2" class="ztree"></ul>
					</div>
			</div>
		</div>
	</div>	
		
	<div class="col-md-4">	
				<div class="panel panel-default">
				<div class="panel-heading">按钮列表<button type="button" class="btn btn-success btn-sm" onclick="saveMenuBTNs();" style="float: right;margin-top: -5px;">保存</button></div>
				<div class="panel-body hfit" style="overflow-x: auto;">
					<table class="table table-bordered table-hover" id="table_button_fqj">
					   <thead>
					      <tr>
					         <th render="btncheckRender"><input type="checkbox"></th>
					         <th field="name" render="btnNameRender">按钮</th>
					         <th field="description">描述</th>
					      </tr>
					   </thead>
					</table>
				</div>
				</div>
	</div>
		
</div>	
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/jquery/dist/jquery.min.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.exedit-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.excheck-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/system/chooseRoleAuthority.js?v=<%=getServletContext().getAttribute("version") %>"></script>
</body>
</html>


