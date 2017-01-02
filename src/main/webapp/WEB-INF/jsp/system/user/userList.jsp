<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../common/adminContentHead.jsp" />
<title>交易网后台首页</title>
</head>
<body>
	<!-- begin搜索 -->
	<div class="well well-sm" id="searchIntro">
		<form id="searchForm" class="form-inline">
			<div class="row">
				<div class="form-group  col-md-12">
					<div class="input-group">
						<span class="input-group-addon">姓名</span>
						<input class="form-control" name="name" value="${name }">
						<span class="input-group-addon">账号</span>
						<input class="form-control" name="loginName" value="${loginName }">
						<!-- <span class="input-group-addon" id="basic-addon1">角色</span>
					  	<select class="selectpicker" data-width="100%"  id="usersex">
							<option value="0">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
					 	</select>  -->	
					</div>
					
					<div class="input-group" style="margin-left: 20px;">
						<button type="button" class="btn btn-success" onclick="searchIntro()">搜索</button>
						<input type="reset" id="formRest" style="display: none;">
						<button type="button" class="btn btn-success" onclick="resetIntroL()" style="margin-left: 5px;">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="col-md-12" id="userList">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 5px 15px;">
					<button type="button" class="btn btn-success btm-sm" id="add_id"
						onclick="addUser()">新增</button>
				</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;">
					<input type="hidden" id="currentUserId">
					<div id="usercontent_div_wlj"></div>
				</div>
			</div>
		</div>
		<!-- 右侧DIV,加载用户对应的角色数据 -->
		<div class="col-md-5" style="display: none;" id="rightRole">
			<div class="panel panel-default">
				<div class="panel-heading">
					<button type="button" class="btn btn-success btm-sm" onclick="openAddRoleDialog()">添加角色</button>
					<a class="btn btn-default" href="javascript:void(0)" style="float: right" onclick="closeRoleList()" aurl=""><span class="glyphicon glyphicon-remove"></span></a>
				</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;" id="rightRole_List">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">

		/* 初始化 start */
		$(function() {
			var divheight = $(window).height();//初始化修改div的高度
			divheight = divheight - 150;
			$(".hfit").css({
				height : divheight
			});

		});
		function getFromData(formId) {
			var data = {};
			$("#" + formId).serializeArray().map(function(x) {
				data[x.name] = x.value;
			});
			console.log("");
			return data;
		}

		function getPager(offset) {
			var queryparams = getFromData("searchForm");
			queryparams.curIndex = offset;
			queryparams.requestMethod = "getPager";
			queryparams.begin = $("#pageBegin").val();
			queryparams.end = $("#pageEnd").val();
			var url = "${pageContext.request.contextPath}/user/queryUserList.shtml";
			$.post(url, queryparams, function(data) {
				$("#usercontent_div_wlj").html(data);
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
			getPager(0);
		}
		
		//修改
		function editUser(obj){
			var id = $(obj).attr("data-id");
			$.post("${pageContext.request.contextPath}/user/retrunUserUpdate.shtml",{id:id},function(data){
				openLayerModel("修改用户",data);
			},'html');
		}
		
		//删除
		function  deleteUser(obj){
			var id = $(obj).attr("data-id");
			$.post("${pageContext.request.contextPath}/user/delectUser.shtml",{id:id},function(data){
				if(data == "false"){
					 $("#mymodal").modal("toggle");
				}else{
					layer.msg(data.info,{time:1000},function(){
						getPager(0);
						return ;
					});
				}
			});
			
		}
		
		//添加
		function addUser(){
			$.ajax({
				url:"${pageContext.request.contextPath}/user/retrunUserAdd.shtml",
				type:"post",
				success:function(data){
					openLayerModel("添加用户",data);
				}
			});
		}
		
		//弹窗(返回后操作分页数据)
		function openLayerModel(title,html){
			if(html == "false"){
				$("#mymodal").modal("toggle");
			}else{
				var index = layer.open({
					title:title,
					content:html,
					scrollbar:false,
					move:false,
					area:'500px',
					type:1
				});
				
				$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
				$(".myCloseZ").on('click',function(){
					layer.close(index);
					getPager(0);
				});
			}
		}
		
		//指派角色按钮单击事件
		function managerRole(obj){
			$("#userList").removeClass("col-md-12").addClass("col-md-7");
			$("#rightRole").css("display","block");
			//$(obj).parent("tr").css("background-color","#f5f5f5");
			var userId = $(obj).attr("data-id");
			$("#currentUserId").val(userId);
			loadUserRole();
		}
		
		//关闭角色信息
		function closeRoleList(){
			$("#userList").removeClass("col-md-7").addClass("col-md-12");
			$("#rightRole").css("display","none");
		}
		
		
		//加载用户对应的角色
		function loadUserRole(){
			var userId = $("#currentUserId").val();
			if(userId == ''){
				return ;
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/user/userRoleList.shtml",
				data:{userId:userId},
				type:"post",
				success:function(data){
					$("#rightRole_List").empty();
					$("#rightRole_List").append(data);
				}
			});
		}
		
		//删除已给用户指派的角色
		function deleteUserRole(obj){
			var id = $(obj).attr("data-id");
			$.ajax({
				url:"${pageContext.request.contextPath}/user/deleteUserRole.shtml",
				dataType:'json',
				type:'post',
				data:{id:id},
				success:function(data){
					layer.msg(data.info,{time:1000});
					loadUserRole();
				}
			});
		}
		
		//打开添加角色dialog
		function openAddRoleDialog(){
			var userId = $("#currentUserId").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/user/addRole.shtml",
				data:{userId:userId},
				type:"post",
				success:function(data){
					openLayerModelRole('添加角色',data);
				}
			});
		}
		
		//弹窗（添加角色专用）
		function openLayerModelRole(title,html){
			
			var index = layer.open({
				title:title,
				content:html,
				scrollbar:false,
				move:false,
				area:['760px','530px'],
				yes:function(index, layero){
					addRoleSubmit();
					layer.close(index);
					loadUserRole();
					
				}
			});
			
		}
		
		//选择或取消 角色
		function chooseOrCancel(obj){
			var badge = $(obj).find("span[class='badge']");
			var roleId = $(badge).attr("data-id");
			var isChecked = $(badge).attr("isChecked");
			
			if(isChecked == true || isChecked == 'true'){
				$(badge).attr("isChecked",false);
				$(badge).empty();
			}else{
				$(badge).attr("isChecked",true);
				$(badge).empty();
				$(badge).append('<span class="glyphicon glyphicon-ok myCheckItems" data-id='+roleId+' ></span>');
			}
			
		}
		//选完角色后，确定按钮所完成操作
		function addRoleSubmit(){
			var roleIds = new Array();
			$(".panel .list-group .myCheckItems").each(function(){
				var id = $(this).attr("data-id");
				roleIds.push(id);
			});
			roleIds=roleIds.join(",");
			var uId = $("#uId").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/user/saveRole.shtml",
				data:{
					roleIds:roleIds,
					userId:uId	
				},
				dataType:'json',
				type:'post',
				async:false,
				success:function(data){
					layer.msg(data.info,{time:1000});
				}
			});
		}
	
	</script>
	
</body>
</html>
