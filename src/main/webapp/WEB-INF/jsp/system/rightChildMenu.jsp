<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav ${className }">
	<c:forEach items="${menuList }" var="data">
		<c:if test="${data.hasChild == false }">
		    <li>
		        <a href="javascript:void(0);" onclick="index_loadMain('${data.parentRes.url}');">${data.parentRes.name }</a>
		    </li>
		</c:if>
		<c:if test="${data.hasChild == true }">
			<li>
				<a href="javascript:void(0);" onclick="index_rightMenuChild('${data.parentRes.id }','nav-third-level',this)">${data.parentRes.name } <span class="fa arrow"></span></a>
			</li>
		</c:if>
	</c:forEach>
</ul>