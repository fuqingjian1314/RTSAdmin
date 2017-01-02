	$(function(){
		loadPaging(1);
		var divheight=$(window).height();//初始化修改div的高度
		divheight=divheight-119;
		$(".hfit").css({height:divheight});
 	});
	 function searchIntro(){
		 loadPaging(1); 
	 }
	 function resetIntro(){
		$("#searchForm")[0].reset();
		$("#gcOwnedIndu").selectpicker('refresh');
		$("#gdState").selectpicker('refresh');
		loadPaging(1);
	 }
	function getFromData(formId){
		var data = {};
		$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
		return data;
	}
 	/* 分页start */
 	/*点击分页时加载数据*/
	function loadcompany(pageNum){
		var url=rootpath+"/company/list.shtml";
		var offSet=(pageNum-1)*10;
		var date=getFromData("searchForm");
		date.offSet=offSet;
		date.pageSize=10;
		$.post(url,date,function(data){
			var rows=data.items;
			$("#company_table_wj").table(rows);//给table 填充数据
		});
		$("#company_table_wj thead th:first input")[0].checked=false;
	}
 	/* 初次初始化时调用 */
	function loadPaging(pageNum){
		var url=rootpath+"/company/list.shtml";
		var offSet=(pageNum-1)*10;
		var date=getFromData("searchForm");
		date.offSet=offSet;
		date.pageSize=10;
		$.post(url,date,function(data){
			var rows=data.items;
			var count=data.rowsCount;
			$("#company_table_wj").table(rows);//给table 填充数据
			$("#tablePaging").tablePaging(1,10,count,"loadcompany"); //初始化分页插件
		});
	}
	/*按钮渲染 start*/
	function renderIndex(e){
		return e.rowindex+1;
	}
	function renderName(e){
		return e.row.goods.gdName;
	}
	function renderTime(e){
		return timeStamp2String(e.row.goods.gdUpdateTime)
	}
	function renderCount(e){
		var gcTelCount=(e.row.gcTelCount==undefined)?0:(e.row.gcTelCount);
		var gcAppliCount=(e.row.gcAppliCount==undefined)?0:(e.row.gcAppliCount);
		var gcOnlineCount=(e.row.gcOnlineCount==undefined)?0:e.row.gcOnlineCount;
		return ""+(gcTelCount+gcAppliCount+gcOnlineCount);
	}
	function renderLable(e){
		return e.row.goods.gdLabelValue;
	}
	function renderBcount(e){
		if(e.row.gcBrowCount==undefined){
			return "0";
		}else{
			return e.row.gcBrowCount;
		}
	}
	function renderIndu(e){
		if(e.row.gcOwnedIndu==undefined){
			return"";
		}else{
			var arr=e.row.gcOwnedIndu.split(",");
			var value="";
			$.each(arr,function(i,obj){
				value += $("#gcOwnedIndu option[value='"+obj+"']").text() +"&nbsp;&nbsp;";
			});
			return value;
		}
	}
	function renderState(e){
		var state= e.row.goods.gdState;
		if(state==0){
			return '<span style="color: #F0AD4E;">待发布</span>';
		}else if(state==1){
			return '已发布';
		}else if(state==4){
			return '已上架 ';
		}else if(state==3){
			return '<span style="color: #F0AD4E;">已下架</span>';
		}else{
			return '未知 ';
		}
	}
	function renderOP(e){
		var id= e.row.goods.gdId;
		var state= e.row.goods.gdState;
		var ophtml='<a href="'+rootpath+'/company/edit.shtml?id='+id+'" class="btn btn-success btn-sm"  >查看详情</a>&nbsp;&nbsp;';
		/*if(state==0){
			ophtml+='<button type="button" class="btn btn-warning btn-sm" onclick="bcst(\''+id+'\',1)" >发布</button>&nbsp;&nbsp;';
		}else if(state==1){
			ophtml+='<button type="button" class="btn btn-success btn-sm" onclick="bcst(\''+id+'\',4)" >上架</button>&nbsp;&nbsp;';
		}else*/ if(state==4){
			ophtml+='<button type="button" class="btn btn-success btn-sm" onclick="bcst(\''+id+'\',3)" >下架</button>&nbsp;&nbsp;';
		}else if(state==3){
			ophtml+='<button type="button" class="btn btn-warning btn-sm" onclick="bcst(\''+id+'\',4)" >上架</button>&nbsp;&nbsp;';
		}
		return ophtml;
	}
	/*按钮渲染 end*/
	
	function timeStamp2String(time){  
	    var datetime = new Date();  
	    datetime.setTime(time);  
	    var year = datetime.getFullYear();  
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
 	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date+' '+hour+':'+minute+':'+second;  
	}
	
	
	/* 分页end */
	
	
 	/* 初始化 end */
	var dic_frompid_wj=0;
	function openDictlayer(){
		dic_frompid_wj= layer.open({
		  title:"选择标签",
		  type:1,
		  area: ['400px', '230x'],
		  content: $('#layer_Companylable_wj'),
		  end:function() {
       }
	   });
	}
	function closeDictlayer(){
		layer.close(dic_frompid_wj);
	}
	
	$("input.checkCansle").click(function() {
		//$("#company_table_wj").checkedTableALLRows();
		$("#company_table_wj").notCheckedTableALLRows();
 	});
	
	function batchop(type,value,ids,url){
		$.ajax({
			 type:"post",
			 url:url,
			 dataType:"json",
			 data:{type:type,value:value,id:ids},
			 success:function(data){
				 if (data.status) {
					layer.msg("成功操作"+data.info+"条数据！", {icon: 6});
					closeDictlayer();
					setTimeout(function(){
						loadPaging(1); 
					},800);
				 }else{
					 layer.msg(data.info, {icon: 5});
				 }
			 }
		});
	}
	/*按钮方法*/
	function getSelect(){
		var rows=$("#company_table_wj").getTableSelects();
		var ids=new Array();
		 $.each(rows,function(i,obj){
			ids[i]=obj.goods.gdId;
		});
		return ids;
	}
	function getESelect(state){
		var rows=$("#company_table_wj").getTableSelects();
		var ids=new Array();
		 $.each(rows,function(i,obj){
			if(obj.goods.gdState!=state){
				ids[i]=obj.goods.gdId;
			}
		});
		return ids;
	}
	function getELable(state){
		var rows=$("#company_table_wj").getTableSelects();
		var ids=new Array();
		 $.each(rows,function(i,obj){
			if(obj.goods.gdLabel!=state){
				ids[i]=obj.goods.gdId;
			}
		});
		return ids;
	}
	/* 批量修改标签 */
	function  batchModifyLabel(){
		var url=rootpath+"/goods/batchop.shtml";
		var value=$("select.form-control").val();
		var ids=getSelect();
		if(value.length<=0){layer.msg("请选择标签！", {icon: 5 });return;}
		if(ids.length<=0){layer.msg("请选择一行数据！", {icon: 5 });return;}
		var id = getELable(value);
		if(id.length<=0){layer.msg("选择的行，已是该状态！", {icon: 5 });return;}
		batchop('lable',value,id,url);
	}
	
	/* 批量上架 */
	function mashelves(){
		var url=rootpath+"/goods/batchop.shtml";
		var ids=getSelect();
		//var ids=$("input.checItem:checked").map(function(){return $(this).val();}).toArray();
		if(ids.length<=0){layer.msg("请选择一行数据！", {icon: 5 });return;}
		var id = getESelect(4);
		if(id.length<=0){layer.msg("选择的行，已是该状态！", {icon: 5 });return;}
		batchop('state',4,id,url);
	}
	/* 批量下架 */
	function batchLowerFrame(){
		var url=rootpath+"/goods/batchop.shtml";
		var ids=getSelect();
		if(ids.length<=0){layer.msg("请选择一行数据！", {icon: 5 });return;}
		var id = getESelect(3);
		if(id.length<=0){layer.msg("选择的行，已是该状态！", {icon: 5 });return;}
		batchop('state',3,id,url);
	}
	/* 批量删除 */
	function batchDelete(){
		layer.msg('确定删除选中的数据吗?',{time:0,btn:['确定', '取消'],yes: function(index){ layer.close(index);
			var url=rootpath+"/goods/batchop.shtml";
			var ids=getSelect();
			if(ids.length<=0){layer.msg("请选择一行数据！", {icon: 5 });return;}
			var id = getESelect(-1);
			if(id.length<=0){layer.msg("选择的行，已是该状态！", {icon: 5 });return;}
			batchop('delete',0,id,url);
		}});
	}
	/* 修改状态 */
	function bcst(id,value){
		var ids=new Array();
		ids[0]=id;
		var url=rootpath+"/goods/batchop.shtml";
		batchop('state',value,ids,url);
	}
	