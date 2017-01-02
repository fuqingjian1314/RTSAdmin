<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">
<title>交易网后台首页</title>
</head>    
<body>
<div class="row">
	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-heading" style="position: relative;">
				<span>机构组织</span>
				<div style="float:right;position: relative;padding: 0px;margin: 0px;">
					<input type="text" id="searchKey" style="border-radius:8px;padding:2px 36px 2px 5px;outline:none;float: right;" placeholder="输入如：“工商事业部” 查询">
					<div style="position: absolute;right:0;/* pxborder-radius:8px;border-radius-left:0px; background-color: #9a9a9a;*/top:3px;width:35px;; height: auto;color: #cdcdcd;border-left: 2px solid #cdcdcd;padding-left: 5px;padding-right: 9px;cursor: pointer;" onclick="selectNode();">GO</div>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="panel-body" style="overflow-y: auto;">
				<div id="orgTree" class="ztree"></div>
			</div>
		</div>
	</div>
	<div class="col-md-9">
		<input type="hidden" id="queryOrgId">
		<div class="panel panel-default" id="userListC">
			<div class="panel-heading">
				用户列表&nbsp;-&gt&nbsp;<span id="orgName">顶呱呱集团</span>
			</div>
			<div class="panel-body" style="overflow-y: auto;">
				<table class="table table-bordered table-hover" id="userList">
				   <thead>
				      <tr>
			       		 <th style="width: 30px;"><input type="checkbox"></th>
				         <th field="number" render="getNumber" style="width: 60px;">序号</th>
				         <th field="locked" render="statusFormat">启用状态</th>
				         <th field="loginName">登录名</th>
				         <th field="name">真实姓名</th>
				         <th field="phone">电话号码</th>
				         <th field="sex">性别</th>
				         <th field="id" render="operateFormat">操作</th>
				      </tr>
				   </thead>
				</table>
				<div id="tablePaging"></div>
			</div>
		</div>
	</div>
