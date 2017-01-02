<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Object version = getServletContext().getAttribute("version");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/bootstrap/dist/css/bootstrap.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/font-awesome/css/font-awesome.min.css?v=<%=version %>" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/plugins/layer/skin/layer.css?v=<%=version %>" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bootstrap-select/css/bootstrap-select.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/boostrap-datetimepicker/css/bootstrap-datetimepicker.min.css?v=<%=version %>" rel="stylesheet">
<%--<link href="${pageContext.request.contextPath}/css/font-awesome/font-awesome.min.css?v=<%=version %>" rel="stylesheet" type="text/css">--%>
<link href="${pageContext.request.contextPath}/css/Font-Awesome-master/css/font-awesome.min.css?v=<%=version %>" rel="stylesheet">



<!-- JS -->
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/jquery/dist/jquery-1.10.2.min.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/bootstrap/dist/js/bootstrap.min.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/plugins/layer/layer.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/bootstrap-select/js/bootstrap-select.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/boostrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=<%=version %>"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]-->
  <!-- <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script> -->
<!--  [endif]-->

<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.js?v=<%=version %>"></script>
<style type="text/css">
html{width:100%;overflow:hidden;}
body{margin: 8px;}
.well {
	margin-bottom: 10px;
}  
</style>
<script type="text/javascript">
	var rootpath="${pageContext.request.contextPath}";
	
	$(function(){
		//销毁没有权限的按钮
		//destroyNotCompetenceBtn();
		
		//destroyNotCompetenceBtn1(btnList);
		
	});
	
	//获取当前页的按钮列表
	//var btnList = ${btnjsons};
	
	
</script>

<script src="${pageContext.request.contextPath}/js/system/pageBtnIsDisplay.js?v=<%=version %>"></script> 
	
 
<!-- 提示窗口 -->
<div class="modal" id="mymodal" style="margin-top: 150px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">提示信息</h4>
			</div>
			<div class="modal-body">
				<p>你没有当前操作的权限</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

