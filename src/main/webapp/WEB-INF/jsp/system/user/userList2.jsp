<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<input class="form-control" name="name">
						<span class="input-group-addon">账号</span>
						<input class="form-control" name="loginName">
						<!-- <span class="input-group-addon" id="basic-addon1">角色</span>
					  	<select class="selectpicker" data-width="100%"  id="usersex">
							<option value="0">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
					 	</select>  -->	
					</div>
					
					<div class="input-group" style="margin-left: 20px;">
						<button type="button" class="btn btn-success" onclick="loadPaging(1)">搜索</button>
						<input type="reset" id="formRest" style="display: none;">
						<button type="button" class="btn btn-success" onclick="resetIntro(1)" style="margin-left: 5px;">重置</button>
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
					<button type="button" class="btn btn-success btm-sm" id="add_id"
						onclick="delUsers()">批量删除</button>		
				</div>
				<div class="panel-body hfit" style="overflow-x: auto;">
					<input type="hidden" id="currentUserId">
					<!-- Table -->
				  	<table class="table table-bordered table-hover"  id="table_users_fqj">
					   <thead>
					      <tr>
					      	 <th style="width: 30px;"><input type="checkbox"></th>
					         <th field="loginName">账号 </th>
					         <th field="name">姓名</th>
					         <th field="seatNumber">工号</th>
					         <th field="restscore">剩余分</th>
					         <th field="createDateFormat">添加时间</th>
					         <th field="lockFormat" render="statusRender">状态</th>
					         <th field="cz" render="btndos">操作</th>
					      </tr>
					   </thead>
					</table>
					<div id="table_Paging_fqj"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js?v=<%=getServletContext().getAttribute("version") %>"></script>
	<script type="text/javascript">

		/* 初始化 start */
		$(function() {
			var divheight = $(window).height();//初始化修改div的高度
			divheight = divheight - 125;
			$(".hfit").css({
				height : divheight
			});
			loadPaging(1);

		});
		/*加载用户*/
		function loadUsers(pageNum){
			var url=rootpath+"/user/queryUsers.shtml";
			var offSet=(pageNum-1)*10;
			var searchData=$("#searchForm").getFormData();
			searchData.offSet=offSet;
			searchData.pageSize=10;
			$.post(url,searchData,function(rows){
				$("#table_users_fqj").table(rows);
				//初始化分页插件
			});
		}
		function loadPaging(pageNum){
			var url=rootpath+"/user/queryUserPager.shtml";
			var offSet=(pageNum-1)*10;
			var searchData=$("#searchForm").getFormData();
			searchData.offSet=offSet;
			searchData.pageSize=10;
			$.post(url,searchData,function(data){
				var rows=data.items;
				var count=data.rowsCount;
				$("#table_users_fqj").table(rows);
				//初始化分页插件
				$("#table_Paging_fqj").tablePaging(1,10,count,"loadUsers");
			});
		}
		/*重置*/
		function resetIntro(){
			$("#searchForm").reset();
			loadPaging(1);
		}
		/*状态标签*/
		function statusRender(e){
			var value=e.value;
			var row=e.row;
			var status=row.locked;
			if(status==0){
				return '<span class="label label-success">'+value+'</span>';
			}else if(status==1){
				return '<span class="label label-warning">'+value+'</span>';;
			}
		}
		/*按钮渲染*/
		function btndos(e){
			var row=e.row;
			var btns = new Array();
			var locked=row.locked;
			/**
		     * 0：启用
		     * 1：禁用
		     */
			if(locked==0){
				btns.push("<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"modifyUserState("+row.id+","+row.locked+")\">禁用</button>");
			}else{
				btns.push("<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"modifyUserState("+row.id+","+row.locked+")\">启用</button>");
			}
			btns.push("<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"deleteUser("+row.id+")\">删除</button>");
			btns.push("<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"editUser("+row.id+")\">修改</button>");
			btns.push("<button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"confirmModifyUserRole("+row.id+","+row.locked+")\">分配角色</button>");
			return btns.join("&nbsp;&nbsp;");;
		}
		/*修改用户状态*/
		function modifyUserState(userId){
			var url="${pageContext.request.contextPath}/user/toggleUserStatus.shtml";
			$.post(url,{userId:userId},function(data){
				if(data.status){
					loadPaging(1);
				}
				layer.msg(data.info,{time:800});
			});
		}
		/*判断是否修改 */
		function confirmModifyUserRole(userId,status){
			if(status==0){
				modifyUserRole(userId);
			}else if(status==1){
				layer.confirm('用户已经被禁用确定要分配角色吗？', {
					  btn: ['确定','取消'] //按钮
					}, function(index){
						layer.close(index);
						modifyUserRole(userId);
					}, function(){
				});
			}
			
		}
		/*保存修改后的角色*/
		function modifyUserRole(userId){
			layer.open({
			      type: 2,
			      title: '分配角色',
			      shadeClose: true,
			      shade: false,
			      area: ['450px', '600px'],
			      content: '${pageContext.request.contextPath}/user/addRole.shtml?userId='+userId,
			      btn: ['确定','取消'], //按钮
			      yes: function(index, layero){
			    	  var ulist = layer.getChildFrame('#list_role_fqj', index);
			    	  var ids=new Array();
			    	  var keys=new Array();
			    	  ulist.children("a").each(function(){
			    		  var ischoose=$(this).children("span").is("span");
			    		  if(ischoose){
			    			  ids.push($(this).attr("data-id"));
                              keys.push($(this).attr("data-key"));
			    		  }
			    		  
			    	  });
			    	  var url="${pageContext.request.contextPath}/user/saveUserRoleModify.shtml";
		    		  $.post(url,{userId:userId,roleIds:ids.join(","),roleKeys:keys.join(",")},function(result){
		    			  if(result.status){
		    				  layer.msg(result.info);
		    				  setTimeout(function(){
			    				  layer.close(index);
			    				  //modifyUserRole(userId);
		    					  
		    				  },800)
		    			  }else{
		    				  layer.msg("修改失败！");
		    			  }
		    		  });
			    	  
			      },
			      btn2: function(index, layero){
			      }
			  });
		}
		//弹出修改
		function editUser(id){
			layer.open({
			      type: 2,
			      title: '修改用户',
			      /* 20161026  huangLiang 放开遮罩层，防止用户在打开窗口后，再次点击“新增”按钮，多次弹出新增窗口 */
			      /* 
			      shadeClose: true,
			      shade: false,
			       */
			      area: ['580px', '610px'],
			      content: '${pageContext.request.contextPath}/user/retrunUserUpdate.shtml?id='+id,
			      btn: ['确定','取消'], //按钮
			      yes: function(index, layero){
			    	  saveUser(index,layero);
			      },
			      btn2: function(index, layero){
			      }
			  });
		}
		//保存用户
		function saveUser(index,layero){
			var form = layer.getChildFrame('#updataForm', index);
	    	  var formdata=form.getFormData();
	    	  if(form.attr("isSubmit") == true || form.attr("isSubmit") == 'true'){
					layer.alert("请勿重复提交");
					return;
				}
	    	    form.attr("isSubmit",true);
				//验证
				var loginName =formdata.loginName;
				var loginPwd =formdata.loginPwd;
				var seatNumber =formdata.seatNumber;
				var name =formdata.name;
				var phone =formdata.phone;
				var id =formdata.id;
				var orgId =formdata.orgId;
				
				var ret = true;//默认是账号不存在
				if(loginName == null || loginName == ''){
					layer.alert("请输入账号");
					form.attr("isSubmit",false);
					return;
				}else if(!id){//如果为修改就不进，新增进入
					$.ajax({
							url:"${pageContext.request.contextPath}/user/checkLoginName.shtml",
							data:{loginName:loginName},
							async:false,
							success:function(data){
								if(data.status){
									ret = data.status;
								}
							}
					});
					
				}
				if(!ret){
					layer.alert("账号已存在");
					form.attr("isSubmit",false);
					return;
				}
				
				
				
				if(loginPwd == null || loginPwd == ''){
					layer.alert("请输入登录密码");
					form.attr("isSubmit",false);
					return;
				}
				
				if(seatNumber == null || seatNumber == ''){
					layer.alert("请输入工号");
					form.attr("isSubmit",false);
					return;
				}
				
				if(name == null || name == ''){
					layer.alert("请输入姓名");
					form.attr("isSubmit",false);
					return;
				}
				
				if(phone != null && phone != ''){
					var mobileRegx = /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
			        if (!mobileRegx.test(phone)) {
			            layer.alert('请输入正确的手机号');
			            form.attr("isSubmit",false);
			            return;
			        }
				}
				
				if(orgId == null || orgId == ''){
					layer.alert("请输入机构");
					form.attr("isSubmit",false);
					return;
				}
				
				var url = "${pageContext.request.contextPath}/user/saveOrUpdate.shtml";
				
				$.post(url,formdata,function(data){
					if(data.status){
						loadPaging(1);
						layer.msg(data.info,{time:800},function(){
							layer.close(index);
						});
					}else{
						form.attr("isSubmit",false);
						layer.msg(data.info,{time:800});
					}
				},'json');
		}
		
		//删除
		function  deleteUser(id){
			layer.confirm('确定删除用户吗？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.post("${pageContext.request.contextPath}/user/delectUser.shtml",{id:id},function(data){
						if(data.status){
							layer.msg(data.info,{time:1000},function(){
								loadPaging(1);
							});
						}
					});
				}, function(){
				  layer.msg('已取消！');
				});
			
			
		}
		//批量删除
		function  delUsers(){
			var rows=$("#table_users_fqj").getTableSelects();
			var ids=new Array();
			for(var i=0;i<rows.length;i++){
				var id=rows[i].id;
				ids.push(id);
			}
			layer.confirm('确定删除批量用户吗？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.post("${pageContext.request.contextPath}/user/delectUsers.shtml",{ids:ids},function(data){
						if(data.status){
							layer.msg(data.info,{time:1000},function(){
								loadPaging(1);
								return ;
							});
						}
					});
				}, function(){
				  layer.msg('已取消！');
				});
			
			
		}
		
		//新增
		function addUser(){
			layer.open({
			      type: 2,
			      title: '新增用户',
				  /* 20161026  huangLiang 放开遮罩层，防止用户在打开窗口后，再次点击“新增”按钮，多次弹出新增窗口 */
			      /* 
			      shadeClose: true,
			      shade: false,
			       */
			      area: ['580px', '610px'],
			      content: '${pageContext.request.contextPath}/user/retrunUserAdd.shtml',
			      btn: ['确定','取消'], //按钮
			      yes: function(index, layero){
			    	  saveUser(index,layero);
			      },
			      btn2: function(index, layero){
			      }
			  });
		}
	</script>
	
</body>
</html>
