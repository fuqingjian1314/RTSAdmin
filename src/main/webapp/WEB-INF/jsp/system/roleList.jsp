<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<title>交易网后台首页</title>
</head>    
<body>
<!-- begin搜索 -->
<div class="well well-sm" id="searchIntro">
	<form id="searchForm" class="form-inline">
		<div class="row">
			<div class="form-group  col-md-4 ">
   				<div class="input-group">
					<span class="input-group-addon">角色名称</span>
					<input class="form-control" name="name" value="${name }">
					<span class="input-group-addon">KEY</span>
					<input class="form-control" name="roleKey" value="${roleKey }">	
				</div>
			</div>
			<div class="form-group  col-md-2">
				<button type="button" class="btn btn-success" onclick="searchIntro()">搜索</button>
				<input type="reset" id="formRest" style="display: none;">
				<button type="button" class="btn btn-success" onclick="resetIntro()">重置</button>
			</div>
			
		</div>
	</form>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading" style="padding: 5px 15px;">
				<button type="button" class="btn btn-success btm-sm" id="add_id" onclick="addRole()">新增</button>
			</div>
			<div class="panel-body hfit" style="overflow-x: auto;">
				<!-- Table -->
			  	<table class="table table-bordered table-hover"  id="table_url_fqj">
				   <thead>
				      <tr>
				         <th field="id">ID</th>
				         <th field="name">名称</th>
				         <th field="roleKey">KEY</th>
				         <th field="description">描述</th>
				         <th field="formatCreateDate">创建时间</th>
				         <th field="createrName">创建人</th>
				         <th field="cz" render="btndos">操作</th>
				      </tr>
				   </thead>
				</table>
				<div id="tablePaging"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script type="text/javascript">
$(function(){
	var divheight=$(window).height();//初始化修改div的高度
	divheight=divheight-119;
	 $(".hfit").css({height:divheight});
});
//搜索方法
function searchIntro(){
	loadPaging(1);
}
//重置搜索条件
function resetIntro(){
	$("#searchForm")[0].reset();
	loadPaging(1);
}
function getFromData(formId){
	var data = {};
	$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
	return data;
}

/*加载角色*/
function loadPager(pageNum){
	var url="${pageContext.request.contextPath}/admin/role/roleQueryLimit.shtml";
	var offSet=(pageNum-1)*10;
	$.post(url,{offSet:offSet,pageSize:10},function(rows){
		$("#table_url_fqj").table(rows);
		//初始化分页插件
	});
}
function loadPaging(pageNum){
	var url="${pageContext.request.contextPath}/admin/role/rloeQueryPager.shtml";
	
	var date=getFromData("searchForm");
	date.offSet=pageNum-1;
	date.pageSize=10;
	$.post(url,date,function(data){
		var rows=data.items;
		var count=data.rowsCount;
		$("#table_url_fqj").table(rows);
		//初始化分页插件
		$("#tablePaging").tablePaging(1,10,count,"loadPager");
	});
}
/*按钮渲染*/
function btndos(e){
	var value=e.value;
	var row=e.row;
	var btn ="<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"modifyRole('"+row.id+"')\">修改</button>&nbsp;&nbsp;" 
			+ "<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"deleteRole('"+row.id+"')\">删除</button>";
	return btn;
}
loadPaging(1);
	
//修改
function modifyRole(id){
	$.post("${pageContext.request.contextPath}/admin/role/updateRedirect.shtml",{id:id},function(data){
		openLayerModel("修改角色",data);
	},'html');
}

//删除
function  deleteRole(id){
	$.post("${pageContext.request.contextPath}/admin/role/delete.shtml",{id:id},function(data){
		layer.msg(data.info,{time:1000},function(){
			loadPaging(1);
			return ;
		});
	});
	
}

//添加
function addRole(){
	$.ajax({
		url:"${pageContext.request.contextPath }/admin/role/addRedirct.shtml",
		type:"post",
		success:function(data){
			openLayerModel("添加角色",data);
		}
		
	});
}
function openLayerModel(title,html){
	var index = layer.open({
		title:title,
		content:html,
		scrollbar:false,
		move:false,
		area:['500px','350px'],
		type:1
	});
	
	$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
	$(".myCloseZ").on('click',function(){
		layer.close(index);
		loadPaging(1);
	});
	
}	
</script>
</body>
</html>