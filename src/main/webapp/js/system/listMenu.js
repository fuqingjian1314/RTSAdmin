$(document).ready(function() {
		createTree();//初始化ztree
		var divheight=$(window).height();//初始化修改div的高度
		divheight=divheight-65;
		$(".panel-body").css({height:divheight});
		
		$("#child_sub").click(function() {
			$("#child_sub").attr("disabled","disabled");
			var menu_name = checkEmpty($("#chmenu_name"), "请输入名称");
			var name = $("#chmenu_name").val();
			var desc = $("#chmenu_desc").val();
			var sort = $("#chmenu_order").val();
			var url = $("#chmenu_url").val();
			var icons = $("#chmenu_icon").val();
			var pid = parentNode.id;
			if (menu_name) {
				$.post(rootpath+'/menu/saveOrUpdate.shtml', {
					pid : pid,
					name : name,
					description : desc,
					sort : sort,
					icon : icons,
					url : url
				}, function(datas) {
					if (datas.status == true) {
						var zTree = $.fn.zTree.getZTreeObj("orgtree");
						var vl = $.parseJSON(datas.data);
						if(vl.pid==0){
							icon=rootpath+'/css/zTreeStyle/img/diy/1_open.png'							
						}else{
							icon=rootpath+'/css/zTreeStyle/img/diy/3.png'
						}
						var nodes = {
							id : vl.id,
							name : vl.name,
							open : true,
							icon : icon
						};
						zTree.addNodes(parentNode, nodes);
						$("#childTreeform").hide();
						layer.msg(datas.info, {icon: 6});
					} else {
						$("#child_sub").removeAttr("disabled");
						layer.msg(datas.info, {icon: 5});
					}
				}, 'json');
			}else{
				$("#child_sub").removeAttr("disabled");
			}
		});
		//添加根节点
		$("#add_sub").click(function() {
			$("#add_sub").attr("disabled","disabled");
			var menu_name = checkEmpty($("#menu_name"), "请输入名称");
			var name = $("#menu_name").val();
			var desc = $("#menu_desc").val();
			var sort = $("#menu_order").val();
			var url = $("#menu_url").val();
			var icons = $("#menu_icon").val();
			if (menu_name) {
				$.post(rootpath+'/menu/saveOrUpdate.shtml', {
					name : name,
					description : desc,
					sort : sort,
					icon : icons,
					url : url
				}, function(datas) {
					if (datas.status == true) {
						var zTree = $.fn.zTree.getZTreeObj("orgtree");
						var vl = $.parseJSON(datas.data);
						var icon="";
						if(vl.pid==0){
							icon=rootpath+'/css/zTreeStyle/img/diy/1_open.png'							
						}else{
							icon=rootpath+'/css/zTreeStyle/img/diy/3.png'
						}
						var nodes = {
							id : vl.id,
							name :name,
							open : true,
							icon : icon
						};
						zTree.addNodes(null, nodes);
						$("#Treeform").hide();
						$("#menu_name").val("");
						$("#menu_desc").val("");
						$("#menu_order").val("");
						$("#menu_url").val("");
						$("#menu_icon").val("");
						$("#search_icon").val("");
						layer.msg(datas.info, {icon: 6});
					} else {
						$("#add_sub").removeAttr("disabled");
						layer.msg(datas.info, {icon: 5});
					}
				}, 'json');
			}else{
				$("#add_sub").removeAttr("disabled");
			}
		});
		//修改
		$("#mody_sub").click(function() {
			$("#mody_sub").attr("disabled","disabled");
			var menu_name = checkEmpty($("#upmenu_name"), "请输入名称");
			var name = $("#upmenu_name").val();
			var desc = $("#upmenu_desc").val();
			var url = $("#upmenu_url").val();
			var icons = $("#upmenu_icon").val();
			var sort = $("#upmenu_order").val();
			var id = $("#Id").val();
			if (menu_name) {
				$.post(rootpath+'/menu/saveOrUpdate.shtml', {
					id:id,
					name : name,
					description : desc,
					sort : sort,
					icon : icons,
					url : url
				}, function(datas) {
					if (datas.status == true) {
						var zTree = $.fn.zTree.getZTreeObj("orgtree");
						var vl = $.parseJSON(datas.data);
						console.info(vl);
						var nodes = zTree.getNodeByParam("id", vl.id, null);
						console.info(nodes);
						nodes.name = vl.name;
						zTree.updateNode(nodes);
						$("#upTreeform").hide();
						layer.msg(datas.info, {icon: 6});
					} else {
						layer.msg(datas.info, {icon: 5});
						$("#mody_sub").attr("disabled","disabled");
					}
				}, 'json');
			}else{
				$("#mody_sub").removeAttr("disabled");
			}
		});
		//
		/*新增*/
		selectCity("#menu_icon","#menu_btn");
		/*修改*/
		selectCity("#upmenu_icon","#upmenu_btn");
		/*树上面新增*/
		selectCity("#chmenu_icon","#chmenu_btn");

		/*鼠标移开面板隐藏*/
		$('#selectItem').mouseleave(function(){
			$('#selectItem').hide();
			layer.closeAll();
		});

		//给文本框绑定keydown事件
		$("#search_icon").keyup(function (e) {
			var iconValue = $("#search_icon").val();
			// if(iconValue == null || iconValue == ""){
			// 	layer.tips("请输入查询的图标", $("#search_icon"),{tips: 1, time: 2000});
			// 	return;
			// }
			//如果不存在，直接从后台获取
			if(applicationIconList == null || applicationIconList == ''){
				alert(1);
				$.post(rootpath+'/menu/getFont.shtml', function(datas) {
					var objData = $.parseJSON(datas.data);
					applicationIconList = objData; //赋值给全局变量
				},'json');
			}
			var html = "";
			$.each(applicationIconList,function(index,item){
				if(item.indexOf(iconValue) >= 0){
					html += "<li><a href='javascript:void(0);' onclick='fzText(\""+applicationTextId+"\",\""+item+"\")'>"+"<i class='fa "+item+"'></i> "+item+"</a></li>";
				}
			});

			$("#myTab").html(html);
		});
	});
	
	function checkEmpty(obj, msg) {
		var check = false;
		var val = obj.val();
		if (val == "") {
			layer.msg(datas.info, {icon: 5});
		} else {
			check = true;
		}
		return check;
	};
	
	function createTree() {
		$.fn.zTree.init($("#orgtree"), setting);
	}
	 
	var parentNode;
	var setting = {
		async : {
			enable : true,
			url : rootpath+'/menu/menuList.shtml',
			type : "post",
			dataType : "json",
			autoParam : [ "id" ]
		},
		check : {
			enable : false,
			nocheckInherit : true
		},
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : true,
			nameIsHTML : true,
			showIcon: false,
			showTitle:false
		},
		edit : {
			enable : true,
			editNameSelectAll : true,
			showRenameBtn : false
		},
		data : {
			simpleData : {
				datatype : 'json',
				enable : true
			}
		},
		callback : {
			beforeRemove : beforeRemove,
			onClick : function(event, treeId, treeNode, clickFlag) {
				if(treeNode.id==0){return;}
				$("#upTreeform").show();
				$("#Treeform").hide();
				$("#childTreeform").hide();
				$.post(rootpath+'/menu/findmenu.shtml', {
					id : treeNode.id
				}, function(datas) {
					var data = $.parseJSON(datas.data);
					$("#Id").val(data.id);
					$("#upmenu_name").val(data.name);
					$("#upmenu_desc").val(data.description);
					$("#upmenu_order").val(data.sort);
					$("#upmenu_url").val(data.url);
					$("#upmenu_icon").val(data.icon);
					$("#upmenu_icon").parent("div").find("button").html("<i class='fa "+data.icon+"'></i>");
					//修改按钮不禁用
					$("#mody_sub").removeAttr("disabled");
				}, 'json');
			},
			onAsyncError : zTreeOnAsyncError,
			onAsyncSuccess : function(event, treeId, treeNode, msg) {
			}
		}

	};
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest,textStatus, errorThrown) {
		layer.msg("数据加载错误，请联系管理员！", {icon: 5});
	};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	};
	//删除结点
	function beforeRemove(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgtree");
		zTree.selectNode(treeNode);
		var $_result = false;
		//信息框-例2
		layer.msg('删除后不恢复！可能造成严重后果！', {
		  time: 0 //不自动关闭
		  ,btn: ['删除', '取消']
		  ,yes: function(index){
		    layer.close(index);
		    
			$.ajax({
				async : false,
				dataType : 'json',
				type : 'post',
				data : { id : treeNode.id},
				url : rootpath+'/menu/delete.shtml',
				success : function(datas) {
					if (datas.status) {
						zTree.removeNode(treeNode);
						layer.msg(datas.info, {icon: 6});
						$_result = true;
					}else{
						layer.msg(datas.info, {icon: 5});
					}
				}
			});
			$("#upTreeform").hide();
			$("#Treeform").hide();
			
		  }
		});
		return $_result;
	}
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		//悬浮
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)  return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn){
			btn.bind("click", function() {
			$("#childTreeform").show();
			$("#Treeform").hide();
			$("#upTreeform").hide();
			parentNode = treeNode;
			return false;
			});
		}
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	};
	function buttonOnClickAdd() {
		$("#Treeform").show();
		$("#upTreeform").hide();
		$("#childTreeform").hide();
	}
