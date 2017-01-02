<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<pg:pager maxPageItems="${param.pagesize}" maxIndexPages="5" items="${param.items }" export="curPage=pageNumber" url="${param.url }">

	<c:forEach items="${param.params }" var="p">
		<pg:param name="${p}"/>
	</c:forEach>
	
	<pg:last>
		共${param.items }条记录,共${pageNumber }页,
	</pg:last>
	当前第${curPage }页
	
	<pg:first>
		<li><a
		<c:choose>
			<c:when test="${curPage>1}">
			 	class="prev" href="${pageUrl}"
			</c:when>
			<c:otherwise>
				class="next"  style="background-color:#BDBDBD;border-color:#BDBDBD;color:#666666;cursor:default"
			</c:otherwise>
		</c:choose>
		><span>首页</span></a></li>
	</pg:first>
	
	<pg:prev>
		<li><a href="${pageUrl}" class="prev"><span>上一页</span></a></li>
	</pg:prev>
	
	<pg:pages>
		<c:if test="${curPage eq pageNumber }">
			<li><a class="active" href="javascript:void(0);">${pageNumber }</a></li>
		</c:if>
		<c:if test="${curPage ne pageNumber }">
			  <li><a href="${pageUrl }">${pageNumber }</a></li>
		</c:if>
	</pg:pages>
	
	<pg:next>
		<li><a href="${pageUrl}" class="prev"><span>下一页</span></a></li>
	</pg:next>
	
	<pg:last>
		<li><a  
		<c:choose>
			<c:when test="${pageNumber>1&&pageNumber>curPage}">
			 	class="prev" href="${pageUrl}"
			</c:when>
			<c:otherwise>
				class="next"  style="background-color:#BDBDBD;border-color:#BDBDBD;color:#666666;cursor:default"
			</c:otherwise>
		</c:choose>
		><span>尾页</span></a></li>
	</pg:last>
</pg:pager>