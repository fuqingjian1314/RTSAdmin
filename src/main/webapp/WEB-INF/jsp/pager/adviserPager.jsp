<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<div class="row">
				<div class="col-lg-4">		
				<!-- Table -->
					<div style="margin-left: 110px;">
						<ul class="ul_wj">
							<c:if test="${empty pager.items}"><span style="color: #ccc;margin-left: 130px;">没有搜索到顾问.....</span></c:if>
							<c:forEach items="${pager.items}" var="ad" varStatus="status" >
								<li onclick="choice('${ad.jobNumber}','${ad.name}')">
									<div style="float:left;">
										<c:if test="${!empty ad.imageUrl}">
											<img style="width:60px;height:60px;" src="${httpPrefix}${ad.imageUrl}?crop/113x113" class="img-circle"> 
										</c:if>
										<c:if test="${empty ad.imageUrl}">
											<img style="width:60px;height: 60px;" src="${pageContext.request.contextPath}/images/portrait.jpg" class="img-circle"> 
										</c:if>
									</div>
									<div>
									 <span>姓名：${ad.name}</span><br>
									 <span>工号：${ad.jobNumber}</span><br>
									</div>
								 </li>
							</c:forEach>
						</ul>	
					</div>
				<!-- Table -->
				</div>
			</div>
            <div class="row">
                <div class="col-lg-12" style="margin-left: 60px;">		
                	<span style="float: left;line-height: 34px;margin-left: -30px;">
						共${pager.rowsCount}条记录,共${pager.pagesCount}页
					</span>
                	<div class="input-group" style="width: 80px;float: left;">
				      <input type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="form-control" value="<c:if test="${pager.pagesCount>0}">${pager.curPage}</c:if>">
				      <span class="input-group-btn">
				        <button class="btn btn-default" type="button" onclick="jumpPage(${pager.requestMethod},this,${pager.pageSize })">跳</button>
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
							<c:when test="${pager.curPage==(i)}">
							 	<li class="active"><a href="javascript:${pager.requestMethod}(${(i-1)*pager.pageSize} );">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:${pager.requestMethod}(${(i-1)*pager.pageSize});">${i}</a></li>
							</c:otherwise>
						</c:choose>
					  </c:forEach>
					  <c:if test="${pager.curPage<pager.pagesCount}">
					  <li><a href="javascript:${pager.requestMethod}(${pager.curPage*pager.pageSize});">下一页</a></li>
					  </c:if>
					  
					  <c:choose>
						<c:when test="${pager.curPage>=1 && pager.pagesCount>pager.curPage}">
						 	<li><a href="javascript:${pager.requestMethod}(${(pager.pagesCount-1)*pager.pageSize});">尾页</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="javascript:void(0);">尾页</a></li>
						</c:otherwise>
					  </c:choose>  
					</ul>
               	</div>
            </div>
            <form id="pagerbe">
	            <input name="begin" type="text" value="${pager.begin}" style="display: none" id="pageBegin"/>
	            <input name="end" type="text" value="${pager.end}" style="display: none" id="pageEnd"/>
            </form>
            <input type="hidden"  id="pageSize_w" value="${pager.pageSize}">
            <input type="hidden"  id="pagesCount_w" value="${pager.pagesCount}">
 <script type="text/javascript">
 	function jumpPage(method,obj,pageSize){
 		var pageIndex = $(obj).parent().parent().find("input[type='text']").val();
 		var pagesCount=$("#pagesCount_w").val()
 		if(pageIndex <1){
			layer.msg("输入页码不能小于1！");
			return;
		}else if(parseInt(pageIndex)>parseInt(pagesCount)){
			layer.msg("输入页码超过最大页面码！");
			return;
		}
 		pageIndex = (pageIndex-1)*$("#pageSize_w").val();
 		var METHOD = eval(method,"()");
 		METHOD(pageIndex);
 	};
 	 
 </script>
 