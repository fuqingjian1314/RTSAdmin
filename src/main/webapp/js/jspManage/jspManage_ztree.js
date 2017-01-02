	//var pathName = getRootPath();
	//全局通用树对象
	var zTreeObj;
	$(function(){
		//初始化树和列表高度
		var docH = $(window).height();
		$("#orgTree").height(docH-100);
		$("#editorCont").height(docH-85);
		//
		$("#searchKey").keydown(function(event){
			if(event.keyCode==13){
				selectNode();
			}
		});
		
		var setting = {
			async: {
				enable: true,
				url:getAsyncUrl,
				type:'post',
				dataType:'text',
				dataFilter:ajaxDataFilter,
				autoParam: ["id=dirPath"]
			},
			data:{
				/* key:{
					name:"fileName"
				}, */
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0,
					
				}
			},
			callback:{
				beforeClick: beforeClick,
				onClick:treeOnClick,
				onNodeCreated:onNodeCreated,
				onAsyncSuccess:onAsyncSuccess
			},
		}
		//初始化树
		zTreeObj = $.fn.zTree.init($("#orgTree"), setting, null);
	});
	//异步时，可对url进行操作的方法，也可直接把url写在async的url属性里
	function getAsyncUrl(treeId, treeNode) {
	    return rootpath+"/jspManage/loadFile.shtml";
	};
	//加载数据后，进行树渲染前的操作
	function ajaxDataFilter(treeId, parentNode, childNodes){
		for(var i= 0;i<childNodes.length;i++){
			childNodes[i].isParent=childNodes[i].isDir;
			childNodes[i].name=childNodes[i].fileName;
			childNodes[i].id=childNodes[i].fileAbsolutePath;
		}
		return childNodes;
	}
	
	//当节点被创建后触发
	function onNodeCreated(event, treeId, treeNode){
	}
	
	//异步树完成后执行
	function onAsyncSuccess(event, treeId, treeNode, msg){
		var node = zTreeObj.getNodeByParam("name","jsp", null);
		zTreeObj.expandNode(node, true, false, true);
	}
	//onClick事件前执行---非叶子节点时，不触发单击事件
	function beforeClick(treeId, treeNode, clickFlag){
		return !treeNode.isParent;
	}
	//onClick事件触发---仅对叶子节点有效，加载jsp文件内容
	function treeOnClick(event, treeId, treeNode){
		var filePath = treeNode.id;
		$("#filePath").val(filePath);
		$.post(rootpath+"/jspManage/loadHtmlNew.shtml",{filePath:filePath},function(data){
			/*UE.getEditor('editor').execCommand('cleardoc');
			var htmlArr = data.split("\n");
			for(var i = 0;i<htmlArr.length;i++){
				//htmlArr[i] = htmlArr[i].replace("$ET","\n");
				//UE.getEditor('editor').execCommand('inserthtml',htmlArr[i]);
				//UE.getEditor('editor').setContent(htmlArr[i],true);
				UE.getEditor('editor').setContent(htmlArr[i],true);
			}*/
			
			/******begin update by HouJH 2016-11-04***********/
			$("#codeContainer").val('');
			$("#codeContainer").val(data);
			$("#insertValueListener").click();
			/******end update by HouJH 2016-11-04***********/
		},"json");
	}
	
	
	
	//保存操作
	function submitForm(obj){
		//重复提交验证
		if($(obj).attr("isSubmit")==true || $(obj).attr("isSubmit") == 'true'){
			return ;
		};
		//开启锁
		$(obj).attr("isSubmit",true);
		
		var name = $("#submitForm").find("input[name='name']").val();
		
		if(name == ""){
			layer.tips("请输入机构名",$("#submitForm").find("input[name='name']"),{tip:1});
			$(obj).attr("isSubmit",false);			
			return ;
		}
		var url = rootpath+"/organization/saveOrUpdate.shtml"
		$.post(url,$("#submitForm").serialize(),function(data){
			if(data){
				$(".myCloseZ").click();
				//得到操作节点的父节点或当前节点
				var node =zTreeObj.getNodeByTId($(obj).attr("tId"));
				//如果添加或修改节点后按父节点刷新，就会存在递归查询，否则就会把有子节点的节点显示为叶子节点
				//zTreeObj.reAsyncChildNodes(parentNode, "refresh");
				
				//为了避免加载缓慢的情况，使用页面级的修改节点显示信息或添加节点
				if(data.status){
					var newNode = data.data;
					if(data.operateType == "add"){
						//添加节点后
						zTreeObj.addNodes(node,newNode);
					}else{
						//修改节点后
						node.name=newNode.name;
						zTreeObj.updateNode(node);
					}
				}
			}
		});
		//打开锁
		$(obj).attr("isSubmit",false);
	}
	
	//js获取项目根路径，如： http://localhost:8083/uimcardprj
	function getRootPath(){
	    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return(localhostPaht+projectName);
	}
	