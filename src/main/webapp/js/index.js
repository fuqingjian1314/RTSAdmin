

/*$(function(){
	//页面初始化时加载第一个菜单项，以及其子项，第一个子项对应的页面
	$("#topMenu").find("li:first-child").find("a").click();
});
*/
/**
 * 顶部菜单的点击事件
 * @param obj 点击的对象
 * @author huangliang
 */
/*function index_changeMenu(obj){
	
	$(obj).parent().parent().find("li").each(function (){
		$(this).find("a").removeClass("index_active_li_hl");
	});
	$(obj).addClass("index_active_li_hl");
	var pid=$(obj).attr("data-id");
	var pName = $(obj).text();
	index_loadMenuByPid(pid,pName);

}


*//**
 * 顶部菜单单击事件：加载右侧菜单列表
 * @param pid 上级菜单ID
 * @param pName 上级菜单名称 
 * @author huangliang
 *//*
function index_loadMenuByPid(pid,pName){
	if(pid == "" || pid == null){
		return;
	}
	$.ajax({
		type : "POST",
		data : {
			pid:pid,
			pName:pName
		},
		url :"loadMenu.shtml",
		dataType : 'html',
		success:function(data) {
			$("#rightMenu").empty();
			$("#rightMenu").append(data);
			$("#rightMenu").find("ul li:first-child").find("a").click();
		}
		
	});
}

*//**
 * 主窗体数据加载
 * @param url 为菜单项配置的页面URL地址
 * @author huangliang
 *//*
function index_loadMain(url){
	if(url == null || url == ""){
		return;
	}
	$.ajax({
		type : "POST",
		data : {},
		url :"/webpj/"+url,
		dataType : 'html',
		success:function(data) {
			$("#page-wrapper").empty();
			$("#page-wrapper").append(data);
		}
	});
}

*//**
 * 加载右侧菜单子项 ,二级，三级都是此方法
 * @param pid 上级ID
 * @param className 子项使用的样式名称 
 * @author huangliang
 *//*
function index_rightMenuChild(pid,className,obj){
	
	//已加载子菜单时，显示隐藏
	var ul = $(obj).parent().find("ul").get(0);
	if(ul != null && ul != undefined && ul !="" && ul.length !=0){
		if($(ul).is(":hidden")){
			$(ul).removeClass("index_hidden");
			return;
		}
		$(ul).addClass("index_hidden");
		return;
	}
	
	
	//还未加载子菜单时，加载菜单
	if(pid == null || pid == ""){
		return;
	}
	$.ajax({
		type : "POST",
		data : {pid:pid,
			className:className
			},
		url :"rightLoadMenu.shtml",
		dataType : 'html',
		success:function(data) {
			$(obj).parent().find("ul").remove();
			$(obj).parent().append(data);
		}
	});
}*/

function treeMenu(a,path){
    this.tree=a||[];
    this.groups={};
    this.rootPath=path;
    
};
var mcls=['nav','nav nav-second-level','nav nav-third-level','nav nav-forth-level'];
treeMenu.prototype={
init:function(pid){
    this.group();
    return this.getDom(this.groups[pid],0);
},
group:function(){
    for(var i=0;i<this.tree.length;i++){
        if(this.groups[this.tree[i].pid]){
            this.groups[this.tree[i].pid].push(this.tree[i]);
        }else{
            this.groups[this.tree[i].pid]=[];
            this.groups[this.tree[i].pid].push(this.tree[i]);
        }
    }
},
getDom:function(a,b){
    if(!a){return ''}
    var html='\n<ul '
    if(b==0){
    	html+='id="side-menu"';
    }
    	html+='class="'+mcls[b]+'">\n';
    for(var i=0;i<a.length;i++){
    	if(a[i].url != ""){
    		html+='<li><a href="javascript:void(0);" onclick="clickMenu(this);" data-id="'+a[i].id+'" aurl="'+this.rootPath+a[i].url+'?mid='+a[i].id+'"><i class="'+a[i].icon+'"></i>&nbsp;&nbsp;'+a[i].name;
    	}else{
    		html+='<li><a href="javascript:void(0);" onclick="clickMenu(this);" data-id="'+a[i].id+'" aurl="'+this.rootPath+a[i].url+'"><i class="'+a[i].icon+'"></i>&nbsp;&nbsp;'+a[i].name;
    	}
        
        if(this.getDom(this.groups[a[i].id],b+1)){
        	html+='<span class="fa arrow"></span>';
        }
        html+='</a>';
        html+=this.getDom(this.groups[a[i].id],b+1);
        html+='</li>\n';
    };
    html+='</ul>\n';

    return html;
}
};


