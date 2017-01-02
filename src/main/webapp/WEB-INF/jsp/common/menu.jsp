<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" style="padding: 2px 39px;" href="${pageContext.request.contextPath}/index.shtml"><img
			alt="交易网" style="height: 45px;"
			src="${pageContext.request.contextPath}/images/logo.png"></a>
	</div>
	<ul class="nav navbar-left navbar-top-links index_topMenu" id="topMenu">
		<c:forEach items="${menu }" var="data">
			<li><a href="javascript:void(0)"
				onclick="index_changeMenu(this);" data-id="${data.id }">${data.name }</a></li>
		</c:forEach>

	</ul>
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages">
				<li><a href="#">
						<div>
							<strong>陈好</strong> <span class="pull-right text-muted"> <em>昨天</em>
							</span>
						</div>
						<div>商铺已经上线了，请查看详情...</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>查看所以通知
					</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul></li>
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-tasks">
				<li><a href="#">
						<div>
							<p>
								<strong>任务 1</strong> <span class="pull-right text-muted">完成
									40%</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% 完成 (成功)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>查看所以的任务</strong>
						<i class="fa fa-angle-right"></i>
				</a></li>
			</ul></li>
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li><a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> 新的评论
							<span class="pull-right text-muted small">4 分钟前</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>查看所以提醒</strong>
						<i class="fa fa-angle-right"></i>
				</a></li>
			</ul>
		</li>
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-user fa-fw"></i>${sessionScope.user.name }
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user" style="min-width: 130px;">
				<li><a href="javascript:void(0);" onclick="clickMenu(this);"
					aurl="${pageContext.request.contextPath}/user/returnUserDetail.shtml?id=${sessionScope.user.id }"><i
						class="fa fa-user fa-fw"></i>个人资料</a></li>
				<li><a href="javascript:void(0);" onclick="userUpdatePwd()"><i class="fa fa-unlock-alt fa-fw"></i>修改密码</a></li>
				<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a></li> -->
				<li class="divider"></li>
				<li><a href="loginOut.shtml"><i
						class="fa fa-sign-out fa-fw"></i> 注销</a></li>
			</ul>
		</li>
	</ul>

	<div class="sidebar" role="navigation" id="rightMenu">
		<div class="sidebar-nav navbar-collapse">
			<h2 id="testnav" style="color: #fff; text-align: center;"></h2>
		</div>
	</div>
</nav>

<script type="text/javascript">
//用户修改密码
function userUpdatePwd(){
	//var id = $("#updataForm").find("input[name='id']").val();
	var id = "${sessionScope.user.id}";
	$.post("${pageContext.request.contextPath}/user/retrunUserUpdatePwd.shtml",{id:id},function(data){
		var index = layer.open({
			title:"修改密码",
			content:data,
			scrollbar:false,
			move:false,
			area:'500px',
			type:1,
			btn: ['确定','取消'], //按钮
			yes: function(index, layero){
				saveUserPwd(layero);
			},
			btn2: function(index, layero){
			}
		});
		
		$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
		$(".myCloseZ").on('click',function(){
			layer.close(index);
		});
	},'html');
}
</script>
 