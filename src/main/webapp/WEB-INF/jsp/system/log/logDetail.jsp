<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default" style="padding: 10px 0px;border: none;">
	<form id="updataForm" class="form-horizontal">
		<table class="table" style="table-layout:fixed;" >
		   <tbody>
		      <tr>
		         <td width="80px;" style="text-align: right;">描述</td>
		         <td><p>${log.description}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">方法</td>
		         <td><p>${log.method}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">异常</td>
		         <td><p>${log.exceptionCode}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">异常详情</td>
		         <td><p>${log.exceptionDetail}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">参数</td>
		         <td><p>${log.params}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">访问IP</td>
		         <td><p>${log.requestIp}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">访问者</td>
		         <td><p>${log.createby}</p></td>
		      </tr>
		      <tr>
		         <td style="text-align: right;">访问时间</td>
		         <td><p>${log.formatCreateDate}</p></td>
		      </tr>
		   </tbody>
		</table>
	</form>
</div>