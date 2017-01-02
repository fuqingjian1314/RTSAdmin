(function($){     
$.fn.table = function(rows) {      
     // Our plugin implementation code goes here.  
	var tableDom=$(this);
	var tby=$(this).children("tbody").remove();
	var ths=$(this).children("thead").children("tr").children("th");
	var rowClickFuc=$(this).attr("rowClick");
	var rowDbClickFuc=$(this).attr("rowDbClick");
	var fields=[];
	var renders=[];
	var checkRender=ths.first().attr("render");
	var ishascheck=ths.first().children(":checkbox").is(":checkbox");
	if(ishascheck){
		ths.splice(0,1);//删除第一个元素
	}
	ths.each(function(index,element){
		var field=$(element).attr("field");
		var render=$(element).attr("render");
		if(field){
			fields[index]=field;
			renders[index]=render;
		}
		$(element).children("span").remove();//先删除已经有的箭头排序
		$(element).unbind("click");//会绑定多次点击事件
		var sortimg=$("<span class=\"glyphicon glyphicon-arrow-up\" aria-hidden=\"true\"></span>").css({"display":"none"});
		$(element).append(sortimg);
		var is_all_sort=tableDom.attr("columSort");
		if(is_all_sort){
			$(element).click(function(e){
				$(this).siblings().children("span").css({"display":"none"})
				$(this).children("span").css({"display":""})
				var is_asc=$(this).children("span").hasClass("glyphicon-arrow-up");
				if(is_asc){
					$(this).children("span").removeClass("glyphicon-arrow-up").addClass("glyphicon-arrow-down");
					bubbleSortDesc(rows,field);
					tableReDraw(tableDom,rows,ishascheck,checkRender,fields,renders,rowClickFuc,rowDbClickFuc);
				}else{
					$(this).children("span").removeClass("glyphicon-arrow-down").addClass("glyphicon-arrow-up");
					bubbleSortAsc(rows,field);
					tableReDraw(tableDom,rows,ishascheck,checkRender,fields,renders,rowClickFuc,rowDbClickFuc);
				}
			});
		}
	});
	tableReDraw(tableDom,rows,ishascheck,checkRender,fields,renders,rowClickFuc,rowDbClickFuc);
	tableDom.children("thead").children("tr").children("th").children("input").bind("click",function(){
		var ck=$(this).is(':checked');
		var rows=$(this).parents("table").find("tr");
		rows.each(function(){
			$(this).find("td").first().find("input").prop("checked", ck);
		});
	});

}; 
function tableReDraw(tableDom,rows,ishascheck,checkRender,fields,renders,rowClickFuc,rowDbClickFuc){
	tableDom.children("tbody").remove();
	var tbody=$("<tbody></tbody>");
	for(var i=0;i<rows.length;i++){
		var row=rows[i];
		row.id=row.id+"";//处理long数据太长精度被修改的问题
		var tr=$("<tr></tr>").appendTo(tbody);
		var hiddendata=$("<input type='hidden'>").val(JSON.stringify(row));
		tr.append(hiddendata);
		if(ishascheck){
			var checkbox=$("<input type='checkbox'>");
			if(checkRender){
				var func=eval(checkRender);
				var e={};
				e.obj=checkbox;
				e.row=row;
				func(e);
			}
			$("<td></td>").appendTo(tr).append(checkbox);
		}
		
		for(var j=0;j<fields.length;j++){
			var value=row[fields[j]];
			if(renders[j]){
				var e={};
				e.value=value;
				e.row=row;
				e.rowindex = i;
				var  func=eval(renders[j]);
				value=func(e);
			}
			if(value!=null){
				$("<td>"+value+"</td>").appendTo(tr);
			}else{
				$("<td></td>").appendTo(tr);
			}
		}
		
		tr.click(function(e){
			if(e.target.tagName=="INPUT"){
				return;
			}
			var ck=$(this).find("td").first().find("input").is(':checked');
			ck=!ck;
			$(this).find("td").first().find("input").prop("checked", ck);
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
			if(rowClickFuc){
				var row_click_fuc=eval(rowClickFuc);
				row_click_fuc();
			}
		})
		tr.dblclick(function(e){
			if(rowDbClickFuc){
				var row_dbclick_fuc=eval(rowDbClickFuc);
				row_dbclick_fuc();
			}
		})

	}
	tableDom.children("thead").after(tbody);
}

/**
 * func 点击页数触发事件
 */
function tablePaginNum(pagingstart,pagingsize,pagercount,pagingUL_in,headOrend,func){
	var pagingUL=$(pagingUL_in);
	pagingUL.html("");
    var firstpageA=$("<a id='pfirst_fqj'></a>").text("首页").attr("href","javascript:void(0)").click(function(){
    	$(".active").removeClass("active");
    	$(this).addClass("active");
    	func(1);
    	tablePaginNum(1,pagingsize,pagercount,pagingUL,1,func);
    	$("#span_paging_sort").text(",当前第"+1+"页");
    	$("#pfirst_fqj").parent("li").addClass("disabled");
    	$("#ppre_fqj").parent("li").addClass("disabled");
    });
    var firstpageLI=$("<li></li>").append(firstpageA);
    pagingUL.append(firstpageLI);
    var beforpageA=$("<a id='ppre_fqj'></a>").text("上一页").attr("href","javascript:void(0)").click(function(){
    	var nowactive=$(".active");
    	var curpaging=parseInt($(".active").children("a").text());
    	if(curpaging<2){
    		return;
    	}
    	if(curpaging==2){
    		$("#pfirst_fqj").parent("li").addClass("disabled");
    		$("#ppre_fqj").parent("li").addClass("disabled");
    	}
    	nowactive.prev("li").addClass("active");
    	nowactive.removeClass("active");
    	func(curpaging-1);
    	if(curpaging==pagingstart){
    		tablePaginNum(curpaging-pagingsize,pagingsize,pagercount,pagingUL,2,func);
    	}
    	$("#span_paging_sort").text(",当前第"+(curpaging-1)+"页");
    	$("#pend_fqj").parent("li").removeClass("disabled");
    	$("#pnext_fqj").parent("li").removeClass("disabled");
    });
    var beforpageLI=$("<li></li>").append(beforpageA);
    pagingUL.append(beforpageLI);
    var pagingend=pagingsize+pagingstart-1;
    if(pagingend>pagercount){
    	pagingend=pagercount;
    }
    if(pagingstart<1){
    	pagingstart=1;
    }
    for(var i=pagingstart;i<=pagingend;i++){
    	var pageA=$("<a></a>").attr("href","javascript:void(0)").text(i).click(function(){
        	$(".active").removeClass("active");
        	$(this).parent("li").addClass("active");
        	var clickpaging=parseInt($(this).text());
        	func(clickpaging);
        	if(clickpaging==pagingstart&&clickpaging>1){
        		tablePaginNum(pagingstart-pagingsize+1,pagingsize,pagercount,pagingUL,2,func);
        	}
        	if(clickpaging==pagingend&&pagingend!=pagercount){
        		tablePaginNum(pagingend,pagingsize,pagercount,pagingUL,1,func);
        		
        	}
        	$("#span_paging_sort").text(",当前第"+clickpaging+"页");
        	if(clickpaging==pagercount){
        		$("#pend_fqj").parent("li").addClass("disabled");
            	$("#pnext_fqj").parent("li").addClass("disabled");
        	}else{
        		$("#pend_fqj").parent("li").removeClass("disabled");
            	$("#pnext_fqj").parent("li").removeClass("disabled");
        	}
        	if(clickpaging==1){
        		$("#pfirst_fqj").parent("li").addClass("disabled");
        		$("#ppre_fqj").parent("li").addClass("disabled");
        	}else{
        		$("#pfirst_fqj").parent("li").removeClass("disabled");
        		$("#ppre_fqj").parent("li").removeClass("disabled");
        	}
        	
        });
        var pageLI=$("<li></li>").append(pageA);
        if(pagingstart==i&&headOrend==1){
        	pageLI.addClass("active");
        }
        if(pagingend==i&&headOrend==2){
        	pageLI.addClass("active");
        }
        
        pagingUL.append(pageLI);
    }
    
    var nextpageA=$("<a id='pnext_fqj'></a>").text("下一页").attr("href","javascript:void(0)").click(function(){
    	var beforactive=$(".active");
    	var curpaging=parseInt($(".active").children("a").text());
    	if(curpaging>=pagercount){
    		return;
    	}
    	beforactive.next("li").addClass("active");
    	var nextpaging=parseInt(beforactive.next("li").children("a").text());
    	if(nextpaging==pagercount){
    		$("#pend_fqj").parent("li").addClass("disabled");
        	$("#pnext_fqj").parent("li").addClass("disabled");
    	}
    	beforactive.removeClass("active");
    	func(curpaging+1);
    	if(curpaging==pagingend){
    		tablePaginNum(curpaging+1,pagingsize,pagercount,pagingUL,1,func);
    	}
    	$("#span_paging_sort").text(",当前第"+(curpaging+1)+"页");
    	$("#pfirst_fqj").parent("li").removeClass("disabled");
    	$("#ppre_fqj").parent("li").removeClass("disabled");
    });
    var nextpageLI=$("<li></li>").append(nextpageA);
    pagingUL.append(nextpageLI);
    var endpageA=$("<a id='pend_fqj'></a>").text("尾页").attr("href","javascript:void(0)").click(function(){
    	$(".active").removeClass("active");
    	$(this).addClass("active");
    	func(pagercount);
    	tablePaginNum(pagercount-pagingsize+1,pagingsize,pagercount,pagingUL,2,func);
    	$("#span_paging_sort").text(",当前第"+pagercount+"页");
    	$("#pend_fqj").parent("li").addClass("disabled");
    	$("#pnext_fqj").parent("li").addClass("disabled");
    });
    var endpageLI=$("<li></li>").append(endpageA);
    pagingUL.append(endpageLI);
    
    return pagingUL;
}
/**
 * curpage 当前页
 * pagesize 每页多少天记录
 * rowcount 总的记录数
 * fuc 点击分页查询方法
 */
$.fn.tablePaging = function(curpage,pagesize,rowcount,fuc) { 
	var thisPaging=$(this);
	thisPaging.html("");//重新初始化的时候去掉以前内容
	var pagercount=parseInt((rowcount+pagesize-1)/pagesize);
	var recodeSpan=$("<span>").text("共"+rowcount+"条记录");
	var sumrowSpan=$("<span>").text(",共"+pagercount+"页");
	var pagingsortSpan=$("<span id='span_paging_sort'>").text(",当前第"+curpage+"页");
	var gotext=$("<span>").append(recodeSpan).append(sumrowSpan).append(pagingsortSpan).css({"color":"#337ab7","float":"left","line-height": "34px"});
	var goInput=$("<input>").attr("id","input_go_fqj").addClass("form-control");
	var goSpan=$("<span>").addClass("input-group-btn");
	var goButton=$("<button>").addClass("btn btn-default").css({"color":"#337ab7"}).attr("type","button").text("跳").click(function(){
		go_pagingtable(pagesize,fuc,pagercount,rowcount,thisPaging);
	});
	goSpan.append(goButton);
	var goInHtml="<input id='input_go_fqj' type='text' class='form-control'>"+
				"<span class='input-group-btn'>"+
					"<button class=\"btn btn-default\" style=\"color:#337ab7\" type=\"button\" onclick=\"go_pagingtable("+fuc+","+pagercount+")\">跳</button>"+
				"</span>";
	var pagerGoHtml=$("<div class='input-group' style='width: 80px;float: left;'>").append(goInput).append(goSpan);
    //gotext.next(pagerGoHtml);
	var pagingUL_html="<ul class='pagination' style='margin-top: 0px;float:right;'>";
	var pagingstart=curpage;
    var pagingsize=5;
    var func="";
	func=eval(fuc);
	var pagingUL=tablePaginNum(pagingstart,pagingsize,pagercount,pagingUL_html,1,func);
	thisPaging.append(gotext).append(pagerGoHtml).append(pagingUL);
    
    if(pagingstart==1){
    	$("#pfirst_fqj").parent("li").addClass("disabled");
		$("#ppre_fqj").parent("li").addClass("disabled");
    }
}; 
function tablePagingReDraw(curpage,pagesize,rowcount,fuc,thisPaging){
	var pagercount=parseInt((rowcount+pagesize-1)/pagesize);
	var recodeSpan=$("<span>").text("共"+rowcount+"条记录");
	var sumrowSpan=$("<span>").text(",共"+pagercount+"页");
	var pagingsortSpan=$("<span id='span_paging_sort'>").text(",当前第"+curpage+"页");
	var gotext=$("<span>").append(recodeSpan).append(sumrowSpan).append(pagingsortSpan).css({"color":"#337ab7","float":"left","line-height": "34px"});
	var goInput=$("<input>").attr("id","input_go_fqj").addClass("form-control");
	var goSpan=$("<span>").addClass("input-group-btn");
	var goButton=$("<button>").addClass("btn btn-default").css({"color":"#337ab7"}).attr("type","button").text("跳").click(function(){
		go_pagingtable(pagesize,fuc,pagercount,rowcount,thisPaging);
	});
	goSpan.append(goButton);
	var goInHtml="<input id='input_go_fqj' type='text' class='form-control'>"+
				"<span class='input-group-btn'>"+
					"<button class=\"btn btn-default\" style=\"color:#337ab7\" type=\"button\" onclick=\"go_pagingtable("+fuc+","+pagercount+")\">跳</button>"+
				"</span>";
	var pagerGoHtml=$("<div class='input-group' style='width: 80px;float: left;'>").append(goInput).append(goSpan);
    //gotext.next(pagerGoHtml);
	var pagingUL_html="<ul class='pagination' style='margin-top: 0px;float:right;'>";
	var pagingstart=curpage;
    var pagingsize=5;
    var func="";
	func=eval(fuc);
	var pagingUL=tablePaginNum(pagingstart,pagingsize,pagercount,pagingUL_html,1,func);
	thisPaging.append(gotext).append(pagerGoHtml).append(pagingUL);
    
    if(pagingstart==1){
    	$("#pfirst_fqj").parent("li").addClass("disabled");
		$("#ppre_fqj").parent("li").addClass("disabled");
    }
}

/**
 * 跳转翻页方法
 * @param fuc 查询列表方法
 * @param pagercount  总页数，不能超过总页数
 */
function go_pagingtable(pagesize,fuc,pagercount,rowcount,thisPaging){
	var func="";
	func=eval(fuc);
	var gopagin=$("#input_go_fqj").val();
	var reg = new RegExp("^[0-9]*$"); 
	if(!gopagin){
		layer.msg('请输入页数！');
		return;
	}
	if(!reg.test(gopagin)){  
		layer.msg("请输入数字!");  
		return;
    } 
	if(gopagin>pagercount){
		layer.msg('超过总页数！');
		return;
	}
	thisPaging.html("");
	func(gopagin);
	tablePagingReDraw(parseInt(gopagin),pagesize,rowcount,fuc,thisPaging);
	
}
/*获取table选中的行json数据*/
$.fn.getTableSelects = function() {    
	var selectsRows=[];
	var i=0;
	$(this).find(":checkbox:checked").each(function(){
		var rowstr=$(this).parent("td").parent("tr").find(":hidden").val();;
		if(rowstr){
			selectsRows[i]=JSON.parse(rowstr);
			i++;
		}
	});
	return selectsRows;
};
/*获取table没有选中的行json数据*/
$.fn.getTableNoSelects = function() {    
	var selectsRows=[];
	var i=0;
	$(this).find(":checkbox").not(":checked").each(function(){
		var rowstr=$(this).parent("td").parent("tr").find(":hidden").val();
		if(rowstr){
			selectsRows[i]=JSON.parse(rowstr);
			i++;
		}
	});
	return selectsRows;
};
/*获取table没有选中的行json数据*/
$.fn.getTableALLRows = function() {    
	var selectsRows=[];
	var i=0;
	$(this).find(":hidden").each(function(){
		var rowstr=$(this).val();
		if(rowstr){
			selectsRows[i]=JSON.parse(rowstr);
			i++;
		}
	});
	return selectsRows;
};
$.fn.getTableALLRowsNum = function() {    
	var rowsNum=0;
	var rows=$(this).find("tr").has("td");
	rowsNum=rows.length
	return rowsNum;
};
$.fn.getActiveRow = function() {  
	var rowdata=$(this).find(".active").find(":hidden").val();
	return rowdata;
};
/*选中table的行*/
$.fn.checkedTableALLRows = function() {    
	var rows=$(this).find("tr");
	rows.each(function(){
		$(this).find("th").first().find("input").prop("checked", true);
		$(this).find("td").first().find("input").prop("checked", true);
	});
};
/*取消选中table的行*/
$.fn.notCheckedTableALLRows = function() {    
	var rows=$(this).find("tr");
	rows.each(function(){
		$(this).find("th").first().find("input").prop("checked", false);
		$(this).find("td").first().find("input").prop("checked", false);
	});
};
/*获取form数据*/
$.fn.getFormData = function() {    
	var data = {};
	$(this).serializeArray().map(function(x){data[x.name] = x.value;});
	return data;
};
/*设置form数据*/
$.fn.setFormData = function(row) {  
	$(this).find("input").each(function(){
		var name=$(this).attr("name");
		$(this).val(row[name]);
	});
	$(this).find("select").each(function(){
		var name=$(this).attr("name");
		$(this).val(row[name]);
	});
};
/*重置表单*/
$.fn.reset=function(){
	var id=$(this).attr("id");
	document.getElementById(id).reset();
}
/*冒泡排序*/
function bubbleSortAsc(rows,field){
	var length=rows.length;
	var tempRow={};
	for(var i=0;i<length-1;i++){
		var rowi=rows[i];
		for(var j=i+1;j<length;j++){
			var rowj=rows[j];
			var columValuei=rowi[field]+"";
			var columValuej=rowj[field]+"";
			//columValuei>columValuej,rowi>rowj
			if(columValuei.localeCompare(columValuej)>0){
				tempRow=rows[i];
				rows[i]=rows[j];
				rows[j]=tempRow;
			}
		}
	}
	console.log(1);
}
function bubbleSortDesc(rows,field){
	var length=rows.length;
	var tempRow={};
	for(var i=0;i<length-1;i++){
		var rowi=rows[i];
		for(var j=i+1;j<length;j++){
			var rowj=rows[j];
			var columValuei=rowi[field]+"";
			var columValuej=rowj[field]+"";
			//columValuei>columValuej,rowi>rowj
			if(columValuei.localeCompare(columValuej)<0){
				tempRow=rows[i];
				rows[i]=rows[j];
				rows[j]=tempRow;
			}
		}
	}
}
})(jQuery);
