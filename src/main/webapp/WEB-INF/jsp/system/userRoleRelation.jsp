<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户-角色-关系</title>
<jsp:include page="../common/adminContentHead.jsp" />
<style type="text/css">
	.border-red{border-left:1px solid #cccccc;height: 800px;margin-top: 30px;}

</style>
</head>
<body>
	<div class="row">
		<!-- 左侧DIV,加载用户数据 -->
		<div class="col-xs-12 border-red">
		<div style="margin-bottom: 5px;"><a class="btn btn-default" onclick="show(this);">添加启用</a></div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>表头</th>
						<th>b</th>
						<th>c</th>
						<th>d</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>表body</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
					<tr>
						<td>用户</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
					<tr>
						<td>用户</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
					<tr>
						<td>用户</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
					<tr>
						<td>用户</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>表footer</td>
						<td>b</td>
						<td>c</td>
						<td>d</td>
					</tr>
				
				</tfoot>
			</table>
		<div style="margin-top: 10px;">
			<a class="btn btn-default">首页</a>
			<a class="btn btn-default">上一页</a>
			<a class="btn btn-default">1</a>
			<a class="btn btn-default">2</a>
			<a class="btn btn-default">3</a>
			<a class="btn btn-default">4</a>
			<a class="btn btn-default">下一页</a>
			<a class="btn btn-default">末页</a>
			
		</div>
		</div>
		<!-- 右侧DIV,加载用户对应的角色数据 -->
		<div class="col-xs-5 border-red" style="display: none;" id="right">
			<div style="margin-bottom: 5px;"><a class="btn btn-default" onclick="openLayerModel('添加角色')">添加启用</a></div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>角色名</th>
						<th>角色key</th>
						<th>operator</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>管理员</td>
						<td>001</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>薪酬专员</td>
						<td>002</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
					<tr>
						<td>a</td>
						<td>b</td>
						<td>c</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
					<tr>
						<td>a</td>
						<td>b</td>
						<td>c</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
					<tr>
						<td>a</td>
						<td>b</td>
						<td>c</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>表footer</td>
						<td>b</td>
						<td>c</td>
						<td><a class="btn btn-default">启用</a></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
<div id="xxx" style="margin-bottom: 5px;">
	<div><a class="btn btn-default">添加启用</a></div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>角色名</th>
				<th>角色key</th>
				<th>operator</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>管理员</td>
				<td>001</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td>薪酬专员</td>
				<td>002</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
			<tr>
				<td>a</td>
				<td>b</td>
				<td>c</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
			<tr>
				<td>a</td>
				<td>b</td>
				<td>c</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
			<tr>
				<td>a</td>
				<td>b</td>
				<td>c</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td>表footer</td>
				<td>b</td>
				<td>c</td>
				<td><a class="btn btn-default">启用</a></td>
			</tr>
		
		</tfoot>
	</table>
	<div style="margin-top: 10px;">
		<a class="btn btn-default">首页</a>
		<a class="btn btn-default">上一页</a>
		<a class="btn btn-default">1</a>
		<a class="btn btn-default">2</a>
		<a class="btn btn-default">3</a>
		<a class="btn btn-default">4</a>
		<a class="btn btn-default">下一页</a>
		<a class="btn btn-default">末页</a>
		
	</div>
</div>
</body>
<script type="text/javascript">
	function openLayerModel(title){
		var index = layer.open({
			title:title,
			content:$("#xxx"),
			scrollbar:false,
			move:false,
			area:['760px','530px'],
			type:1
		});
		
		$(".layui-layer-setwin .layui-layer-close1").addClass("myCloseZ");
		$(".myCloseZ").on('click',function(){
			layer.close(index);
			getPager(0);
		});
		
	}
	
	function show(obj){
		$(obj).parent().parent().removeClass("col-xs-12").addClass("col-xs-7");
		$("#right").css("display","block");
	}

</script>

</html>