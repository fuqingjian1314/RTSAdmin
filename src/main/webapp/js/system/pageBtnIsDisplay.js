//根据url获取当前的菜单
/**
 * 使用ajax处理按钮
 */
function destroyNotCompetenceBtn(){
	
	var url = window.location.href;
	//获取当前菜单对应的url地址
	var url2 =  url.substring(0, url.lastIndexOf('DGGTradeAdmin')+5);
	var param = window.location.search;
	var mid = null;
	if(param != null){
		mid = param.substring(param.lastIndexOf('mid=')+4,param.length);
	}
	
	$.ajax({
		url: url2 + "/authority/getMenuBtns.shtml",
		type:"post",
		data:{menuId:mid},
		success:function(data){
			console.debug(data);
			for(var i = 0; i < data.length; i++){
				if(data[i].isHide == 0){
					var idObject = document.getElementById(data[i].resKey); 
				    if (idObject != null) 
				          idObject.parentNode.removeChild(idObject);
				    
				}
			}
			
		}
		
	});
}

/**
 * 销毁没有权限的按钮（使用拦截器拦截请求）
 * @param btnList
 */
function destroyNotCompetenceBtn1(btnList){

	for(var i = 0; i < btnList.length; i++){
		if(btnList[i].isHide == 0){
			var idObject = document.getElementById(btnList[i].resKey); 
		    if (idObject != null) 
		          idObject.parentNode.removeChild(idObject);
		    
		}
	}
}
