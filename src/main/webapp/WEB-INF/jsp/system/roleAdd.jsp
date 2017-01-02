<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="submitForm" style="margin-top: 25px;" action="${pageContext.request.contextPath}/admin/role/save.shtml" method="post">
	<input type="hidden" name="id" value="${role.id }">
  <div class="form-group col-md-12">
    <label>菜单名称</label>
    <input type="text" name="name" id="roleName" class="form-control"  placeholder="菜单名称" value="${role.name }">
  </div>
  <div class="form-group col-md-12" >
    <label>KEY</label>
    <input type="text" name="roleKey" id="roleKey" class="form-control"  placeholder="菜单KEY"  value="${role.roleKey }">
  </div>
  <div class="form-group col-md-12">
    <label>描述</label>
    <input type="text" name="description" id="roleDesc" class="form-control"  placeholder="菜单描述"  value="${role.description }">
  </div>
  <div class="form-group col-md-12" style="text-align: right;">
	  <button type="button" class="btn btn-success" onclick="submitForm(this);" isSubmit="false" aurl="${pageContext.request.contextPath }/admin/role/redirect.shtml" >保存</button>
	  <button type="reset" class="btn btn-success">重置</button>
  </div>
</form>

<script type="text/javascript">
	
	//添加 或 修改
	function submitForm(obj){
		var isSubmit = $(obj).attr("isSubmit");
		//重复提交
		if(isSubmit == true || isSubmit == 'true'){
			layer.alert("请勿重复提交！");
			return ;
		}
		
		//验证
		var name = $("#submitForm").find("input[name='name']").val();
		var roleKey = $("#submitForm").find("input[name='roleKey']").val();
		var id = $("#submitForm").find("input[name='id']").val();
		
		
		if(name == null || name == ''){
			layer.tips("请输入名称","#roleName");
			return;
		}
		if(roleKey == null || roleKey == ''){
			layer.tips("请输入KEY","#roleKey");
			return;
		}
		if(!isEnableKey(roleKey,id)){
			layer.tips("Key已存在","#roleKey");
			return;
		}
		$(obj).attr("isSubmit","true");
		
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/role/save.shtml",
			type:"post",
			data:$("#submitForm").serialize(),
			success:function(data){
				if(data.status){
					$(".myCloseZ").click();
					layer.msg(data.info,{time:1000},function(){
						return ;
    				});
				}else{
					layer.msg(data.info,{time:1000},function(){
						$(obj).attr("isSubmit","false");
						return ;
    				});
				}
			}
		});
	}
	//key可用返回：true，否则返回false
	function isEnableKey(roleKey,id){
		
		var flag=false;
		
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/role/checkRoleKey.shtml",
			type:"post",
			async: false,
			data:{
				roleKey:roleKey,
				id:id
			},
			success:function(data){
				flag=data.status;
			}
		});
		return flag;
	}
</script>