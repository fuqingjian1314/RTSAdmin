$(function(){
	loadPaging(1);
	var divheight=$(window).height();//初始化修改div的高度
	divheight=divheight-52;
	$("div.panel-body.hfit").css({height:divheight});
});

/* 初次初始化时调用 */
function loadPaging(pageNum){
var url=rootpath+"/companyCategory/list.shtml";
var offSet=(pageNum-1)*10;
$.post(url,function(data){
	var rows=data.items;
	var count=data.rowsCount;
	$("#company_table_wj").table(rows);//给table 填充数据
	$("#tablePaging").tablePaging(1,10,count,"loadPaging"); //初始化分页插件
	/* 点击编辑按钮 */
	$(".btn.btn-success.btn-sm.edit").click(function(){
		openlayer();
		var $that=$(this).parent().parent();
		var data=$that.children().eq(0).val();
		var obj=jQuery.parseJSON(data);
		$(".form-control.id").val(obj.induId);
		$(".form-control.pid").val(obj.induPId);
		$(".form-control.name").val(obj.induName);
	});
	/* 点击删除按钮 */
	$(".btn.btn-warning.btn-sm.delete").click(function(){
		var $that=$(this).parent().parent();
		layer.msg('确定删除选中的数据吗?',{time:0,btn:['确定', '取消'],yes: function(index){ layer.close(index);
			var data=$that.children().eq(0).val();
			var id=jQuery.parseJSON(data).induId;
			var url=rootpath+"/companyCategory/delete.shtml";
			var datas={"id":id};
			$.post(url,datas,function(data){
				if(data.status){
					$that.remove();
					layer.msg("成功删除"+data.info+"条数据！", {icon: 6});
				}else{
					layer.msg(data.info, {icon: 5});
				}
			});
		}});
		
	});
});
}

/*按钮渲染 start*/
function renderIndex(e){
	return e.rowindex+1;
}
function renderOP(e){
	var id= e.row.induId;
	var induNum= e.row.induNum;
	var ophtml='<a class="btn btn-success btn-sm edit">编辑</a>&nbsp;&nbsp;';
	/*if(induNum==0){
		ophtml+='<button class="btn btn-warning btn-sm delete">删除</button>';
	}*/
	return ophtml;
}
/*按钮渲染 end*/

/* layer弹出层  start*/
var openingLayerIndex=0;
function openlayer(){
	openingLayerIndex= layer.open({
	  title:"分类名称",
	  type:1,
	  area: ['400px', '230x'],
	  content: $('#layer_Companylable_wj'),
	  end:function() {
	}
	});
}
function closelayer(){
	layer.close(openingLayerIndex);
}
/* layer弹出层 end */

/* 点击添加按钮 */
$(".btn.btn-success.btn-sm.add").click(function(){
	var data=$("#company_table_wj tbody tr:first").children().eq(0).val();
	var pid=jQuery.parseJSON(data).induPId;
	$(".form-control.pid").val(pid);
	openlayer();
});
/* 点击取消按钮 */
$(".btn.btn-primary.btn-sm.cansle").click(function(){
	closelayer();
});
 
/*名称验重*/
function checkName(id,value){
	var result=true;
	$.ajax( {  
		 url: rootpath+'/dictionary/checkValue.shtml',
		 data:{"id":id,"value":value},  
		 type:'post',  
		 cache:false,
		 async: false,
		 dataType:'json',  
		 success:function(data){
			 result=data;
		 }
	});
	return result;
}

/* 点击保存按钮 */
$(".btn.btn-primary.btn-sm.save").click(function(){
	var value=$(".form-control.name").val();
	if(value==""){
		layer.tips('必须填写！', ".form-control.name", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		return;
	}
	var url=rootpath+"/companyCategory/saveOrUpdate.shtml";
	var id=$(".form-control.id").val();
	var pid=$(".form-control.pid").val();
	var datas={"id":id,"pid":pid,"value":value};
	var result=checkName(id,value);
	debugger
	if(result){
		layer.tips('已经存在！', ".form-control.name", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		return;
	}
	debugger
	$.post(url,datas,function(data){
		if(data.status){
			$(".form-control.id").val("");
			$(".form-control.pid").val("");
			$(".form-control.name").val("");
			layer.msg("成功操作"+data.info+"条数据！", {icon: 6});
			closelayer();
			setTimeout(function(){
				loadPaging(1);
			},800);
		}else{
			layer.msg(data.info, {icon: 5});
		}
	});
});