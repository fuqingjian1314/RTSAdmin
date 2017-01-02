<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>交易网后台首页</title>
<jsp:include page="../../common/adminContentHead.jsp" />
<style type="text/css">
html {
    width: 100%;
    overflow: auto;
}
</style>
</head>
<body>
<div class="panel panel-default" style="padding: 10px 30px;border: none;">
	<form id="updataForm" class="form-horizontal">
		<div class="form-group col-md-14">
		  	<label>账号</label>
		  	<input type="text" name="loginName" class="form-control" placeholder="账号" value="${adminUser.loginName}">
		</div>
		<div class="form-group col-md-14">
		  	<label>密码</label>
		  	<input type="password" name="loginPwd" class="form-control" placeholder="密码" value="${adminUser.loginPwd}">
		</div>
		<div class="form-group col-md-14">
		  	<label>工号</label>
		  	<input type="text" name="seatNumber" class="form-control" placeholder="工号" value="${adminUser.seatNumber}">
		</div>
		<div class="form-group col-md-14">
		  	<label>姓名</label>
		  	<input type="text" name="name" class="form-control" placeholder="姓名" value="${adminUser.name}">
		</div>
		<div class="form-group col-md-14">
		  	<label>剩余分数</label>
		  	<input type="text" name="restscore" class="form-control" placeholder="剩余分数" value="${adminUser.restscore}">
		</div>
		<div class="form-group col-md-14">
		  	<label>电话</label>
		  	<input type="text" name="phone" class="form-control" placeholder="电话" value="${adminUser.phone}">
		</div>
		<div class="form-group col-md-14">
		  	<label>机构</label>
			<div class="input-group">
				<input type="hidden" name="orgId" id="orgId" value="${adminUser.orgId }">
				<input type="text" class="form-control" readonly="readonly" id="orgNameOnly">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="openOrgModel();">浏览</button>
				</span>
			</div>
		  
		</div>
		<input type="hidden" value="${adminUser.id}" name="id">	
	</form>
</div>
</body>
<script type="text/javascript">

	//采用异步的方式加载用户的机构，目的是不影响已存在的功能
	$(function(){
		var orgId = $("#orgId").val();
		if(orgId == "" || orgId == null){
			return;		
		}
		initOrgName(orgId);
	});
	//加载user当前机构
	function initOrgName(id){
		$.post("${pageContext.request.contextPath}/user/queryOrgById.shtml",{id:id},function(data){
			if(data != null && data != ""){
				$("#orgNameOnly").val(data.name);
			}
		},"json");		
	}
	
	var index;
	function openOrgModel(){
		index = layer.open({
			      type: 2,
			      title: '机构浏览',
			      area: ['430px', '450px'],
			      content: '${pageContext.request.contextPath}/user/browseOrg.shtml',
			      btn: ['选择','取消'],
			      yes: function(index, layero){
			    	  var iframe="layui-layer-iframe"+index;
			    	  document.getElementById(iframe).contentWindow.yes(); 
			      },
			  });
	}
	//开放给child窗口使用的方法:修改机构
	function changeOrg(node){
		$("#orgId").val(node.id);
		$("#orgNameOnly").val(node.name);
	}
	
	//开放给child窗口使用的方法:关闭弹窗
	function closeModel(){
		layer.close(index);
	}
</script>
</html>
