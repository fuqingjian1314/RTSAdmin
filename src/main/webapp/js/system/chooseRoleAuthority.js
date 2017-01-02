	/* 初始化 start */
	$(function(){
		 var divheight=$(window).height();//初始化修改div的高度
		 divheight=divheight-65;
		 $(".panel-body").css({height:divheight});
		 getRoleList();
		 //$("#test").tablePaging(1,15,100,test);
/*		 setTimeout(function(){
			 var treeObj = $.fn.zTree.getZTreeObj("orgtree2");
			 var nodes = treeObj.getNodes();
			 if (nodes.length>0) {
			 	treeObj.selectNode(nodes[0]);
			 } 
		 }, 400)*/
	});
	/* 初始化 end */
	
	function createTree() {
		$.fn.zTree.init($("#orgtree2"), setting2);
	}
	function test(a){
		alert(a);
	} 
	var setting2 = {
		async : {
			enable : true,
			url : 'roleMenuList.shtml',
			type : "post",
			dataType : "json",
			autoParam : [ "id" ]
		},
		check : {
			enable : true,
			nocheckInherit : true
		},
		view : {
			selectedMulti : true
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
/*				var treeObj = $.fn.zTree.getZTreeObj("orgtree2");
				var nodes = treeObj.getNodes();
				if (nodes.length>0) {
					treeObj.selectNode(nodes[0]);
				}*/
			}
		}
		
		

	};
	var setting3 = {
			check : {
				enable : true,
				nocheckInherit : true
			},
			view : {
				selectedMulti : true
			},
			data : {
				simpleData : {
					datatype : 'json',
					enable : true
				}
			},
			callback:{
				onClick:loadAuthoritisbyMenu
			}

		};
	/*名称渲染*/
	function btnNameRender(e){
		var value=e.value;
		return "<button type=\"button\" class=\"btn btn-default\">"+value+"</button>"
	}
	/*名称渲染*/
	function btncheckRender(e){
		var obj=e.obj;
		var row=e.row;
		if(row.isHide){
			obj.prop("checked", row.isHide);
		}
		
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
		loadMenuBtns(treeNode.id);
	};
	/**
	 * 加载角色列表
	 */
	function getRoleList(){
		var url="getrolelist.shtml";
		$.post(url,{},function(data){
			var html="";
			for(var i=0;i<data.length;i++){
				html+='<a href="#" roleid='+data[i].id+' class="list-group-item" onclick="getMemuByRole(this)">'+data[i].name+'</a>'
			}
			$("#div_rolelist_fqj").html(html);
			$("#div_rolelist_fqj").children("a").first().click();
		});
	}
	/**
	 * 点击角色获取菜单
	 */
	function getMemuByRole(obj){
		$(".list-group-item.active").removeClass("active");
		var roleId=$(obj).attr("roleid");
		$(obj).addClass("active");
		$.post("roleMenuList.shtml",{roleId:roleId},function(result){
		    $.fn.zTree.init($("#orgtree2"), setting3,result);
		});
	}
	/*获取角色对应菜单*/
	function saveRoleMenusList(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree2");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length<1){
			layer.msg("请选择菜单！");
			return;
		}
		var roleId=$(".list-group-item.active").attr("roleid")
		var rolemenus=[];
		for(var i=0;i<nodes.length;i++){
			var node=nodes[i];
			var rolemenu={};
			rolemenu.roleId=roleId;
			rolemenu.resourcesId=node.id;
			rolemenus[i]=rolemenu;
		}
		var data={};
		data.roleId=roleId;
		data.rrsstr=JSON.stringify(rolemenus);
		var url="modifyRoleMenus.shtml";
		$.post(url,data,function(result){
			if(result.status==true){
				layer.msg(result.info);
			}else{
				layer.msg(result.info);
			}
		});
	}
	/*保存菜单按钮*/
	function saveMenuBTNs(){
		var treeObj=$.fn.zTree.getZTreeObj("orgtree2");
		var nodes = treeObj.getSelectedNodes();
		if(nodes==""||nodes==null){
			layer.msg('请选择菜单！');
		}
		var rowsno=$("#table_button_fqj").getTableNoSelects();
		var rowsyes=$("#table_button_fqj").getTableSelects();
		var url=rootpath+"/authority/updateResourcesBTNisHide.shtml";
		$.post(url,{rrsno:JSON.stringify(rowsno),rrsyes:JSON.stringify(rowsyes)},function(data){
			if(data.status){
				loadMenuBtns(menuId);
			}
			layer.msg(data.info);
		});
	}
	function checktableallrows(){
		alert($("#table_url_fqj").find(":checkbox:checked"));
	}