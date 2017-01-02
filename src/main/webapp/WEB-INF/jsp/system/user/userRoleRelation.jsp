<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>序号</th>
			<th>角色名</th>
			<th>角色key</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${roleList }" var="data" varStatus="status">
		<tr>
			<td>${status.count }</td>
			<td>${data.name }</td>
			<td>${data.roleKey }</td>
			<td>
				<button type="button" class="btn btn-success btm-sm" data-id=${data.id } onclick="deleteUserRole(this)">删除</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
