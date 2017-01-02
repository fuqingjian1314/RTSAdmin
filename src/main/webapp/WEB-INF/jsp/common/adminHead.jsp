<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Object version = getServletContext().getAttribute("version");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/bootstrap/dist/css/bootstrap.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/metisMenu/dist/metisMenu.min.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/dist/css/sb-admin-2.css?v=<%=version %>" rel="stylesheet">
<%--<link href="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/Font-Awesome-master/css/font-awesome.min.css?v=<%=version %>" rel="stylesheet">--%>
<link href="${pageContext.request.contextPath}/css/Font-Awesome-master/css/font-awesome.min.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/layer/skin/layer.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/index.css?v=<%=version %>" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/search.css?v=<%=version %>" rel="stylesheet">



<!-- JS -->
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/jquery/dist/jquery-1.10.2.min.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/bootstrap/dist/js/bootstrap.min.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/bower_components/metisMenu/dist/metisMenu.min.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/sb-admin-2-1.0.8/dist/js/sb-admin-2.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/plugins/layer/layer.js?v=<%=version %>"></script>
<script src="${pageContext.request.contextPath}/js/index.js?v=<%=version %>"></script>

<!-- 加密 -->
<script src="${pageContext.request.contextPath}/js/jquery.base64.min.js?v=<%=version %>"></script>