//保存全局的textId
var applicationTextId = null;
//前端缓存所有的图标信息
var applicationIconList = null;

var selectCity = function (textId,targetId) {
	var targetId = $(targetId);
	targetId.parent("span").parent("div").find("input").css({"cursor": "pointer" });
	targetId.parent("span").parent("div").click(function () {
		applicationTextId = textId;
		var div_width = $(textId).parent("div").width();
		var btn_width = targetId.parent("span").width();

		var A_top = targetId.offset().top + targetId.outerHeight(true) -1 ;  //  1
		var A_left = targetId.offset().left  - div_width + btn_width+1;

		$('#selectItem').show().css({ "display":"block","position": "absolute", "top": A_top + "px", "left": A_left + "px","width":div_width+"px"});

		$("#search_icon").val("");//置空搜索的文本框
		$.post(rootpath+'/menu/getFont.shtml', function(datas) {
			var html = "";
			var objData = $.parseJSON(datas.data);
			applicationIconList = objData; //赋值给全局变量
			$.each(objData,function(index,item){
				html += "<li><a href='javascript:void(0);' onclick='fzText(\""+textId+"\",\""+item+"\")'>"+"<i class='fa "+item+"' aria-hidden='true'></i> "+item+"</a></li>";
			});

			$("#myTab").html(html);
		},'json');
	});
}

