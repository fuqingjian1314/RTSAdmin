<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/adminHead.jsp" />
<title>交易网后台首页</title>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<%@include file="../common/menu.jsp"%>

		<!-- page-wrapper -->
		<div id="page-wrapper">
			<!-- begin搜索 -->
			<iframe id="page-content" style="width:100%;border:0px;margin: 0px;padding: 0px;"></iframe>

		</div>
	</div>
	<script type="text/javascript">
		/*菜单加载*/
		function clickMenu(obj){
			//$(obj).parent("li").toggleClass("active").children("ul").collapse("toggle");
			//$(obj).toggleClass("active");*/
			$(obj).parent("li").siblings().removeClass("active").children("ul.in").collapse("hide"); 
			$(".active").css({"color":""}).removeClass("active");
			$(obj).toggleClass("active").css({"color":"#337ab7"});
			var url=$(obj).attr("aurl");
			if(url.indexOf("undefined")>-1||(url=="${pageContext.request.contextPath}")){
				return;
			}
			$("#page-content").attr("src", url);
/* 			$.post(url,{},function(data){
				$("#page-wrapper").html(data);
			},'html'); */
		}
		/*获取表单json数据*/
		function getFromData(formId){
			var data = {};
			$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
			return data;
		}
		
		function aa(){
		var url = "${pageContext.request.contextPath}/user/retrunUserList.shtml";
		$.post(url,null,function(data){
			$("#page-wrapper").html(data);
		},'html');
			}
		
		//aa();
		var zNodes=${resjsons};
		//加载右侧菜单
		$(function(){
			$("#topMenu").find("li:first-child").find("a").click();
			
			//默认选中首页仪表盘
			index_changeMenu("[data-id=1]");
			clickMenu("[data-id=2]");
		});
			
		function index_changeMenu(obj){
			$(obj).parent().parent().find("li").each(function (){
				$(this).find("a").removeClass("index_active_li_hl");
			});
			$(obj).addClass("index_active_li_hl");
			var pid=$(obj).attr("data-id");
			var pName = $(obj).text();
			$("#testnav").text(pName);
			var path='${pageContext.request.contextPath}';
			var html=new treeMenu(zNodes,path).init(pid);
			$("#testnav").next().remove();
			$("#rightMenu").find(".sidebar-nav").append(html);
			$('#side-menu').metisMenu();
		}
	</script>
</body>
</html>


