	/**
   		1.page:必须是json对象
    	2.方法里判断page是否是json对象
    	3.
    	
    */
    function ajaxPage(page,queryFormId,url){
    	if(page == null || page.totalRecord == 0){
    		return;
    	}
    	//var jsonPage = $.parseJSON(page);//page对象转成json对象
    	//var items = page.items;//查询结果列表
    	var index=page.index;//当前页
    	var totalPage=page.totalPage;//总页
    	var totalRecord=page.totalRecord;
    	//商标最多5000条
    	if(totalRecord == 5000 ){
    		totalPage =totalPage-1;
    	}
    	var size=page.size;//页大小
    	var prePage=index-1;//上一页
    	var nextPage=index+1;//下一页
    	
    	var html = new Array();
    	html.push("");
    	html.push("<span class=\"page-total\">共<span class=\"page-total\">"+totalRecord+"</span>项， <span class=\"page-total\">共"+totalPage+"</span>页</span>");
    	if(index != 1){
	    	html.push("<a href=\"javascript:void(0);\" class=\"prevPage\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+prePage+")\"><i class=\"fa fa-caret-left\"></i><span>上一页</span></a>");
    	}else{
    		html.push("<a href=\"javascript:void(0);\" class=\"prevPage\"><i class=\"fa fa-caret-left\"></i><span>上一页</span></a>");
    	}
    	
    	if(totalPage<=5){
        	for(var i = 1 ;i<=totalPage;i++){
            	if(i == index){
    	        	html.push("<a href=\"javascript:void(0);\" class=\"active\">"+i+"</a>");
            	}else{
            		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+i+")\">"+i+"</a>");
            	}
            	
            }
        }else{
        	if(index <=3){//当前面为前3页
        		for(var i = 1;i<=totalPage && i<=index + 2;i++){
        			if(i == index){
        	        	html.push("<a href=\"javascript:void(0);\" class=\"active\">"+i+"</a>");
                	}else{
                		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+i+")\">"+i+"</a>");
                	}
        		}
        		html.push("<a href=\"javascript:void(0);\">...</a>");
        		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+totalPage+")\">"+totalPage+"</a>");
        	}else if(totalPage - index <=2){//当前页为后三页
        		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+",1)\">1</a>");
        		html.push("<a href=\"javascript:void(0);\">...</a>");
        		for(var i = index - 2;i<=totalPage && i<=index + 2;i++){
        			if(i == index){
        	        	html.push("<a href=\"javascript:void(0);\" class=\"active\">"+i+"</a>");
                	}else{
                		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+i+")\">"+i+"</a>");
                	}
        		}
        	}else{
        		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+",1)\">1</a>");
        		html.push("<a href=\"javascript:void(0);\">...</a>");
        		for(var i = index - 2;i<=totalPage && i<=index + 2;i++){
        			if(i == index){
        	        	html.push("<a href=\"javascript:void(0);\" class=\"active\">"+i+"</a>");
                	}else{
                		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+i+")\">"+i+"</a>");
                	}
        		}
        		html.push("<a href=\"javascript:void(0);\">...</a>");
        		html.push("<a href=\"javascript:void(0);\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+totalPage+")\">"+totalPage+"</a>");
        	}
        }
    	if(index != totalPage){
	        html.push("<a href=\"javascript:void(0);\" class=\"nextPage\" onclick=\"jumpPage('"+queryFormId+"','"+url+"',"+size+","+nextPage+")\"><span>下一页</span><i class=\"fa fa-caret-right\"></i></a>");
    	}else{
    		html.push("<a href=\"javascript:void(0);\" class=\"nextPage\"><span>下一页</span><i class=\"fa fa-caret-right\"></i></a>");
    	}
    	
    	$("#pageContent").empty();
    	$("#pageContent").append(html.join(" "));
    }
    
    function jumpPage(queryFormId,url,size,index){
    	$("input[page-mark='size']").val(size);
    	$("input[page-mark='index']").val(index);
    	$.ajax({
    		url:url,
    		data:$("#"+queryFormId).serialize(),
    		type:"post",
    		success:function(data){
   				drawHtml(data);
    		}
    	});
    	
    }