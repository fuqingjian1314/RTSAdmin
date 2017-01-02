<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>交易网后台首页</title>
<jsp:include page="../../common/adminContentHead.jsp" />
<style type="text/css">
html {
    width: 100%;
    overflow: auto;
}
</style>
</head>
<body>
	<div class="list-group" id="list_role_fqj">
	<input type="hidden" value="${uId }" id="uId">
	  <c:forEach items="${allroles }" var="data" >
		  <a href="javascript:void(0);" class="list-group-item" data-id="${data.id }" data-key="${data.roleKey}">${data.name }
		  <c:forEach items="${myroles }" var="myrole">
		  		<c:if test="${myrole.id==data.id}">
					  <span class="glyphicon glyphicon-ok" style="float:right"></span>
		  		</c:if>
		  </c:forEach>
		  </a>
	  </c:forEach>
	</div>
	<script type="text/javascript">
		$("#list_role_fqj>a").click(function(){
			var ishasSpan=$(this).children("span").is("span");
			if(ishasSpan){
				$(this).children("span").remove();
			}else{
				var span=$("<span></span>").addClass("glyphicon glyphicon-ok").css({"float":"right"});
				$(this).append(span);
			}
		})
	</script>
</body>
</html>