/*赋值给文本框*/
function fzText(target,value){
	$(target).parent("div").find("button").html("<i class='fa "+value+"'></i>");
	$(target).val("fa "+value);
}

/*搜索图标*/
function searchFontIcon() {
	var search_icon = $("#search_icon").val();
	if(search_icon == null || search_icon == ""){
		//layer.msg("请输入查询的图标", {icon: 5, time: 2000});
		layer.tips("请输入查询的图标", $("#search_icon"),{tips: 1, time: 2000});
		return;
	}
	//不用过滤特殊字符、标签等，这是从缓存中查询

	$.post(rootpath+'/menu/searchFontIcon.shtml',{searchIcon: search_icon }, function(datas) {
		var html = "";
		var objData = $.parseJSON(datas.data);
		$.each(objData,function(index,item){
			html += "<li><a href='javascript:void(0);' onclick='fzText(\""+applicationTextId+"\",\""+item+"\")'>"+"<i class='fa "+item+"'></i> "+item+"</a></li>";
		});
		$("#myTab").html(html);
	},'json');
}

/*重置*/
function reset(){
	$("#search_icon").val(null);
	if(applicationIconList == null || applicationIconList == ""){
		$.post(rootpath+'/menu/getFont.shtml', function(datas) {
			var objData = $.parseJSON(datas.data);
			applicationIconList = objData; //赋值给全局变量
		},'json');
	}
	var html = "";
	$.each(applicationIconList,function(index,item){
		html += "<li><a href='javascript:void(0);' onclick='fzText(\""+applicationTextId+"\",\""+item+"\")'>"+"<i class='fa "+item+"'></i> "+item+"</a></li>";
	});
	$("#myTab").html(html);
}
