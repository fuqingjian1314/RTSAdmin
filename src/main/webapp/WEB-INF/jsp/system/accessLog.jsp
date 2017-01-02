<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<title>交易网后台首页-访问日志</title>
<style type="text/css">
.row {
    margin-right: 0px;
    margin-left: 0px;
}
</style>
</head>
<body>
	<!-- begin搜索 -->
	<div class="well well-sm" id="searchIntro">
		<form id="searchForm" class="form-inline">
			<div class="row">
				<div class="form-group  col-md-12">
					<div class="input-group">
						<span class="input-group-addon">登录名</span>
						<input class="form-control" name="lrLoginName" value="${lrLoginName }">
						<%-- <span class="input-group-addon">账号</span>
						<input class="form-control" name="loginName" value="${loginName }"> --%>
						<!-- <span class="input-group-addon" id="basic-addon1">角色</span> -->
					</div>
					
					<div class="input-group" style="margin-left: 20px;">
						<button type="button" class="btn btn-danger" onclick="searchIntro()">搜索</button>
						<input type="reset" id="formRest" style="display: none;">
						<button type="button" class="btn btn-danger" onclick="resetIntroL()" style="margin-left: 5px;">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="col-md-12" id="userList">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 5px 15px;">访问日志列表(当前在线人数：<a href="javascript:void(0);" onclick="onlineUsers(0)">${sessionCount }</a>)</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;">
					<input type="hidden" id="currentUserId">
					<div id="lrcontent_div_wlj"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	var url = "${pageContext.request.contextPath}/loginRecord/queryLoginRecordList.shtml";
	
	/* 初始化 start */
	$(function() {
		var divheight = $(window).height();//初始化修改div的高度
		divheight = divheight - 115;
		$(".hfit").css({
			height : divheight
		});
	});
	
	function getFromData(formId) {
		var data = {};
		$("#" + formId).serializeArray().map(function(x) {
			data[x.name] = x.value;
		});
		return data;
	}

	function getPager(offset ) {
		var queryparams = getFromData("searchForm");
		queryparams.curIndex = offset;
		queryparams.requestMethod = "getPager";
		queryparams.begin = $("#pageBegin").val();
		queryparams.end = $("#pageEnd").val();
		//alert(JSON.stringify(queryparams));
		//var url = "${pageContext.request.contextPath}/loginRecord/queryLoginRecordList.shtml";
		$.post(url, queryparams, function(data) {
			$("#lrcontent_div_wlj").html(data);
		}, 'html');
	}
	getPager(0);
	
	//搜索方法
	function searchIntro() {
		getPager(0);
	}

	//重置搜索条件
	function resetIntroL() {
		$("#formRest").click();
		//设置默认的按钮数
		$("#pageEnd").val(4);
		getPager(0);
	}
	
	/*在线人数*/
	function onlineUsers(offset){
		$.post("${pageContext.request.contextPath}/loginRecord/queryOnlineUsers.shtml",function(data){
			openLayerModel("当前在线人员",data);
		},'html');
	}
	
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