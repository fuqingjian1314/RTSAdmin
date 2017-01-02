<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<!-- treegrid树形表单 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/treegrid/css/jquery.treegrid.css?v=<%=getServletContext().getAttribute("version") %>">
<script type="text/javascript" src="${pageContext.request.contextPath}/treegrid/js/jquery.treegrid.js?v=<%=getServletContext().getAttribute("version") %>"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/treegrid/js/jquery.treegrid.bootstrap3.js?v=<%=getServletContext().getAttribute("version") %>"></script>  
<style type="text/css">
.list-group-item
{
    padding: 6px 15px;
}
.list-group-item.active,
.list-group-item.active:hover,
.list-group-item.active:focus {
  z-index: 2;
  color: #fff;
  background-color: #4D4D4E;
  border-color: #4D4D4E;
}
 
a.list-group-item span{
	display:none;
}
a.list-group-item:hover span{
	display:inline;
}
a.list-group-item:hover{
	background: #ccc;
}
</style>
<title>交易网后台首页</title>
</head>    
<body>   
	<div class="row" style="padding-top:5px;">
	  <div class="col-md-12">
	  	<div class="panel panel-default">
  			<div class="panel-heading">
  			栏目列表<button type="button" class="btn btn-success btn-sm" onclick="openDictlayer()" style="float: right;margin-top: -5px;">新增</button>
  			</div>
			<div class="panel-body hfit" style="overflow-x: auto;">
				<table class="tree table table-bordered table-hover" cellpadding="0" cellspacing="0">
					<tr>
						<!-- <td  style="width: 60px;" >   <input type="checkbox" id="diccheckboxAll_wj">全选    </td> -->
						<td>名称</td>
						<td>code</td>
						<!-- <td>类型</td> -->
						<td>描述</td>
						<td>排序</td>
						<td style="width:85px;">操作</td>
					</tr>
					<tbody id="treebody">
						<c:forEach items="${dList }" var="d">
							<tr class="treegrid-${d.id }">
								<%-- <td>
									 <input type="checkbox"  value="${d.id}"  >
								</td>   --%>
							    <td>${d.value }</td>
							    <td>${d.code }</td>
							    <%-- <td>${d.type }</td> --%>
								<td>${d.description }</td>
							    <td>${d.sort }</td>
							    <td>
								    <c:if test="${d.type!=0}">
										<span onclick="edit(${d.id})" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;
								   	 	<span onclick="hideData(${d.id})" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>
								    </c:if>
							    </td>
							</tr>
						</c:forEach> 
					</tbody>
				</table> 
			</div>
		</div>
	  </div>
	  <div id="openModel" style="display: none;">
	      	 <form id="submitForm" action="${pageContext.request.contextPath }/articleType/saveOrUpdate.shtml" method="post" class="form-horizontal"  style="width: 650px;margin-top: 20px;">
				<!-- 本身ID，用于修改 -->
				<input type="hidden" name="id">
				
				<div class="form-group">
					<label  class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="value">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">code</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="code">
					</div>
				</div>
				<!-- <div class="form-group">
					<label  class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" value="业务参数" readonly="readonly">
					</div>
				</div> -->
				<div class="form-group">
					<label class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="description">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">排序</label>
					<div class="col-sm-10">
						<input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" name="sort">
					</div>
				</div>
				<div class="form-group" style="margin-left: 300px;">
					<button type="button" class="btn btn-primary btn-sm" onclick="saveOrUpdate()" >保存</button>
				</div> 
		  </form>
	</div>
	</div>
	<script type="text/javascript">
	
	//弹窗
	function openDictlayer(){
		var index= layer.open({
		  type:1,
		  area: ['700px', '350px'],
		  content: $('#openModel'),
		  end:function() {
			$('#submitForm')[0].reset();
	      }
		});
		$(".layui-layer-close").click(function(){
	 		layer.closeAll(index);
	 	});
 	}
	
	//编辑一条记录------------start
	function edit(id){
		var url=rootpath+"/articleType/findDic.shtml";
		$.post(url,{id:id},function(data){
			data.value=(data.value==null || data.value==undefined)?(""):(data.value);
			data.code=(data.code==null   || data.code==undefined)?("") :(data.code);
			data.sort=(data.sort==null   || data.sort==undefined)?("") :(data.sort);
			data.description=(data.description==null   || data.description==undefined)?("") :(data.description);
			$("#submitForm").find("input[name='id']").val(data.id);
			$("#submitForm").find("input[name='value']").val(data.value);
			$("#submitForm").find("input[name='code']").val(data.code);
			$("#submitForm").find("input[name='sort']").val(data.sort);
			$("#submitForm").find("input[name='type']").val(data.type);
			$("#submitForm").find("input[name='description']").val(data.description);
			openDictlayer();
		},'json');
	}
	//编辑一条记录-----------end
	
	//修改或新增
	function saveOrUpdate(){
		
		var id = $("#submitForm").find("input[name='id']").val();
		var code = $("#submitForm").find("input[name='code']").val();
		var value = $("#submitForm").find("input[name='value']").val();
		if(value == null || value == ''){
			layer.tips('请输入名称 ',$("#submitForm").find("input[name='value']"), { tips : 1 });
			return;
		}
		if(code == null || code == ''){
			layer.tips('请输入code ',$("#submitForm").find("input[name='code']"), { tips : 1 });
			return;
		}
		if(checkCode(code,id)){
			return;
		}
		$("#submitForm")[0].submit();
	}
	
	/* 删除一条记录 start */
	function hideData(id){
		layer.msg('确定删除选中的数据吗?',{time:0,btn:['确定', '取消'],yes: function(index){
			layer.close(index);
	 		window.location.href=rootpath+'/articleType/delete.shtml?id='+id;
		}});
	}
	/* 查看一条记录 end */
	
	
	//code已存在：true，否则返回false
	function checkCode(code,id){
		var result=false;
		$.ajax({  
			type: "post",  
			url: rootpath+'/articleType/checkCode.shtml',  
			data: {id:id,code:code},  
			async: false,  
			success : function(data){  
				if(data){
					result=data;
					layer.tips('该 code 已经存在，请换一个！ ',$("#submitForm").find("input[name='code']"), { tips : 1 });
				}
	     	}  
	     }); 
		return result;
	}
	
	</script>
	
</body>
</html>