<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar-nav navbar-collapse">
	<h2 style="color: #fff;text-align: center;">${pName }</h2>
	<ul class="nav" id="side-menu">
   		<c:forEach items="${menuList }" var="data">
   			<c:if test="${data.hasChild == true }">
   				<li>
		           <a href="javascript:void(0)" onclick="index_rightMenuChild('${data.parentRes.id }','nav-second-level',this)"><i class="fa fa-bar-chart-o fa-fw"></i> ${data.parentRes.name }<span class="fa arrow"></span></a>
		       </li>
   			</c:if>
   			<c:if test="${data.hasChild == false }">
   				<li style="border-top:  1px solid #555;">
		           <a href="javascript:void(0)" onclick="index_loadMain('${data.parentRes.url}');"><i class="fa fa-dashboard fa-fw"></i> ${data.parentRes.name }</a>
		       </li>
   			</c:if>
   		</c:forEach>
	</ul>
</div>