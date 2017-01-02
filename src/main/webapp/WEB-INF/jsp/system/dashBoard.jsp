<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">
<head>
<title>交易网后台首页</title>
<jsp:include page="../common/adminContentHead.jsp" />
<style type="text/css">
.huge {
	font-size: 30px;
}
.panel{
	border: 1px solid #EEE;
}
strong {
    font-weight: bold;
    color: #04c;
    font-size: 18px;
    margin: 3px;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">仪表盘</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6">
			<div class="panel">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-tags fa-5x"></i>
						</div>
						<div class="col-xs-9 text-left">
							<div class="huge">今日发布</div>
							<div>商标:<strong>${todayTradeCount }</strong>条&nbsp;&nbsp;&nbsp;&nbsp;公司:<strong>${todayComCount }</strong>条</div>
						</div>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="viewDetail('[data-id=7]','[data-id=8]','[data-id=19]')">
					<div class="panel-footer">
						<span class="pull-left">详情</span> <span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6">
			<div class="panel">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-suitcase fa-5x"></i>
						</div>
						<div class="col-xs-9 text-left">
							<div class="huge">购买申请</div>
							<div>商标:<strong>${todayApplyTradeCount }</strong>条&nbsp;&nbsp;公司:<strong>${todayApplyComCount }</strong>条</div>
						</div>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="viewDetail('[data-id=54]','[data-id=55]','')">
					<div class="panel-footer">
						<span class="pull-left">详情</span> <span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6">
			<div class="panel">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-shopping-cart fa-5x"></i>
						</div>
						<div class="col-xs-9 text-left">
							<div class="huge">转让申请</div>
							<div>商标:<strong>${todayTransferTradeCount }</strong>条&nbsp;&nbsp;公司:<strong>${todayTransferComCount }</strong>条</div>
						</div>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="viewDetail('[data-id=50]','[data-id=52]','')">
					<div class="panel-footer">
						<span class="pull-left">详情</span> <span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6">
			<div class="panel">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-user fa-5x"></i>
						</div>
						<div class="col-xs-9 text-left">
							<div class="huge">在线人数</div>
							<div><strong>${sessionCount }</strong>人</div>
						</div>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="viewDetail('[data-id=1]','[data-id=47]','[data-id=48]')">
					<div class="panel-footer">
						<span class="pull-left">详情</span> <span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/*点击详情跳转*/
		function viewDetail(menuId,leftMenuId,twoMenuId){
			window.parent.index_changeMenu(menuId);
			window.parent.clickMenu(leftMenuId);
			if(twoMenuId != ''){
				window.parent.clickMenu(twoMenuId);
				window.parent.$(twoMenuId).parent("li").parent("ul").addClass("in");
			}
			
		}
		
	</script>
</body>
</html>