/* 初始化 start */
	$(function(){
		var divheight=$(window).height();//初始化修改div的高度
		divheight=divheight-65;
		 $(".hfit").css({height:divheight});
		 createTree();
		 // setTimeout(function(){
         //
		 // }, 800)
		 
		 
		 
	});
	
	/* 初始化 end */
	function createTree() {
		$.fn.zTree.init($("#orgtree1"), setting1);
	}
	/*树设置ztree*/
	var setting1 = {
		async : {
			enable : true,
			url : rootpath+'/authority/menuList.shtml',
			type : "post",
			dataType : "json",
			autoParam : [ "id" ]
		},
		data : {
			simpleData : {
				datatype : 'json',
				enable : true
			}
		},
		callback:{
			onClick:loadAuthoritisbyMenu,
			onAsyncSuccess:function () {
				// var treeObj = $.fn.zTree.getZTreeObj("orgtree1");
				// var nodes = treeObj.getNodes();
				// if (nodes.length>0) {
				// 	treeObj.selectNode(nodes[0]);
				// }
			}
		}

	};
	/*名称渲染*/
	function btnNameRender(e){
		var value=e.value;
		return "<button type=\"button\" class=\"btn btn-default\">"+value+"</button>"
	}

	/*加载菜单urls*/
	function loadMenuUrls(mid){
		var url=rootpath+"/authority/getMenuUrls.shtml";
		$.post(url,{menuId:mid},function(rows){
			$("#table_url_fqj").table(rows);
		});
	}
	/*加载菜单按钮*/
	function loadMenuBtns(mid){
		var url=rootpath+"/authority/getMenuBtns.shtml";
		$.post(url,{menuId:mid},function(rows){
			$("#table_button_fqj").table(rows);
		});
	}
	var menuId="";
	/*点击菜单加载菜单对应权限*/
	function loadAuthoritisbyMenu(event, treeId, treeNode) {
		menuId=treeNode.id;
		loadMenuUrls(treeNode.id);
		loadMenuBtns(treeNode.id);
	};
	function openDictlayer(){
 		dictindex_wj= layer.open({
		  type:1,
		  area: ['700px', '400px'],
		  content: $('#layer_Dictdiv_wj'),
		  end:function() {
          }
	   });
 	}
	/*添加菜单权限*/
	function addAuthoritybyMemu(){
		$("#urlsearch_input_fqj").bind("keypress",function(){
			seachurl();
		});
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
			return;
		}
		var url=rootpath+"/authority/sysUrls.shtml";
		$.post(url,{},function(data){
			$("#table_addurl_fqj").table(data);
			var dictindex_fqj= layer.open({
				type:1,
				area: ['500px', '500px'],
				btn: ['确定', '取消'],
				title :'系统url列表',
				content: $('#addurl_div_fqj'),
				yes: function(index, layero){
					var selects=$("#table_addurl_fqj").getTableSelects();
					if(selects.length==0){
						layer.msg("请选择需要添加的菜单！");
						return;
					}
					var urlsave=rootpath+"/authority/saveResourcesURLBTN.shtml";
					$.post(urlsave,{menuId:menuId,res:JSON.stringify(selects)},function(data){
						if(data.status){
							layer.close(index);
							loadMenuUrls(menuId);
							$("#urlsearch_input_fqj").unbind("keypress",function(){
								seachurl();
							});
							$("#urlsearch_input_fqj").val("");
						}
						layer.msg(data.info);
					});
					
				  },
				btn2: function(index, layero){
					$("#urlsearch_input_fqj").unbind("keypress",function(){
						seachurl();
					});
					$("#urlsearch_input_fqj").val("");
				  }
			
			});
		});
	}
	/*搜索url*/
	function seachurl(){
		var searchvalue=$("#urlsearch_input_fqj").val();
		var url=rootpath+"/authority/sysUrls.shtml";
		$.post(url,{mlike:searchvalue},function(data){
			$("#table_addurl_fqj").table(data);
		});
	}
	/*重置*/
	function reseturl(){
		$("#urlsearch_input_fqj").val("");
		seachurl();
	}
	/*添加菜单按钮权限*/
	function addAuthoritybtnbyMemu(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
			return;
		}
		var modifybtn_fqj= layer.open({
			type:1,
			area: ['500px', '250px'],
			title :'新增按钮',
			content: $('#savemodifybtn_div_fqj'),
			btn: '保存',
			yes:function(index, layero){
				var formdata=$("#formbtn_save_fqj").getFormData();
				formdata.pid=menuId;
				formdata.type=4;
				formdata.isHide=1;
				var url=rootpath+"/authority/saveResourcesBTN.shtml";
				$.post(url,formdata,function(data){
					layer.msg(data.info);
					if(data.status){
						layer.close(index);
						loadMenuBtns(menuId);
					}
				})
			}
		
		});
	}
	/*修改菜单按钮权限*/
	function modifyAuthoritybtnbyMemu(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
			return;
		}
		var selects=$("#table_button_fqj").getTableSelects();
		if(selects.length==0){
			layer.msg("请选择需要修改url！");
			return;
		}else if(selects.length>1){
			layer.msg("你选择了多条记录请选择一条url！");
			return;
		}
		$("#formbtn_save_fqj").setFormData(selects[0]);
		var modifyurl_fqj= layer.open({
			type:1,
			area: ['500px', '250px'],
			title :'修改按钮',
			content: $('#savemodifybtn_div_fqj'),
			btn: '保存',
			yes:function(index, layero){
				var formdata=$("#formbtn_save_fqj").getFormData();
				var url=rootpath+"/authority/updateResourcesURLBTN.shtml";
				$.post(url,formdata,function(data){
					layer.msg(data.info);
					if(data.status){
						layer.close(index);
						loadMenuBtns(menuId);
					}
				})
			}
		
		});
	}
	/*修改菜单权限*/
	function modifyAuthoritybyMemu(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
			return;
		}
		var selects=$("#table_url_fqj").getTableSelects();
		if(selects.length==0){
			layer.msg("请选择需要修改url！");
			return;
		}else if(selects.length>1){
			layer.msg("你选择了多条记录请选择一条url！");
			return;
		}
		$("#form_save_fqj").setFormData(selects[0]);
		var modifyurl_fqj= layer.open({
			type:1,
			area: ['500px', '260px'],
			title :'修改url',
			content: $('#modifyurl_div_fqj'),
			btn: '保存',
			yes:function(index, layero){
				var formdata=$("#form_save_fqj").getFormData();
				var url=rootpath+"/authority/updateResourcesURLBTN.shtml";
				$.post(url,formdata,function(data){
					layer.msg(data.info);
					if(data.status){
						layer.close(index);
						loadMenuUrls(menuId);
						
					}
				})
			}
		
		});
	}
	
	/*删除菜单权限*/
	function delectAuthoritybyMemu(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
		}
		var rows=$("#table_url_fqj").getTableSelects();
		if(rows.length==0){
			layer.msg('请选择要删除的url！');
			return;
		}
		var url=rootpath+"/authority/deleteResourcesURLBTN.shtml";
		$.post(url,{res:JSON.stringify(rows)},function(data){
			if(data.status){
				loadMenuUrls(menuId);
			}
			layer.msg(data.info);
		});
	}
	/*删除菜单权限*/
	function deleteAuthoritybtnbyMemu(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree1");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
		}
		var rows=$("#table_button_fqj").getTableSelects();
		if(rows.length==0){
			layer.msg('请选择要删除的url！');
			return;
		}
		var url=rootpath+"/authority/deleteResourcesURLBTN.shtml";
		$.post(url,{res:JSON.stringify(rows)},function(data){
			if(data.status){
				loadMenuBtns(menuId);
			}
			layer.msg(data.info);
		});
	}
	/*保存url和按钮资源*/
	function updateURL(index, layero){
		var formdata=$("#form_save_fqj").getFormData();
		var url=rootpath+"/authority/updateResourcesURLBTN.shtml";
		$.post(url,formdata,function(data){
			if(data.status){
				loadMenuUrls(menuId);
				loadMenuBtns(menuId);
			}
			layer.msg(data.info);
		})
		
	}
	/*切换权限类型tab*/
	function changeAuthType(obj){
		$(".active").removeClass("active");
		var pobj=$(obj).parent();
		pobj.addClass("active");
		var name=$(obj).text();
		if(name=="URL"){
			$("#table_url_fqj").css({display:""});
			$("#table_button_fqj").css({display:"none"});
			$("#btns_url_fqj").css({display:""});
			$("#btns_btn_fqj").css({display:"none"});
		}else if(name=="按钮"){
			$("#table_button_fqj").css({display:""});
			$("#table_url_fqj").css({display:"none"});
			$("#btns_url_fqj").css({display:"none"});
			$("#btns_btn_fqj").css({display:""});
		}
	}