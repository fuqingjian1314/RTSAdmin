<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常日志</title>
<jsp:include page="../../common/adminContentHead.jsp" />
</head>
<body>
<div class="well well-sm" id="searchIntro">
    <form id="searchForm" class="form-inline" action="${pageContext.request.contextPath}/goods/goodsList.shtml" method="get">
		<div class="row">
			<div class="form-group  col-md-2">
		    				<div class="input-group">
				  <span class="input-group-addon">异常时间</span>
				  <input type="text" name="occurStartTime" class="form-control Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})" value="<fmt:formatDate value="${param.occurStartTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="开始日期...">
				</div>
			</div>
			<div class="form-group  col-md-2">
				<div class="input-group">
				  <input type="text" name="occurEndTime" class="form-control Wdate" onFocus="WdatePicker({lang:'zh-cn',readOnly:true})" value="<fmt:formatDate value="${param.occurEndTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="结束日期..." >
				</div>
			</div>
			<div class="form-group  col-md-2">
   				<div class="input-group">
   					<span class="input-group-addon">访问者</span>
				  	<input type="text" class="form-control" name="createby" value="${param.createby}" placeholder="访问者...">
				</div>
			</div>
			<div class="form-group  col-md-2" style="text-align: center">
				<button type="button" class="btn btn-success" onclick="searchIntro()" >搜索</button>
			 		<input type="reset" class="btn btn-success" onclick="resetIntro()" value="重置">
			</div>
  		</div>
  	</form>
 </div>
<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 5px 15px;">异常日志列表</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;">
					<!-- Table -->
				  	<table class="table table-bordered table-hover"  id="table_url_fqj">
					   <thead>
					      <tr>
					      	 <th style="width: 30px;display: none;"><input type="checkbox"></th>
					         <th field="description" style="min-width: 80px;">描述</th>
					         <th field="method">方法</th>
					         <th field="exceptionCode">异常编码</th>
					         <th field="createby" style="min-width: 90px;">访问者</th>
					         <th field="formatCreateDate" style="min-width: 100px;">异常时间</th>
					         <th field="requestIp">访问IP</th>
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
<script src="${pageContext.request.contextPath}/plugins/My97DatePicker/WdatePicker.js?v=<%=getServletContext().getAttribute("version") %>" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	var divheight=$(window).height();//初始化修改div的高度
	divheight=divheight-100;
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

/*加载用户*/
function loadPager(pageNum){
	var url="${pageContext.request.contextPath}/syslog/queryExecLogList.shtml";
	var offSet=(pageNum-1)*10;
	$.post(url,{offSet:offSet,pageSize:10,logType:1},function(rows){
		$("#table_url_fqj").table(rows);
		//初始化分页插件
		//隐藏多选列
		$('table tr').find('td:eq(0)').hide();
	});
}
function loadPaging(pageNum){
	var url="${pageContext.request.contextPath}/syslog/queryExecLogPager.shtml";
	
	var date=getFromData("searchForm");
	date.offSet=(pageNum-1)*10;;
	date.pageSize=10;
	date.logType=1; 
	$.post(url,date,function(data){
		var rows=data.items;
		var count=data.rowsCount;
		$("#table_url_fqj").table(rows);
		//初始化分页插件
		$("#tablePaging").tablePaging(1,10,count,"loadPager");
		//隐藏多选列
		$('table tr').find('td:eq(0)').hide();
	});
}
/*按钮渲染*/
function btndos(e){
	var value=e.value;
	var row=e.row;
	return "<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"detail('"+row.id+"')\">详情</button>";
}
/*按钮方法*/
function detail(id){
	$.post("${pageContext.request.contextPath}/syslog/queryLogDetailById.shtml",{id:id},function(data){
		openLayerModel("异常详情",data);
	},'html');
}

loadPaging(1);

//弹窗(返回后操作分页数据)
function openLayerModel(title,html){
	var index = layer.open({
		title:title,
		content:html,
		scrollbar:false,
		move:false,
		area:'850px',
		type:1
	});
	
	$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
	$(".myCloseZ").on('click',function(){
		layer.close(index);
	});
}
</script>		
</body>
</html>