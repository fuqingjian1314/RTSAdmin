<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../common/adminContentHead.jsp" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">
<title>交易网后台首页</title>
</head>    
<body>
<div class="row">
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span>机构树</span>
				<div style="float:right;position: relative;padding: 0px;margin: 0px;">
					<input type="text" id="searchKey" style="border-radius:8px;padding:2px 36px 2px 5px;outline:none;float: right;" placeholder="输入如：“工商事业部” 查询">
					<div style="position: absolute;right:0;/* pxborder-radius:8px;border-radius-left:0px; background-color: #9a9a9a;*/top:3px;width:35px;; height: auto;color: #cdcdcd;border-left: 2px solid #cdcdcd;padding-left: 5px;padding-right: 9px;cursor: pointer;" onclick="selectNode();">GO</div>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="panel-body" style="overflow: auto;width: 100%;height: 300px;">
				<div id="orgTree" class="ztree"></div>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.exedit-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.excheck-3.5.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js"></script> --%>
<script type="text/javascript">
	
	//全局通用树对象
	var zTreeObj;
	$(function(){
		//搜索框事件
		$("#searchKey").keydown(function(event){
			if(event.keyCode==13){
				selectNode();
			}
		});
		
		//同步数源
		var data = ${orgList};
		//树配置
		var setting = {
			data:{
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0
				}
			},
			edit:{
				enable: false,
			},
			view:{
				selectedMulti:true
			},
			callback:{
				onDblClick:onDblClick
			},
		}
		//初始化树
		zTreeObj = $.fn.zTree.init($("#orgTree"), setting, data);
		
		onSuccess();
		
	});
	//onClick事件触发
	function onDblClick(event, treeId, treeNode){
		var orgId = treeNode.id;
		//调用父窗口的方法
		parent.changeOrg(treeNode);
		parent.closeModel();
	}
	
	//同步树完成后执行
	function onSuccess(event, treeId, treeNode, msg){
		var node = zTreeObj.getNodeByParam("id", 0, null);
		zTreeObj.expandNode(node, true, false, true);
	}
	//树搜索功能
	function selectNode(){
		var searchKey = $("#searchKey").val();
		if(searchKey == null || searchKey == ""){
			return ;
		}
		
		$.post("${pageContext.request.contextPath}/organization/searchOrg.shtml",{name:searchKey},function(data){
			var nodes = data;
			for(var i=0;i<nodes.length;i++ ){
				var node = zTreeObj.getNodeByParam("id", nodes[i].id, null);
				zTreeObj.selectNode(node,true);
			}
		},"json");
	}
	
	//提供给父窗口调用的方法
	function yes(){
		var nodes = zTreeObj.getSelectedNodes();
		if(nodes.length > 1){
			layer.msg("请选择单个机构！",{time:1000});
			return;
		}
		if(nodes.length < 1){
			layer.msg("请选择机构！",{time:1000});
			return;
		}
		//调用父窗口的方法
		parent.changeOrg(nodes[0]);
		parent.closeModel();
	}
</script>
</body>
</html>