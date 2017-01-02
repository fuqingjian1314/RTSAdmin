	/* 初始化 start */
 	$(function(){
		 $('.tree').treegrid( {treeColumn: 1});
		 var divheight=$(window).height();//初始化修改div的高度
		 divheight=divheight-65;
		 $(".panel-body").css({height:divheight});
 		 var spid= $("#dic_firstpid_wj").val();
 		 $("a[data-id="+spid+"]").addClass("active");
 		
 	});
 	/* 初始化 end */
 	var dic_frompid_wj=0;
 	function openDictlayer(){
 		dictindex_wj= layer.open({
		  type:1,
		  area: ['700px', '400px'],
		  content: $('#layer_Dictdiv_wj'),
		  end:function() {
			$("#dic_id_wj").val("")
			$("#dic_pid_wj").val("");
			$("#dic_name_wj").val("");
			$("#dic_desc_wj").val("");
			$("#dic_code_wj").val("");
			$("#dic_sort_wj").val("");
			//$("#dic_selectype_wj").val("");
		    $("#dic_id_wj").removeAttr("disabled");
			$("#dic_pid_wj").removeAttr("disabled");
			$("#dic_name_wj").removeAttr("disabled");
			$("#dic_code_wj").removeAttr("disabled");
			$("#dic_sort_wj").removeAttr("disabled");
			$("#dic_selectype_wj").removeAttr("disabled");
			$("#saveOrupdate_button_wj").removeAttr("disabled");
			$("#dic_desc_wj").removeAttr("disabled");
          }
	   });
 		
		$(".layui-layer-close").click(function(){
	 		layer.closeAll('tips');
	 	});
 	}
 	
 	function closeDictlayer(){
 		layer.close(dictindex_wj);
 		layer.closeAll('tips');
  	}
 	/* 点击子添加按钮 */
	$("#addsubdic_button_wj").click(function() {
		var b=false;
		dic_frompid_wj=1;
		$("#treebody").find("input").each(function(i,dom){
			if(dom.checked){
				b=dom.checked
			}
		});
		if(!b){
			if(dic_frompid_wj!=0){
				$("#dic_pid_wj").val($(".active").filter("a").attr("data-id"));
			}
		}
		openDictlayer();
 	});
	
 	/* 点击根添加按钮 */
	$("#addParendic_button_wj").click(function() {
		$("#dic_pid_wj").val(0);
		dic_frompid_wj=0;
		openDictlayer();
 	});
 	
 	/* 点击搜索按钮 */
 	$("#dicsearch_button_wj").click(function() {
 		if($("input[name=dicName]").val().length>0){
 			dicSearchRefresh();
 		}else{
 			 layer.tips('请输入关键字',$("input[name=dicName]"), {
                 tips : 1
             });
 		}
 	});
 	//选择行事件
 	function changetr_wj(obj){
 		$("tbody#treebody > tr").removeClass("success");
 		$(obj).addClass("success");
 		var checkbox=$(obj).children("td").children("input")[0];
 		var cv=checkbox.checked
 		$("#treebody").find("input").each(function(i,dom){
			dom.checked =false;
		});
 		checkbox.checked =!cv;
 		if(checkbox.checked){
 			$("#dic_pid_wj").val($(checkbox).val());
 		}else{
 			$("#dic_pid_wj").val($(".active").filter("a").attr("data-id"));
 		}
 	}
 	
 	/* 点击选择所有按钮 */
	$("#diccheckboxAll_wj").click(function() {
		var that=$(this)[0].checked;
		$("#treebody").find("input").each(function(i,dom){
			dom.checked =that;
		});
 	});
 	
 	/* 点击重置按钮 */
	$("#dicRefresh_button_wj").click(function() {
		$("input[name=dicName]").val("");
 		dicSearchRefresh();
 	});
 	
 	/* 搜索 或刷新 start */
 	function dicSearchRefresh(){
 		var url=rootpath+"/dictionary/ajaxlist.shtml";
			$.post(url,{dicName:$("input[name=dicName]").val()},function(data){
				var htmlA="";
				var active='';
				$.each(data.leftList,function(i,n){
					/*console.info(i);*/
					if(i==0){
						active='active';
					}else{
						active='';
					}
 					htmlA+='<a data-id="'+n.id+'" onclick="getChildDicWJ(this,'+n.id+')" class="list-group-item '+active+'">'+n.value+'<span style="float:right;"> '+
 					'<span onclick="searchDic_wj('+n.id+')" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;';
 					if(n.type!=0){
 						htmlA+='<span onclick="editDic_wj('+n.id+',0)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;'+
				   	 	'<span onclick="trashDic_wj('+n.id+',0)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>';
 					} 
 					htmlA+='</span></a>';
 					
				});
				$(".list-group").html(htmlA);
				
			var treehtml='';
			$.each(data.secondList,function(i,n){
				var clazz='';
				if(n.pid>0){
					clazz='treegrid-'+n.id +' treegrid-parent-'+n.pid;
				}else{
					clazz='treegrid-'+n.id;
				}
				n.value=(n.value==null || n.value==undefined)?(""):(n.value);
				n.code=(n.code==null || n.code==undefined)?(""):(n.code);
				n.sort=(n.sort==null || n.sort==undefined)?(""):(n.sort);
				var check='';
				if(n.type==0){
					check='<input type="checkbox" value="'+n.id+'">';
				}else{
					check='<input type="checkbox" value="'+n.id+'">';
				}
				treehtml+='<tr class="'+clazz+'" onclick="changetr_wj(this)" > <td>'+check+'</td> <td>'+n.value+'</td> <td>'+n.code+'</td> <td>'+n.sort+'</td> <td>'+
				'<span onclick="searchDic_wj('+n.id+')" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;';
				if(n.type!=0){
					treehtml+='<span onclick="editDic_wj('+n.id+',1)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;'+
				    '<span onclick="trashDic_wj('+n.id+',1)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span> </td></tr>';
				}	
			});
			data.pid=(data.pid==null || data.pid==undefined)?(0):(data.pid);
			$("#dic_pid_wj").val(data.pid);
			$("table").find("#treebody").html(treehtml);
 			$('.tree').treegrid( {treeColumn: 1});
			},'json');
 	}
 	/* 搜索 或刷新 end */
 	
 	/* 获取一条记录的子记录  start */
 	function getChildDicWJ(obj,pid){
 		$(".list-group-item").removeClass("active");
 		$(obj).addClass("active");
 		$("#dic_firstpid_wj").val($(obj).attr("data-id"));
 		var url=rootpath+"/dictionary/ajaxlist.shtml";
 		$.post(url,{pid:pid},function(data){
 		    var treehtml='';
			$.each(data.secondList,function(i,n){
				var clazz='';
				if(n.pid>0){
					clazz='treegrid-'+n.id +' treegrid-parent-'+n.pid;
				}else{
					clazz='treegrid-'+n.id;
				}
				n.value=(n.value==null || n.value==undefined)?(""):(n.value);
				n.code=(n.code==null || n.code==undefined)?(""):(n.code);
				n.sort=(n.sort==null || n.sort==undefined)?(""):(n.sort);
				var check='';
				if(n.type==0){
					check='<input type="checkbox" value="'+n.id+'">';
				}else{
					check='<input type="checkbox" value="'+n.id+'">';
				}
				treehtml+='<tr class="'+clazz+'" onclick="changetr_wj(this)" > <td>'+check+'</td> <td>'+n.value+'</td> <td>'+n.code+'</td> <td>'+n.sort+'</td> <td>'+
				'<span onclick="searchDic_wj('+n.id+')" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;';
				if(n.type!=0){
					treehtml+='<span onclick="editDic_wj('+n.id+',1)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;'+
				    '<span onclick="trashDic_wj('+n.id+',1)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span> </td></tr>';
				}
				
			});
			$("#dic_pid_wj").val(pid);
			$("table").find("#treebody").html(treehtml);
			$(obj)
			//更新
 			$('.tree').treegrid({treeColumn: 1});
 		},'json');
 	}
	/* 获取一条记录的子记录  end */
 
 	//编辑一条记录------------start
	function editDic_wj(id,type){
		dic_frompid_wj=type;
		var url=rootpath+"/dictionary/findDic.shtml";
		$.post(url,{id:id},function(data){
				data.value=(data.value==null || data.value==undefined)?(""):(data.value);
				data.code=(data.code==null   || data.code==undefined)?("") :(data.code);
				data.sort=(data.sort==null   || data.sort==undefined)?("") :(data.sort);
				data.description=(data.description==null   || data.description==undefined)?("") :(data.description);
				$("#dic_id_wj").val(data.id);
				$("#dic_pid_wj").val(data.pid);
				$("#dic_name_wj").val(data.value);
				$("#dic_code_wj").val(data.code);
				$("#dic_sort_wj").val(data.sort);
				$("#dic_selectype_wj").val(data.type);
				$("#dic_desc_wj").val(data.description);
				openDictlayer();
		},'json');
	}
	//编辑一条记录-----------end
	
	/* 查看一条记录 start */
	function searchDic_wj(id){
		var url=rootpath+"/dictionary/findDic.shtml";
		$.post(url,{id:id},function(data){
				data.value=(data.value==null || data.value==undefined)?(""):(data.value);
				data.code=(data.code==null   || data.code==undefined)?("") :(data.code);
				data.sort=(data.sort==null   || data.sort==undefined)?("") :(data.sort);
				data.description=(data.description==null   || data.description==undefined)?("") :(data.description);
				$("#dic_id_wj").val(data.id);
				$("#dic_pid_wj").val(data.pid);
				$("#dic_name_wj").val(data.value);
				$("#dic_code_wj").val(data.code);
				$("#dic_sort_wj").val(data.sort);
				$("#dic_selectype_wj").val(data.type);
				$("#dic_desc_wj").val(data.description);
				
				$("#dic_id_wj").attr("disabled","disabled");
				$("#dic_pid_wj").attr("disabled","disabled");
				$("#dic_name_wj").attr("disabled","disabled");
				$("#dic_code_wj").attr("disabled","disabled");
				$("#dic_desc_wj").attr("disabled","disabled");
				$("#dic_sort_wj").attr("disabled","disabled");
				$("#dic_selectype_wj").attr("disabled","disabled");
				$("#saveOrupdate_button_wj").attr("disabled","disabled");
				openDictlayer();
		},'json');
	}
	/* 查看一条记录 end */
	
	/* 删除一条记录 start */
	function trashDic_wj(id,type){
		layer.msg('确定删除选中的数据吗?',{time:0,btn:['确定', '取消'],yes: function(index){ layer.close(index);
			 dic_frompid_wj=type;
			 $.post(rootpath+'/dictionary/delete.shtml', {id:id}, function(datas) {
				if (datas.status) {
					if(type==0){//根节点删除
						layer.msg(datas.info, {icon: 6});
						$(".list-group-item:first-child").addClass("active");
						$("a[data-id="+datas.data+"]").remove();//删除左边的
						$("#treebody").html("");
						getChildDicWJ($(".active").filter("a"),$(".active").filter("a").attr("data-id"));
					}else{
						layer.msg(datas.info, {icon: 6});
						var id=$(".active").filter("a").attr("data-id");
						getChildDicWJ($(".active").filter("a"),id);
					}
				} else {
					layer.msg(datas.info, {icon: 5});
				}
			}, 'json');
		}});
	}
	/* 查看一条记录 end */
	 
	
	//添加或保存------------start
	$("#saveOrupdate_button_wj").click(function() {
	$("#saveOrupdate_button_wj").attr("disabled","disabled");
		if($("#dic_name_wj").val().length<=0 ){
			 layer.tips('名称为必填项！',$("#dic_name_wj"), {
			 	  tips:1
             });
			 $("#saveOrupdate_button_wj").removeAttr("disabled");
			return;
		}
		if($("#dic_selectype_wj").val().length<=0){
			 layer.tips('类型为必选项！',$("#dic_selectype_wj"), {
			 	  tips:1
            });
			$("#saveOrupdate_button_wj").removeAttr("disabled");
			return;
		}
		if($("#dic_code_wj").val().length<=0){
			 layer.tips('code为必填项，且保证唯一！',$("#dic_code_wj"), {
			 	  tips:1
            });
			$("#saveOrupdate_button_wj").removeAttr("disabled");
			return;
		}
		if(!/^[A-Za-z0-9_]+$/.test($("#dic_code_wj").val())){
			 layer.tips('code为字母或数字下划线的组合',$("#dic_code_wj"), {
			 	  tips:1
           });
			$("#saveOrupdate_button_wj").removeAttr("disabled");
			return;
		}
		var id = $("#dic_id_wj").val();
		var pid =$("#dic_pid_wj").val();
		var value = $("#dic_name_wj").val();
		var code = $("#dic_code_wj").val();
		var desc = $("#dic_desc_wj").val();
		var sort = $("#dic_sort_wj").val();
		var type = $("#dic_selectype_wj").val();
		if(checkCode(code,id)){ 
			$("#saveOrupdate_button_wj").removeAttr("disabled");
			return;
		} 
		$.post(rootpath+'/dictionary/saveOrUpdate.shtml', {id:id,pid : pid, value : value,description : desc, sort : sort, code : code ,type:type}, function(datas) {
			if (datas.status) {
				layer.msg(datas.info, {icon: 6});
				var n=jQuery.parseJSON(datas.data);
				if(dic_frompid_wj==0){//根数据
					if(id.length<=0){//添加
						var htmlA='<a data-id="'+n.id+'" onclick="getChildDicWJ(this,'+n.id+')" class="list-group-item">'+n.value+'<span style="float:right;"> '+
	 					'<span onclick="searchDic_wj('+n.id+')" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;';
	 					if(n.type!=0){
	 						htmlA+='<span onclick="editDic_wj('+n.id+',0)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;'+
					   	 	'<span onclick="trashDic_wj('+n.id+',0)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>';
	 					} 
	 					htmlA+='</span></a>';
						$(".list-group").append(htmlA);
						var selector=$('a[data-id='+n.id+']');
						getChildDicWJ(selector,n.id);
					}else{//修改
 						var amark=$("a[data-id="+n.id+"]")
						amark.remove();//删除左边的
						var htmlA='<a data-id="'+n.id+'" onclick="getChildDicWJ(this,'+n.id+')" class="list-group-item">'+n.value+'<span style="float:right;"> '+
	 					'<span onclick="searchDic_wj('+n.id+')" class="glyphicon glyphicon-eye-open" style="cursor: pointer;"></span>&nbsp;&nbsp;';
	 					if(n.type!=0){
	 						htmlA+='<span onclick="editDic_wj('+n.id+',0)" class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;&nbsp;'+
					   	 	'<span onclick="trashDic_wj('+n.id+',0)" class="glyphicon glyphicon-trash" style="cursor: pointer;"></span>';
	 					} 
	 					htmlA+='</span></a>';
						$(".list-group").append(htmlA);
						var selector=$('a[data-id='+n.id+']');
						getChildDicWJ(selector,n.id);
					}
				}else{//二级或以下数据
					getChildDicWJ($(".active").filter("a"),$("#dic_firstpid_wj").val());
				}
				closeDictlayer();
			} else {
				layer.msg(datas.info, {icon: 5});
			}
			$("#saveOrupdate_button_wj").removeAttr("disabled");
		}, 'json');
	});
	//添加或保存------------end
	function checkCode(code,id){
		var result=true;
		$.ajax({  
	         type: "post",  
	          url: rootpath+'/dictionary/checkCode.shtml',  
	         data: {id:id,code:code},  
	        async: false,  
	     success : function(data){  
				result=data;
				if(data){
					layer.tips('该 code 已经存在，请换一个！ ',$("#dic_code_wj"), { tips : 1 });
				}
	     	}  
	     }); 
		return result;
	}