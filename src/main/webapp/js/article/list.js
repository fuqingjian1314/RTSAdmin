	$(function(){
		loadPaging(1);
		var divheight=$(window).height();//初始化修改div的高度
		divheight=divheight-130;
		$(".hfit").css({height:divheight});
	});
	 $(".btn.btn-success.search").click(function(){
		 loadPaging(1);
	 });
	 $(".btn.btn-success.reset").click(function(){
		$("#searchForm")[0].reset();
		$("#aSite").selectpicker('refresh');
		$("#aTpId").selectpicker('refresh');
		loadPaging(1);
	 });
	function getFromData(formId){
		var data = {};
		$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
		return data;
	}
 	/* 分页start */
 	/*点击分页时加载数据*/
	function loadArticle(pageNum){
		var url=rootpath+"/article/articleData.shtml";
		var offSet=(pageNum-1)*10;
		var date=getFromData("searchForm");
		date.offSet=offSet;
		date.pageSize=10;
		$.post(url,date,function(data){
			var rows=data.items;
			$("#article_table_wj").table(rows);//给table 填充数据
		});
	}
 	/* 初次初始化时调用 */
	function loadPaging(pageNum){
		var url=rootpath+"/article/articleData.shtml";
		var offSet=(pageNum-1)*10;
		var date=getFromData("searchForm");
		date.offSet=offSet;
		date.pageSize=10;
		$.post(url,date,function(data){
			var rows=data.items;
			var count=data.rowsCount;
			$("#article_table_wj").table(rows);//给table 填充数据
			$("#tablePaging").tablePaging(1,10,count,"loadArticle"); //初始化分页插件
		});
	}
	/*按钮渲染 start*/
	 
	function renderTime(e){
		return timeStamp2String(e.row.atAddtime)
	}
	function renderOP(e){
		var id= e.row.atId;
		return '<a href="'+rootpath+'/article/edit.shtml?id='+id+'" class="btn btn-success btn-sm" >编辑</a>&nbsp;&nbsp;<button type="button" class="btn btn-warning btn-sm" onclick="del(\''+id+'\')" >删除</button>';
	}
	function del(id){
		var url=rootpath+"/article/delete.shtml"
		layer.msg('确定删除数据吗?',{time:0,btn:['确定', '取消'],yes: function(index){ layer.close(index);
			$.ajax({
				 type:"post",
				 url:url,
				 dataType:"json",
				 data:{id:id},
				 success:function(data){
					 if (data.status) {
						layer.alert("成功删除"+data.info+"条数据！", {icon: 6, title: '提示'});
						setTimeout(function(){
							loadPaging(1);
						},800);
					 }else{
						 layer.alert(data.info, {icon: 5, title: '提示'});
					 }
				 }
			});
		}});
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