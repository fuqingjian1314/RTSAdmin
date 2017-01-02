<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           <div class="row">
               <div class="col-lg-12">		
				  <!-- Table -->
				  <table class="table table-bordered table-hover">
				  <%--  <caption>边框表格布局</caption> --%>
				   <thead>
				      <tr>
				      	<c:forEach items="${tableheads}" var="head">
				      		<th>${head}</th>
						</c:forEach>
				      </tr>
				   </thead>
				   <tbody>
				   	  <c:forEach items="${pager.items}" var="obj">
				      <tr>
					     <c:forEach items="${tablecolumns}" var="column">
				      	 <td>${obj[column]}</td>
						 </c:forEach>
						 <c:if test="${pager.hasOpt==true}">
						 	<td>
						 		<c:forEach items="${pager.buttons}" var="btn" varStatus="status">
				      				<button class="btn btn-primary btn-sm" data-id="${obj.id }" onclick="${btn.jsMethod }(this)">${btn.name}</button>
								</c:forEach>
						 	</td>
						 </c:if>
				      </tr>
				      </c:forEach>
				   </tbody>
				</table>
				
               </div>
           </div>
           <c:if test="${pager.rowsCount>0 && pager.pagesCount >=1}">
	            <div class="row">
	                <div class="col-lg-12">		
	                	<span style="float: left;line-height: 34px;color: #337ab7">
								共${pager.rowsCount}条记录,共${pager.pagesCount}页,
							当前第${pager.curPage}页
						</span>
	                	<div class="input-group" style="width: 80px;float: left;">
					      <input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control">
					      <span class="input-group-btn">
					        <button style="color: #337ab7" class="btn btn-default" type="button" onclick="jumpPage(${pager.requestMethod},this,${pager.pageSize })">跳</button>
					      </span>
					    </div>
	                	
	                	<ul class="pagination" style="margin-top: 0px;float:right;">
		                	<c:choose>
								<c:when test="${pager.curPage>1}">
								 	<li><a href="javascript:${pager.requestMethod}(0);">首页</a></li>
								</c:when>
								<c:otherwise>
									<li class="disabled"><a href="javascript:void(0);">首页</a></li>
								</c:otherwise>
							</c:choose>
							
						  <c:if test="${pager.curPage>1}">
						  	<li><a href="javascript:${pager.requestMethod}(${(pager.curPage-2)*pager.pageSize});">上一页</a></li>
						  </c:if>
						  <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
						  	<c:choose>
								<c:when test="${pager.curPage==(i+1)}">
								 	<li class="active"><a href="javascript:${pager.requestMethod}(${i*pager.pageSize} );">${i+1}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:${pager.requestMethod}(${i*pager.pageSize});">${i+1}</a></li>
								</c:otherwise>
							</c:choose>
						  </c:forEach>
						  <c:if test="${pager.curPage<pager.pagesCount}">
						  <li><a href="javascript:${pager.requestMethod}(${pager.curPage*pager.pageSize});">下一页</a></li>
						  </c:if>
						  
						  <c:choose>
							<c:when test="${pager.curPage>1 && pager.pagesCount>pager.curPage}">
							 	<li><a href="javascript:${pager.requestMethod}(${(pager.pagesCount-1)*pager.pageSize});">尾页</a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a href="javascript:void(0);">尾页</a></li>
							</c:otherwise>
						  </c:choose>  
						</ul>
	               	</div>
	            </div>
           </c:if>
            <form id="pagerbe">
	            <input name="begin" type="text" value="${pager.begin}" style="display: none" id="pageBegin"/>
	            <input name="end" type="text" value="${pager.end}" style="display: none" id="pageEnd"/>
            </form>
 <script type="text/javascript">
 	function jumpPage(method,obj,pageSize){
 		var pageIndex = $(obj).parent().parent().find("input[type='text']").val();
 		pageIndex = (pageIndex-1)*10;
 		var METHOD = eval(method,"()");
 		METHOD(pageIndex);
 	};
 	
 
 </script>