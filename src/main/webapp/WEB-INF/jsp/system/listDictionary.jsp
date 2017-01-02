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
	  <div class="col-md-3">
	  	<div class="panel panel-default">
	  			<div class="panel-heading">参数类型<button type="button" class="btn btn-success btn-sm" id="addParendic_button_wj" style="float: right;margin-top: -5px;">新增</button></div>
				<div class="panel-body hfit" style="overflow-x: auto;">
					<!-- <form  id="dicsearch_form_wj"   method="post" > -->
						<div class="input-group custom-search-form" style="margin-bottom: 3px;">
							    <input type="text" class="form-control" name="dicName" value="${dicName}" placeholder="Search...">
							    <span class="input-group-btn">
							    	<button class="btn btn-default" id="dicsearch_button_wj" type="button">  <i class="fa fa-search"></i> </button>
							    	<button class="btn btn-default" id="dicRefresh_button_wj" type="button">  <i class="fa fa-repeat"></i> </button>
								</span>
						</div>
					<!-- </form> -->
					<div class="list-group">
				  		<c:forEach items="${leftList}" var="dic">
							   <a data-id="${dic.id}" onclick="getChildDicWJ(this,${dic.id})" data-type="${dic.type}" class="list-group-item">${dic.value}
						   			<span style="float:right;">
						   				<span onclick="searchDic_wj(${dic.id})" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;
									    <c:if test="${dic.type!=0}">
									    	<span onclick="editDic_wj(${dic.id},0)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;
									   	 	<span onclick="trashDic_wj(${dic.id},0)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>
										</c:if>
									</span>
							   </a>
						</c:forEach>
			 		</div>
				</div>
		</div>	
	  </div>
	  <div class="col-md-9">
	  	<div class="panel panel-default">
	  			<div class="panel-heading">
	  			参数列表<button type="button" class="btn btn-success btn-sm" id="addsubdic_button_wj" style="float: right;margin-top: -5px;">新增</button>
	  			</div>
				<div class="panel-body hfit" style="overflow-x: auto;">
					<table class="tree table table-bordered table-hover" cellpadding="0" cellspacing="0">
						<tr>
							<td  style="width: 60px;" >   <!-- <input type="checkbox" id="diccheckboxAll_wj">全选 -->    </td>
							<td>名称</td>
							<td>code</td>
							<td>sort</td>
							<td style="width:85px;">操作</td>
						</tr>
						<tbody id="treebody">
							<c:forEach items="${secondList }" var="d">
								<tr class="treegrid-${d.id } <c:if test="${d.pid ne 0 }"> treegrid-parent-${d.pid} </c:if>" onclick="changetr_wj(this)">
									<td>
										 <input type="checkbox"  value="${d.id}"  >
									</td>  
								    <td>${d.value }</td>
								    <td>${d.code }</td>
								    <td>${d.sort }</td>
								    <td>
									    <span onclick="searchDic_wj(${d.id})" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;
									    <c:if test="${d.type!=0}">
									    	<span onclick="editDic_wj(${d.id},1)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;
									   	 	<span onclick="trashDic_wj(${d.id},1)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>
									    </c:if>
								    </td>
								</tr>
							</c:forEach> 
						</tbody>
					</table> 
				</div>
		</div>
	  </div>
	  <div id="layer_Dictdiv_wj" style="display: none;">
	      	 <form class="form-horizontal"  style="width: 650px;margin-top: 20px;">
	      	 	<input type="hidden" id="dic_firstpid_wj" value="${pid}" ><!-- 根id -->
	       		<input type="hidden" id="dic_pid_wj" value="${pid}" ><!-- 子级 -->
				<input type="hidden" id="dic_id_wj" value=""><!-- 自己 -->
				<div class="form-group">
					<label  class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dic_name_wj">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">code</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dic_code_wj">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<select class="form-control" id="dic_selectype_wj">
						  <option value="0"  >系统参数</option>
						  <option value="1" selected >业务参数</option> 
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dic_desc_wj">
					</div>
				</div>
				<div class="form-group">
					<label   class="col-sm-2 control-label">排序</label>
					<div class="col-sm-10">
						<input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" id="dic_sort_wj">
					</div>
				</div>
				<div class="form-group" style="margin-left: 300px;">
					<button type="button" class="btn btn-primary btn-sm" id="saveOrupdate_button_wj" >保存</button>
				</div> 
		  </form>
	</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/system/listDictionary.js?v=<%=getServletContext().getAttribute("version") %>"></script> 
</body>
</html>