</div>
<div id="openModel" style="display: none;">
	<form id="submitForm" style="margin-top: 25px;" action="javascript:void(0);">
		<input type="hidden" name="id">
		<input type="hidden" name="pid">
		<div class="form-group col-md-12">
			<label>机构名称</label>
			<input type="text" name="name" class="form-control"  placeholder="机构名称" value="${org.name }">
		</div>
		<div class="form-group col-md-12" >
			<label>排序</label>
			<input type="text" name="sort" class="form-control" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" placeholder="排序"  value="${org.sort }">
		</div>
		<div class="form-group col-md-12">
			<label>描述</label>
			<input type="text" name="description" class="form-control"  placeholder="菜单描述"  value="${org.description }">
		</div>
		<div class="form-group col-md-12" style="text-align: right;">
			<button type="button" id="subButton" class="btn btn-success" onclick="submitForm(this);" isSubmit="false" tId="0">保存</button>
			<button type="reset" class="btn btn-success">重置</button>
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.exedit-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js"></script>
<script type="text/javascript">

	//全局通用树对象
	var zTreeObj;
	$(function(){
		//初始化树和列表高度
		var docH = $(window).height();
		$("#orgTree").height(docH-100);
		$("#userListC").height(docH-20);
		//
		$("#searchKey").keydown(function(event){
			if(event.keyCode==13){
				selectNode();
			}
		});
		
		var setting = {
			async: {
				enable: true,
				url:getAsyncUrl,
				type:'post',
				dataType:'text',
				dataFilter:ajaxDataFilter,
				autoParam:["id=pid"]
			},
			data:{
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0
				}
			},
			view:{
				addHoverDom:addHoverDom,
				removeHoverDom:removeHoverDom
			},
			edit:{
				enable: true,
				removeTitle: "删除",
				showRemoveBtn: setRemoveBtn,
				showRenameBtn: true,
				renameTitle: "编辑"
			}, 
			callback:{
				beforeEditName:beforeEditName,
				beforeRemove: beforeRemove,
				beforeClick: beforeClick,
				onClick:treeOnClick,
				onNodeCreated:onNodeCreated,
				onAsyncSuccess:onAsyncSuccess
			},
		}
		//初始化树
		zTreeObj = $.fn.zTree.init($("#orgTree"), setting, null);
	});
	//异步时，可对url进行操作的方法，也可直接把url写在async的url属性里
	function getAsyncUrl(treeId, treeNode) {
	    return "${pageContext.request.contextPath}/organization/loadTree.shtml";
	};
	//加载数据后，进行树渲染前的操作
	function ajaxDataFilter(treeId, parentNode, childNodes){
		return childNodes;
	}
	
	//设置所有父节点不显示删除按钮
	function setRemoveBtn(treeId,treeNode){
		return !treeNode.isParent;
	}
	//当节点被创建后触发
	function onNodeCreated(event, treeId, treeNode){
	}
	
	//异步树完成后执行
	function onAsyncSuccess(event, treeId, treeNode, msg){
		var node = zTreeObj.getNodeByParam("id", 0, null);
		zTreeObj.expandNode(node, true, false, true);
		//初始化用户列表
		queryUser();
	}
	//鼠标移入显示自定控件
	function addHoverDom(treeId,treeNode){
		var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        //添加按钮的点击事件
        if (btn) btn.bind("click", function(){
        	$("#submitForm").find("input[name='pid']").val(treeNode.id);
        	$("#subButton").attr("tId",treeNode.tId);
        	openModel("添加");
        });
	}
	//鼠标移出事件
	function removeHoverDom (treeId,treeNode){
		$("#addBtn_"+treeNode.tId).unbind().remove();
	}
	
	//修改按钮
	function beforeEditName(treeId,treeNode){
		$.post("${pageContext.request.contextPath}/organization/queryNode.shtml",{id:treeNode.id},function(data){
			if(data != "" && data != null){
				var id = data.id;
				var pid = data.pid;
				var name = data.name;
				var sortL = data.sort;
				var desc = data.description;
				$("#submitForm").find("input[name='id']").val(id);
				$("#submitForm").find("input[name='pid']").val(pid);
				$("#submitForm").find("input[name='name']").val(name);
				$("#submitForm").find("input[name='sort']").val(sortL);
				$("#submitForm").find("input[name='description']").val(desc);
				//修改时把当前节点的tId放入
				$("#subButton").attr("tId",treeNode.tId);
				openModel("修改");
			}
		},"json");
		return false;
	}
	
	//删除节点前的操作
	function beforeRemove (treeId,treeNode){
		var parentNode = treeNode.getParentNode();
		var index = layer.confirm("删除后无法恢复，是否删除？",{icon:3,title:"删除确认"},function(){
			$.post("${pageContext.request.contextPath}/organization/delete.shtml",{id:treeNode.id},function(data){
				layer.close(index);
				//如果删除后按父节点刷新，就会存在递归查询，否则就会把有子节点的节点显示为叶子节点
				//zTreeObj.reAsyncChildNodes(parentNode, "refresh");
				if(data){
					//此方法只是做页面级的删除节点，为了保证数据的准备性，则需要根据后台返回的状态来判定是否需要删除节点
					zTreeObj.removeNode(treeNode,false);
				}
				
				//updateNode只用于更改单个节点的显示值，并没有什么实际价值
				//zTreeObj.updateNode(parentNode);
			});
		});
		return false;
	}
	
	//保存操作
	function submitForm(obj){
		//重复提交验证
		if($(obj).attr("isSubmit")==true || $(obj).attr("isSubmit") == 'true'){
			return ;
		};
		//开启锁
		$(obj).attr("isSubmit",true);
		
		var name = $("#submitForm").find("input[name='name']").val();
		
		if(name == ""){
			layer.tips("请输入机构名",$("#submitForm").find("input[name='name']"),{tip:1});
			$(obj).attr("isSubmit",false);			
			return ;
		}
		var url = "${pageContext.request.contextPath}/organization/saveOrUpdate.shtml"
		$.post(url,$("#submitForm").serialize(),function(data){
			if(data){
				$(".myCloseZ").click();
				//得到操作节点的父节点或当前节点
				var node =zTreeObj.getNodeByTId($(obj).attr("tId"));
				//如果添加或修改节点后按父节点刷新，就会存在递归查询，否则就会把有子节点的节点显示为叶子节点
				//zTreeObj.reAsyncChildNodes(parentNode, "refresh");
				
				//为了避免加载缓慢的情况，使用页面级的修改节点显示信息或添加节点
				if(data.status){
					var newNode = data.data;
					if(data.operateType == "add"){
						//添加节点后
						zTreeObj.addNodes(node,newNode);
					}else{
						//修改节点后
						node.name=newNode.name;
						zTreeObj.updateNode(node);
					}
				}
			}
		});
		//打开锁
		$(obj).attr("isSubmit",false);
	}
	
	//弹出窗口
	function openModel(title){
		var index = layer.open({
			title:title,
			content:$("#openModel"),
			scrollbar:false,
			move:false,
			end:function(){
				$("#submitForm")[0].reset();
				$("#submitForm").find("input[name='id']").val("");
				$("#submitForm").find("input[name='pid']").val("");
			},
			area:['500px','350px'],
			type:1
		});
		
		$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
		$(".myCloseZ").on('click',function(){
			layer.close(index);
		});
	}
	
	function queryUser(orgId,orgName){
		//针对第一级：顶呱呱集团，只加载隶属于集团的人员
		$("#queryOrgId").val(orgId);
		//针对第一级：顶呱呱集团，如果放开后加载集团所有人，不带机构条件
		//$("#queryOrgId").val(orgId==0?null:orgId);
		$("#orgName").text(orgName);
		loadPage(1);
	}
	
	//加载分页数据
	function loadPage(pageNum){
		var offset=(pageNum-1)*15;
		var orgId = $("#queryOrgId").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/organization/queryUser.shtml",
			data:{
					offset:offset,
					orgId:orgId
				},
			type:"post",
			dataType:"json",
			success:function(data){
				var rows=data.items;
				var rowsCount = data.rowsCount;
				$("#userList").table(rows);
				//初始化分页插件
				$("#tablePaging").html("");
				$("#tablePaging").tablePaging(1,15,rowsCount,"loadTable");
			},
			error:function(){
				
			}
			
		});
	}
	//加载用户列表
	function loadTable(pageNum){
		var offset=(pageNum-1)*15;
		var orgId = $("#queryOrgId").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/organization/queryUser.shtml",
			data:{
				offset:offset,
				orgId:orgId
			},
			type:"post",
			dataType:"json",
			success:function(data){
				var rows=data.items;
				$("#userList").table(rows);
			},
			error:function(){
			}
		});
	}
	
	//用户状态渲染
	function statusFormat(e){
		var value=e.value;
    	var row=e.row;
   		return value == 1 ? "禁用":"启用";
	}
	//用户操作渲染
	function operateFormat(e){
		var value=e.value;
    	var row=e.row;
    	return "<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"openOrgModel("+value+")\">调整机构</button>";
	}
	
	//onClick事件前执行
	function beforeClick(treeId, treeNode, clickFlag){
		return true;
	}
	//onClick事件触发
	function treeOnClick(event, treeId, treeNode){
		var orgId = treeNode.id;
		queryUser(orgId,treeNode.name);
	}
	
	//序号
	function getNumber(e){
		return e.rowindex+1;
	}
	//树搜索功能
	function selectNode(){
		var searchKey = $("#searchKey").val();
		if(searchKey == null || searchKey == ""){
			return ;
		}
		//服务器端的模糊匹配，有可能是多个选中，所以就不连动用户查询了
		$.post("${pageContext.request.contextPath}/organization/searchOrg.shtml",{name:searchKey},function(data){
			var nodes = data;
			for(var i=0;i<nodes.length;i++ ){
				var node = zTreeObj.getNodeByParam("id", nodes[i].id, null);
				zTreeObj.selectNode(node,true);
			}
		},"json");
		
		//页面级的精准匹配
		/* var node = zTreeObj.getNodeByParam("name", searchKey, null);
		//var a = zTreeObj.expandNode(node, true, true, true);
		zTreeObj.selectNode(node,false);
		if(node != null){
			queryUser(node.id,node.name);
		} */
	}
	
	var openIndex;
	var chooseUserId;
	//调整机构
	function openOrgModel(id){
		
		//这是为了修改后，可以刷新出正确的结果，应对用户通过左侧的查询框查询后，右侧列表没有刷新，直接进行修改用户机构，修改完成后，系统不知应该以选中的哪个机构的ID刷新用户列表
		var nodes = zTreeObj.getSelectedNodes();
		if(nodes.length != 1){
			layer.msg("调整机构前，左侧机构只能选中单个",{time:1000});
			return ;
		}
		
		chooseUserId=id;
		openIndex = layer.open({
			      type: 2,
			      title: '机构浏览',
			      area: ['430px', '450px'],
			      content: '${pageContext.request.contextPath}/user/browseOrg.shtml',
			      btn: ['确认','取消'],
			      yes: function(index, layero){
			    	  var iframe="layui-layer-iframe"+index;
			    	  document.getElementById(iframe).contentWindow.yes(); 
			      },
			      btn2: function(index, layero){
			      } 
			  });
	}
	//开放给child窗口使用的方法:修改机构
	function changeOrg(node){
		var nodes = zTreeObj.getSelectedNodes();
		if(nodes.length != 1){
			layer.msg("调整机构前，左侧机构只能选中单个",{time:1000});
			return ;
		}
		if(chooseUserId == null || chooseUserId == ''){
			return ;
		}
		$.post("${pageContext.request.contextPath}/user/saveOrUpdate.shtml",{id:chooseUserId,orgId:node.id},function(data){
			closeModel();
			if(data.status){
				layer.msg(data.info,{time:1000});
				queryUser(nodes[0].id,nodes[0].name);
				
			}else{
				layer.msg(data.info,{time:1000});
			}
		},"json");
	}
	
	//开放给child窗口使用的方法:关闭弹窗
	function closeModel(){
		layer.close(openIndex);
	}
	
</script>
</body>
</html>