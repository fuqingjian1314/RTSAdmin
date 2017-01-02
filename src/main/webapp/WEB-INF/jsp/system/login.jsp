<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>交易网后台登录</title>
	<jsp:include page="../common/adminHead.jsp" />
</head>

<body>

    <div class="container" style="margin-top: 50px;">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">交易网后台系统</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="${pageContext.servletContext.contextPath}/loginSystem.shtml" method="POST" id="loginForm">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="账号" name="loginName" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" name="loginPwd"  type="password">
                                </div>
                                <div class="form-group">
				    				<input type="text" name="securitycode" class="form-control" style="width: 150px;float: left;" id="securitycode"/>
				    				<div style="float: right;">
										<img src="${pageContext.servletContext.contextPath}/ValidateCode/validate.shtml" id="validate"  onclick="changeValidateCode()"/>
										<span style="font-size: 12px;color: #656565;cursor: pointer;" onclick="changeValidateCode()">看不清？</span>
									</div>
									<div style="clear: both;"></div>
				    			</div>
                                <a href="javascript:void(0);" onclick="login()" class="btn btn-lg btn-success btn-block">登录</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	//判断是否为顶层页面
	if (window != top) {
		top.location.href = location.href; 
	}
	
	$(function(){
		//回车事件
		$(document).keydown(function(event){
			//回车键的键值为13
			if(event.keyCode==13){
				//调用登录按钮的登录事件
				login();
			}
		});
	}); 
	
	function login(){
		$("[name='loginPwd']").val($.base64.encode($("[name='loginPwd']").val()));
		var loginName = $("[name='loginName']").val();
		var loginPwd = $("[name='loginPwd']").val();
		var securitycode = $("#securitycode").val();
		if(loginName == null || loginName == ''){
			layer.tips("必填",$("[name='loginName']"),{time:2000,tips:1});
			$("[name='loginPwd']").val("");
			return null;
		}
		if(loginPwd == null || loginPwd == ''){
			layer.tips("必填",$("[name='loginPwd']"),{time:2000,tips:1});
			$("[name='loginPwd']").val("");
			return null;
		}
		if(securitycode == null || securitycode == ''){
			layer.tips("必填",$("#securitycode"),{time:2000,tips:1});
			$("[name='loginPwd']").val("");
			return null;
		}
		var flag = checkValidateCode(securitycode);
		if(flag==false || flag == 'false' ){
			$("[name='loginPwd']").val("");
			return ;
		}
		var index = layer.load(2,{time:10000});
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/loginSystem.shtml",
			data:$("#loginForm").serialize(),
			type:"post",
			dataType:"json",
			success:function(data){
				layer.close(index);
				 if(!data.status){
					 if(data.code == 1 ){
						 layer.tips(data.info,$("[name='loginName']"),{time:2000,tips:1});
					 }else if(data.code == 2){
						 layer.tips(data.info,$("[name='loginPwd']"),{time:2000,tips:1});
					 }
					 return;
				 }else{
					 window.location.href="${pageContext.servletContext.contextPath}/index.shtml"; 
				 }
			}
		});
	}
	
	function checkValidateCode(code){
		var returnValue=false;
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/checkValidateCode.shtml",
			data:{code:code},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(!data.status){
					layer.tips(data.info,$("#securitycode"),{time:2000,tips:1});
				}
				returnValue = data.status;
			}
		});
		return returnValue;
	}
	
	/**
	 * 验证码改变
	 * @param obj
	 */
	function changeValidateCode() {
		var timeNow = new Date().getTime();
		var obj = $("#validate")[0];
		obj.src = $(obj).attr("src") + "?time=" + timeNow;
	}
</script>
</html>
