<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<jsp:include page="../../common/adminContentHead.jsp" />
</head>
<body>
<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 5px 15px;">
					<button type="button" class="btn btn-success btn-sm"
						onclick="getCheckedRows()">获取选中行数据</button>
						<button type="button" class="btn btn-success btn-sm"
						onclick="getNotCheckedRows()">获取没有选中行数据</button>
					<button type="button" class="btn btn-success btn-sm"
						onclick="checkedAll()">全部选中</button>
					<button type="button" class="btn btn-success btn-sm"
						onclick="notCheckedAll()">全部取消选中</button>
						<button type="button" class="btn btn-success btn-sm"
						onclick="getAll()">获取全部行数据</button>
						<button type="button" class="btn btn-success btn-sm"
						onclick="getAllRowNum()">获取总行</button>			
				</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;">
					<!-- Table -->
				  	<table class="table table-bordered table-hover"  id="table_url_fqj" rowClick="tt" rowDbClick="tt1" columSort="true">
					   <thead>
					      <tr>
					      	 <th style="width: 30px;"><input type="checkbox"></th>
					         <th field="loginName">登录名 </th>
					         <th field="name">名称  </th>
					         <th field="sex">性别</th>
					         <th field="cz" render="btndos">操作</th>
					      </tr>
					   </thead>
					</table>
					<div id="tablePaging"></div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js"></script>
<script type="text/javascript">
$(function(){
	var divheight=$(window).height();//初始化修改div的高度
	divheight=divheight-100;
	 $(".hfit").css({height:divheight});
	 
})

function tt(){
	console.log(9);
	var row=$("#table_url_fqj").getActiveRow();
	alert(row);
	//alert(9);
}
function tt1(){
	console.log(10);
	//alert(10);
}
function getCheckedRows(){
	var rows=$("#table_url_fqj").getTableSelects();
	alert(JSON.stringify(rows));
}
function getNotCheckedRows(){
	var rows=$("#table_url_fqj").getTableNoSelects();
	alert(JSON.stringify(rows));
}
function checkedAll(){
	$("#table_url_fqj").checkedTableALLRows();
}
function notCheckedAll(){
	$("#table_url_fqj").notCheckedTableALLRows();
}
function getAll(){
	var rows=$("#table_url_fqj").getTableALLRows();
	alert(JSON.stringify(rows));
}
/*获取全部行数*/
function getAllRowNum(){
	var num=$("#table_url_fqj").getTableALLRowsNum();
	alert(num);
}
/*加载用户*/
function loadUsers(pageNum){
	var url=rootpath+"/example/queryUserList.shtml";
	var offSet=(pageNum-1)*10;
	$.post(url,{offSet:offSet,pageSize:10},function(rows){
		$("#table_url_fqj").table(rows);
		//初始化分页插件
	});
}
function loadPaging(pageNum){
	var url=rootpath+"/example/queryUserPager.shtml";
	var offSet=(pageNum-1)*10;
	$.post(url,{offSet:offSet,pageSize:10},function(data){
		var rows=data.items;
		var count=data.rowsCount;
		$("#table_url_fqj").table(rows);
		//初始化分页插件
		$("#tablePaging").tablePaging(1,10,count,"loadUsers");
	});
}
/*按钮渲染*/
function btndos(e){
	var value=e.value;
	var row=e.row;
	return "<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"addClick("+row.id+")\">修改</button>";
}
/*按钮方法*/
function addClick(id){
	var rows=$("#table_url_fqj").getTableNoSelects();
	alert(JSON.stringify(rows));
	//alert(id);
}
loadPaging(1);
</script>		
</body>
</html>