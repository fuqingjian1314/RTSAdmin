<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/metroStyle/metroStyle.css?v=<%=getServletContext().getAttribute("version") %>" rel="stylesheet">
<style type="text/css">
.layui-layer-btn{text-align:center;padding:0 10px 12px;pointer-events:auto}
</style>
<title>交易网后台首页</title>
</head>    
<body>   
<div class="row">
	<div class="col-md-3">
		<div class="panel panel-default">
				<div class="panel-heading">菜单树</div>
				<div class="panel-body hfit" style="height: 600px;overflow-y: auto;">
					<div>
						<ul id="orgtree1" class="ztree"></ul>
					</div>
				</div>
		</div>
		
	</div>
	<div class="col-md-9">
		<div class="panel panel-default">
			<div class="panel-heading">权限列表</div>
			<div class="panel-body hfit" style="height: 600px;overflow-y: auto;">
			<ul class="nav nav-tabs nav-justified">
			   <li class="active"><a href="javascript:void(0)" onclick="changeAuthType(this)">URL</a></li>
			   <li><a href="javascript:void(0)" onclick="changeAuthType(this)" style="color: #337ab7;">按钮</a></li>
			</ul>
			<div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading" style="padding: 5px 15px;">
				<div class="btn-group" id="btns_url_fqj">
				  <button type="button" class="btn btn-default" onclick="addAuthoritybyMemu();">添加</button>
				  <button type="button" class="btn btn-default" onclick="modifyAuthoritybyMemu();">修改</button>
				  <button type="button" class="btn btn-default" onclick="delectAuthoritybyMemu();">删除</button>
				</div>
				<div class="btn-group" id="btns_btn_fqj" style="display: none">
				  <button type="button" class="btn btn-default" onclick="addAuthoritybtnbyMemu();">添加</button>
				  <button type="button" class="btn btn-default" onclick="modifyAuthoritybtnbyMemu();">修改</button>
				  <button type="button" class="btn btn-default" onclick="deleteAuthoritybtnbyMemu();">删除</button>
				</div>
			  </div>
			  <div class="panel-body">
			  <!-- Table -->
			  <table class="table table-bordered table-hover"  id="table_url_fqj">
				   <thead>
				      <tr>
				      	 <th style="width: 30px;"><input type="checkbox"></th>
				         <th field="url">url</th>
				         <th field="resKey">key</th>
				         <th field="name">描述</th>
				      </tr>
				   </thead>
				</table>
			  <!-- Table -->
			  <table class="table table-bordered table-hover" id="table_button_fqj" style="display: none">
				   <thead>
				      <tr>
				      	 <th style="width: 30px;"><input type="checkbox"></th>
				         <th field="name" render="btnNameRender">按钮</th>
				         <th field="resKey">页面元素id</th>
				         <th field="description">备注</th>
				      </tr>
				   </thead>
				</table>
				
				
			</div>
				
				
			</div>
		</div>
		</div>
		<div id="addurl_div_fqj" style="display: none;margin: 9px;">
			<div>
			 <div class="well" style="padding: 5px;margin-bottom: 5px;">
			 	<div class="row">
			 	  <div class="col-md-8">
				  <div class="input-group">
			         <span class="input-group-addon">url</span>
			         <input type="text" class="form-control" id="urlsearch_input_fqj">
			      </div>
			      </div>
			      <div class="col-md-4">
			      <button type="button" class="btn btn-success btn-sm" onclick="seachurl();">搜索</button>
			      <button type="button" class="btn btn-success btn-sm" onclick="reseturl();">刷新</button>
			      </div>
			   </div>  
			 </div>
	      	 <table class="table table-bordered table-hover"  id="table_addurl_fqj">
				   <thead>
				      <tr>
				      	 <th style="width: 30px;"><input type="checkbox"></th>
				         <th field="url">url</th>
				      </tr>
				   </thead>
			</table>
			</div>
		</div>
		<div id="modifyurl_div_fqj" style="margin-top: 9px;width: 485px;display: none;">
			<form class="form-horizontal" id="form_save_fqj">
				<input type="hidden" name="id">
				<div class="form-group">
					<label  class="col-sm-3 control-label">url</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="url">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-3 control-label">key</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="resKey">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">描述</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="name">
					</div>
				</div>
		  </form>
		</div>
		<div id="savemodifybtn_div_fqj" style="margin-top: 9px;width: 485px;display: none;">
			<form class="form-horizontal" id="formbtn_save_fqj">
				<input type="hidden" name="id">
				<div class="form-group">
					<label  class="col-sm-3 control-label">按钮名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="name">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">html元素id</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="resKey">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">备注</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="description">
					</div>
				</div>
		  </form>
		</div>
	</div>		
</div>	
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/jquery/dist/jquery.min.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.exedit-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.excheck-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/bootstrapTable.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/system/chooseAuthorityMenu.js?v=<%=getServletContext().getAttribute("version") %>"></script>
</body>
</html>

