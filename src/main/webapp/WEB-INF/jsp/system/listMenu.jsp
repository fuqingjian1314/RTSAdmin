<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminContentHead.jsp" />
<link href="${pageContext.request.contextPath}/css/zTreeStyle/metroStyle/metroStyle.css?v=<%=getServletContext().getAttribute("version") %>" rel="stylesheet">
<title>交易网后台首页</title>
<style type="text/css">
	.selectItemhidden{background:#FFFFFF;display:none;overflow :hidden;position:absolute;top:0px;border:1px solid #CCC;width:545px;height:200px;z-index:1000;
		-moz-border-radius: 4px; -webkit-border-radius: 4px;}
	.selectItem_ul{
		height:164px;
		overflow :auto;
	}
	#myTab {
		font:12px verdana, arial, sans-serif; /* 设置文字大小和字体样式 */
		padding:0; /* 将默认的内边距去掉 */
		margin:0; /* 将默认的外边距去掉 */
		width:100%;
	}
	#myTab li {
		list-style:none; /* 将默认的列表符号去掉 */
		padding:1px 0px; /* 将默认的内边距去掉 */
		margin:0; /* 将默认的外边距去掉 */
		float:left;
		text-align: left;
	}
	#myTab li a {
		display:block; /* 将链接设为块级元素 */
		width:166.5px; /* 设置宽度 */
		height:30px; /* 设置高度 */
		line-height:30px; /* 设置行高，将行高和高度设置同一个值，可以让单行文本垂直居中 */
		text-align:left; /* 居中对齐文字 */
		background:#FFFFFF; /* 设置背景色 */
		color:#333; /* 设置文字颜色 */
		text-decoration:none; /* 去掉下划线 */
		border-right:0px solid #000; /* 在左侧加上分隔线 */
		padding-left: 12px;
	}
	#myTab li a:hover {
		background:#eee; /* 变换背景色 */
		color:#333; /* 变换文字颜色 */
		font-size: 16px;
	}
</style>
</head>    
<body>   
<div class="row">
	<div class="col-md-3">
		<div class="panel panel-default">
				<div class="panel-heading">菜单树<button type="button" class="btn btn-success btm-sm" onclick="buttonOnClickAdd()" style="float: right;margin-top: -7px">新增根菜单</button></div>
				<div class="panel-body hfit" style="overflow-y: auto;">
					<div>
						<ul id="orgtree" class="ztree"></ul>
					</div>
				</div>
		</div>
		
	</div>
	<div class="col-md-9">
		<div class="panel panel-default">
			<div class="panel-heading">菜单详情</div>
			<div class="panel-body hfit" style="overflow-y: auto;">
				<form class="form-horizontal" id="Treeform" style="display: none;">
				<input type="hidden" id="Id" value="">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">菜单名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="menu_name">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="menu_desc">
					</div>
				</div>
				<div class="form-group">
					<label for="pinyin" class="col-sm-2 control-label">URL</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="menu_url">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">图标</label>
					<div class = "col-sm-6">
						<div class = "input-group">
							<input type="text" class="form-control" id="menu_icon" readonly="readonly">
							<span class = "input-group-btn">
								<button class = "btn btn-default" style="line-height: 1.478571;" id="menu_btn" type = "button">
									<i class="fa fa-flag"></i>
								</button>
               				</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">排序</label>
					<div class="col-sm-6">
						<input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" id="menu_order">
					</div>
				</div>
				<div class="form-group" style="margin-left: 226px;">
					<button type="button" class="btn btn-primary" id="add_sub">保存</button>
				</div>
			</form>
			<!--  -->
			<form class="form-horizontal" id="upTreeform" style="display: none;">
				<input type="hidden" id="Id" value="">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">菜单名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="upmenu_name">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="upmenu_desc">
					</div>
				</div>
				<div class="form-group">
					<label for="pinyin" class="col-sm-2 control-label">URL</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="upmenu_url">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">图标</label>
					<div class = "col-sm-6">
						<div class = "input-group">
							<input type = "text" class = "form-control" id="upmenu_icon" readonly="readonly">
							<span class = "input-group-btn">
								<button class = "btn btn-default" style="line-height: 1.478571;" id="upmenu_btn" type = "button">
									<i class="fa fa-th-large"></i>
								</button>
               				</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">排序</label>
					<div class="col-sm-6">
						<input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" id="upmenu_order">
					</div>
				</div>

				<div class="form-group" style="margin-left: 226px;">
					<button type="button" class="btn btn-primary" id="mody_sub">修改</button>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="childTreeform"
				style="display: none;">
				<input type="hidden" id="Id" value="">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">菜单名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="chmenu_name">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="chmenu_desc">
					</div>
				</div>
				<div class="form-group">
					<label for="pinyin" class="col-sm-2 control-label">URL</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="chmenu_url">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">图标</label>
					<div class = "col-sm-6">
						<div class = "input-group">
							<input type="text" class="form-control" id="chmenu_icon" readonly="readonly">
							<span class = "input-group-btn">
								<button class = "btn btn-default" style="line-height: 1.478571;" id="chmenu_btn" type = "button">
									<i class="fa fa-flag"></i>
								</button>
               				</span>
						</div>
					</div>

				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">排序</label>
					<div class="col-sm-6">
						<input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" id="chmenu_order">
					</div>
				</div>
				<div class="form-group" style="margin-left: 226px;">
					<button type="button" class="btn btn-primary" id="child_sub">保存</button>
				</div>
			</form>
			
			</div>
		</div>
		
	</div>		
</div>

<div id="selectItem" class="selectItemhidden">
	<div class = "input-group">
		<input type="text" class="form-control" id="search_icon" placeholder="输入查找的图标......">
		<span class = "input-group-btn">
			<button class = "btn btn-default" style="line-height: 1.508571;" onclick="searchFontIcon()" type = "button">
				<i class="fa fa-search"></i>
			</button>
			<button class = "btn btn-default" style="line-height: 1.508571;" onclick="reset()" type = "button">
				<i class="fa fa-refresh"></i>
			</button>
		</span>
	</div>
	<div class = "selectItem_ul">
		<ul id="myTab"></ul>
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.exedit-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ztree.excheck-3.5.js?v=<%=getServletContext().getAttribute("version") %>"></script>
<script src="${pageContext.request.contextPath}/js/system/listMenu.js?v=<%=getServletContext().getAttribute("version") %>"></script> 
</body>
</html>