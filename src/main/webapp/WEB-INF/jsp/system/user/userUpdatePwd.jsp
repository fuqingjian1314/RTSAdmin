<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default" style="padding: 10px 30px;border: none;">
	<form id="userUpdataForm" class="form-horizontal">
		<div class="form-group col-md-14">
		  <label>账号</label>
		  <input type="text" name="loginName" class="form-control" placeholder="账号" value="${adminUser.loginName}" disabled="disabled">
		</div>
		<div class="form-group col-md-14">
		  <label>原密码</label>
		  <input type="password" name="oldLoginPwd" class="form-control" placeholder="原密码" value="">
		</div>
		<div class="form-group col-md-14">
		  <label>新密码</label>
		  <input type="password" name="newLoginPwd" class="form-control" placeholder="新密码" value="">
		</div>
		<div class="form-group col-md-14">
		  <label>确认密码</label>
		  <input type="password" name="loginPwd" class="form-control" placeholder="确认密码" value="">
		</div>
		<input type="hidden" value="${adminUser.id}" name="id">
		<%-- <div class="form-group col-md-14" style="text-align: right;">
			<button type="button" class="btn btn-danger" onclick="saveUserPwd(this)" isSubmit="false">确定</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-danger" onclick="cancel()">取消</button>		
		</div> --%>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$("#userUpdataForm").find("input[name='oldLoginPwd']").val(null);
	});
	
	function getFromData(formId){
		var data = {};
		$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
		console.log("");
		return data;
	} 
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	//保存密码
	function saveUserPwd(obj){
		
		var oldLoginPwd = $("#userUpdataForm").find("input[name='oldLoginPwd']").val();
		var newLoginPwd = $("#userUpdataForm").find("input[name='newLoginPwd']").val();
		var loginPwd = $("#userUpdataForm").find("input[name='loginPwd']").val();
		var id = $("#userUpdataForm").find("input[name='id']").val();
		
		var oldPwd = "${adminUser.loginPwd}";
		
		if(oldLoginPwd == null || oldLoginPwd == ''){
			layer.alert("请输入原密码");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(newLoginPwd == null || newLoginPwd == ''){
			layer.alert("请输入新密码");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(newLoginPwd == oldLoginPwd){
			layer.alert("新密码与原密码相同");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(loginPwd == null || loginPwd == ''){
			layer.alert("请输入确认密码");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		var ret = false;
		$.ajax({
			url:"${pageContext.request.contextPath}/user/verificationPwd.shtml",
			data:{oldLoginPwd:oldLoginPwd,id:id},
			async:false,
			success:function(data){
				if(data.status){
					ret = data.status;
//					alert(ret);
				}
			}
		});
		if(!ret){
			layer.alert("原密码输入错误");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(loginPwd != newLoginPwd){
			layer.alert("确认密码和新密码输入不一致");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		var queryparams=getFromData("userUpdataForm");
		var url = "${pageContext.request.contextPath}/user/saveOrUpdate.shtml";
		
		$.post(url,queryparams,function(data){
			if(data.status){
				layer.msg(data.info,{time:1000});
				$(".myCloseZ").click();
				//调用注销方法
				location.href = "${pageContext.request.contextPath}/loginOut.shtml";
			}else{
				layer.msg(data.info,{time:1000});
				$(obj).attr("isSubmit",false);
			}
		},'json');
	}
	
	//取消
	function cancel(){
		layer.close(1);
	}
	
</script>