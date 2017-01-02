<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../common/adminContentHead.jsp" />
<title>交易网后台-用户详情</title>
<style type="text/css">
.center {text-align: center !important;}
.profile-picture {border: 1px solid #CCC;background-color: #FFF;padding: 4px;display: inline-block;max-width: 100%;-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;box-sizing: border-box;box-shadow: 1px 1px 1px rgba(0, 0, 0, .15);}
.tableClass_wj tr {height: 50px;font-size: 14px;}
.tableClass_wj tr td {height: 50px;vertical-align: middle;font-size: 14px;}
.tableClass_wj .bordertr td {height: 50px;vertical-align: middle;font-size: 14px;border-bottom: #ccc solid 1px;}
.tableClass_wj .input {width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;
	background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;}
.space-4 {max-height: 1px;min-height: 1px;overflow: hidden;margin: 12px 0;margin: 4px 0 3px;}
.space {max-height: 1px;min-height: 1px;overflow: hidden;margin: 12px 0;}
li{list-style-type: none;}
a,input{outline: none;-webkit-tap-highlight-color:rgba(0,0,0,0);}
#choose{display: none;}
canvas{width: 100%;border: 1px solid #000000;}
#upload{display: block;margin: 10px;height: 40px;line-height:30px;text-align: center;cursor: pointer;border-radius:10px;}
.touch{background-color: #ddd;}
.img-list{margin: 10px 0px 30px 0px;padding: 0px;}
.img-list li{position: relative;display: inline-block;width: 180px;height: 200px;margin: 0px;background: #fff no-repeat center;background-size: cover;border: 1px solid #CCC;}
.progress{position: absolute;width: 100%;height: 20px;line-height: 20px;bottom: 0;left: 0;background-color:rgba(100,149,198,.5);}
.progress span{display: block;width: 0;height: 100%;background-color:rgb(100,149,198);text-align: center;color: #FFF;font-size: 13px;}
.size{position: absolute;width: 100%;height: 15px;line-height: 15px;bottom: -18px;text-align: center;font-size: 13px;color: #666;}
.tips{display: block;text-align:center;font-size: 13px;margin: 10px;color: #999;}
.pic-list{margin: 10px;line-height: 18px;font-size: 13px;}
</style>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">用户信息</div>
				<div class="panel-body hfit" style="height: 600px; overflow-y: auto;">
				<form id="updataForm" class="form-horizontal">
					<input type="hidden" value="${adminUser.id}" name="id">
					<input type="hidden" value="${adminUser.avator }" name="avator" id="avator">
					<div class="col-md-3 center">
						<ul class="img-list">
							<c:if test="${adminUser.avator == null }">
								<li style="background-image: url('${pageContext.request.contextPath}/images/profile-pic.jpg')"></li>
							</c:if>
							<c:if test="${adminUser.avator != null }">
								<li style="background-image: url('${adminUser.avator }')"></li>
							</c:if>
						</ul>
						<input type="file" id="choose" accept="image/*" multiple>
						<a href="#" class="btn btn-sm btn-block btn-success" id="upload" style="width: 80%;margin-left: 10%;"> 
							<i class="ace-icon fa fa-plus-circle bigger-120"></i> 
							<span class="bigger-110">上传照片</span>
						</a>
					</div>
					<!-- <div class="col-md-1"></div> -->
					<div class="col-md-4">
						<table class="tableClass_wj" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="5%">姓名</td>
								<td width="25%">
									<input type="text" name="name" class="form-control" value="${adminUser.name }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td width="5%">登录名</td>
								<td width="25%">
									<input type="text" name="loginName" class="form-control" value="${adminUser.loginName }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td width="5%">性别</td>
								<td width="25%">
											<input type="radio" name="sex" value="男" id="boy" disabled="disabled">男
											<input type="radio" name="sex" value="女" id="gril" disabled="disabled">女
								</td>
							</tr>
							<tr>
								<td width="5%">工号</td>
								<td width="25%">
									<input type="text" name="seatNumber" class="form-control" value="${adminUser.seatNumber }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td width="5%">电话</td>
								<td width="25%">
									<input type="text" name="phone" class="form-control" value="${adminUser.phone }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button type="button" id="save" class="btn btn-default" onclick="saveUser(this);" disabled="disabled">保存</button>
									<button type="button" id ="edit" class="btn btn-default" style="margin-left: 20px;" onclick="updateUser();">修改</button>
									<button type="button" id="editPwd" class="btn btn-default" style="margin-left: 20px;" onclick="editUserPwd(this);">修改密码</button>
								</td>
							</tr>
						</table>
					</div>
				</form>
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
		
		var sex = "${adminUser.sex}";
		if(sex == "男"){
			$("#boy").attr('checked',true)
		}else if(sex == "女"){
			$("#gril").attr('checked',true)
		}
	});
	
	function getFromData(formId){
		var data = {};
		$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
		console.log("");
		return data;
	} 
	
	//修改用户信息
	function updateUser(){
		$("input[name='name']").attr("disabled",false);
		$("input[name='seatNumber']").attr("disabled",false);
		$("input[name='phone']").attr("disabled",false);
		$("#boy").attr("disabled",false);
		$("#gril").attr("disabled",false);
		//保存按钮不禁用
		$("#save").attr("disabled",false);
		//修改按钮禁用
		$("#edit").attr("disabled",true);
	}
	
	//保存用户信息
	function saveUser(obj){
		if($(obj).attr("isSubmit") == true || $(obj).attr("isSubmit") == 'true'){
			layer.alert("请勿重复提交");
			return;
		}
		
		$(obj).attr("isSubmit",true);
		//验证
		var seatNumber = $("#updataForm").find("input[name='seatNumber']").val();
		var name = $("#updataForm").find("input[name='name']").val();
		var phone = $("#updataForm").find("input[name='phone']").val();
		var id = $("#updataForm").find("input[name='id']").val();
		var sex = $('input[name="sex"]:checked').val();
		
		if(sex == null || sex == ''){
			layer.alert("请选择性别");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(seatNumber == null || seatNumber == ''){
			layer.alert("请输入工号");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(name == null || name == ''){
			layer.alert("请输入姓名");
			$(obj).attr("isSubmit",false);
			return;
		}
		
		if(phone != null && phone != ''){
			var mobileRegx = /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
	        if (!mobileRegx.test(phone)) {
	            layer.alert('请输入正确的手机号');
	            $(obj).attr("isSubmit",false);
	            return;
	        }
		}
		var queryparams=getFromData("updataForm");
		var url = "${pageContext.request.contextPath}/user/saveOrUpdate.shtml";
		
		$.post(url,queryparams,function(data){
			if(data.status){
				$(".myCloseZ").click();
				layer.msg(data.info,{time:1000});
			}else{
				$(obj).attr("isSubmit",false);
				layer.msg(data.info,{time:1000});
			}
		},'json');
	}
	
	//修改密码
	function editUserPwd(obj){
		var id = $("#updataForm").find("input[name='id']").val();
		$.post("${pageContext.request.contextPath}/user/retrunUserUpdatePwd.shtml",{id:id},function(data){
			openLayerModel("修改密码",data);
		},'html');
	}
	
	//弹窗
	function openLayerModel(title,html){
		var index = layer.open({
			title:title,
			content:html,
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
	}
	
	var filechooser = document.getElementById("choose");
	//    用于压缩图片的canvas
	var canvas = document.createElement("canvas");
	var ctx = canvas.getContext('2d');
	//    瓦片canvas
	var tCanvas = document.createElement("canvas");
	var tctx = tCanvas.getContext("2d");
	var maxsize = 100 * 1024;
	$("#upload").on("click", function() {
		filechooser.click();
	});
	filechooser.onchange = function() {
		if (!this.files.length)
			return;
		var files = Array.prototype.slice.call(this.files);
		files.forEach(function(file, i) {
			if (!/\/(?:jpeg|png|gif)/i.test(file.type))
				return;
			var reader = new FileReader();
			var li = document.createElement("li");
			// 获取图片大小
			var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024))
					/ 10 + "MB"
					: ~~(file.size / 1024) + "KB";
			//li.innerHTML = '<div class="progress"><span></span></div><div class="size">' + size + '</div>';
			$(".img-list").html($(li));
			reader.onload = function() {
				var result = this.result;
				var img = new Image();
				img.src = result;
				$(li).css("background-image", "url(" + result + ")");
				//如果图片大小小于100kb，则直接上传
				if (result.length <= maxsize) {
					img = null;
					upload(result, file.type, $(li));
					return;
				}
				//图片加载完毕之后进行压缩，然后上传
				if (img.complete) {
					callback();
				} else {
					img.onload = callback;
				}
				function callback() {
					var data = compress(img);
					upload(data, file.type, $(li));
					img = null;
				}
			};
			reader.readAsDataURL(file);
		})
	};
	//    使用canvas对大图片进行压缩
	function compress(img) {
		var initSize = img.src.length;
		var width = img.width;
		var height = img.height;
		//如果图片大于四百万像素，计算压缩比并将大小压至400万以下
		var ratio;
		if ((ratio = width * height / 4000000) > 1) {
			ratio = Math.sqrt(ratio);
			width /= ratio;
			height /= ratio;
		} else {
			ratio = 1;
		}
		canvas.width = width;
		canvas.height = height;
		//铺底色
		ctx.fillStyle = "#fff";
		ctx.fillRect(0, 0, canvas.width, canvas.height);
		//如果图片像素大于100万则使用瓦片绘制
		var count;
		if ((count = width * height / 1000000) > 1) {
			count = ~~(Math.sqrt(count) + 1); //计算要分成多少块瓦片
			//计算每块瓦片的宽和高
			var nw = ~~(width / count);
			var nh = ~~(height / count);
			tCanvas.width = nw;
			tCanvas.height = nh;
			for (var i = 0; i < count; i++) {
				for (var j = 0; j < count; j++) {
					tctx.drawImage(img, i * nw * ratio, j * nh * ratio, nw
							* ratio, nh * ratio, 0, 0, nw, nh);
					ctx.drawImage(tCanvas, i * nw, j * nh, nw, nh);
				}
			}
		} else {
			ctx.drawImage(img, 0, 0, width, height);
		}
		//进行最小压缩
		var ndata = canvas.toDataURL('image/jpeg', 0.1);
		tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;
		return ndata;
	}
	//图片上传，将base64的图片转成二进制对象，塞进formdata上传
	function upload(basestr, type, $li) {
		var text = window.atob(basestr.split(",")[1]);
		var buffer = new Uint8Array(text.length);
		var pecent = 0, loop = null;
		for (var i = 0; i < text.length; i++) {
			buffer[i] = text.charCodeAt(i);
		}
		var xhr = new XMLHttpRequest();
		var fileObj = document.getElementById("choose").files[0]; // 获取文件对象  
		var formdata = new FormData();
		formdata.append('file', fileObj);
		xhr.open('post', '${pageContext.request.contextPath}/fileUpload/uploadImg.shtml', true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var jsonData = JSON.parse(xhr.responseText);
				var data = JSON.parse(jsonData.data);
				
				var imagedata = jsonData[0] || {};
				var text = jsonData.info ? '上传成功' : '上传失败';
				$("#avator").val(data.url);
				updateUser();
				_image_addredd = data.url;
				clearInterval(loop);
				if (!imagedata.path)
					return;
			}
		};
		xhr.send(formdata);
	}
	
	</script>
</body>
</html>