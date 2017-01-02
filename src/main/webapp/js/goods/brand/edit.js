var classz='';
$(function(){
	var divheight=$(window).height();//初始化修改div的高度
	 divheight=divheight-55;
	 $(".hfit").css({height:divheight});
	 
	 var firstLi=$("#second").find("li.list-group-item:first");
	 choiceSecond(firstLi);
});
/* 初始化ue */
var ue = UE.getEditor('editor');

/* 分页顾问分页开始 */
function getFromData(formId){
	var data = {};
	$("#"+formId).serializeArray().map(function(x){data[x.name] = x.value;});
	return data;
}
function getPager(offset,url){
	var queryparams=getFromData("searchForm");
	queryparams.curIndex=offset;
	queryparams.requestMethod="getPager";
	queryparams.begin = $("#pageBegin").val();
	queryparams.end = $("#pageEnd").val();
	queryparams.businessPid=33;
	var url =  rootpath+"/adviser/findAdviserDate.shtml";
	$.post(url,queryparams,function(data){
		$("#adviser_conten_wj").html(data);
	},'html');
}
getPager(0);
//搜索方法
function searchIntro(){
	getPager(0);	
}

//重置搜索条件
function resetIntro(){
	$("input[name='adviserCond']").val('');
	getPager(0);
}
/* 分页顾问分页结束 */

/*选择顾问  */
function choice(number,name){
	$("input.form-control.selectAdviser.input_img").val(name)
	$("input[name='gddAdviserNum']").val(number);
	layer.msg("选择成功！");
	closChoiceAdviser();
}

var choice_adviser_wj_id=0;
function OpenChoiceAdviser(){
	choice_adviser_wj_id= layer.open({
	  title:"搜索顾问",
	  type:1,
	  area: ['800px', '600x'],
	  content: $('#choice_adviser_wj'),
	  end:function() { }
   });
}
function closChoiceAdviser(){
	layer.close(choice_adviser_wj_id);
}
$(".selectAdviser").click(function() {
	OpenChoiceAdviser();
});
$("span.more").click(function() {
	 $(this).css("display", "none");
	 $("div.se").css("display", "block");
	 $("span.less").css("display", "block");
	});
$("span.less").click(function() {
	$(this).css("display", "none");
	$("div.se").css("display", "none");
	$("span.more").css("display", "block");
});

$("span.btn.btn-default.qbtn").click(function(){
	var BrandNum = $("#gddRegistNum").val();
	BrandNum=$.trim(BrandNum);
	if(BrandNum==''){
		layer.tips('请输入商标注册号！', "input[name='gddRegistNum']", {
			tips: [1, 'rgba(195, 17, 17, 0.58)']
		});
		return;
	}
	var url=rootpath+'/goods/queryByKeyword.shtml';
	$.post(url,{regNo:BrandNum},function(data){
		  var spanHtml='';
		  var intClsName="";
		  var hasExist=false;
		  var ib=0;
		  var brand=data.brand;
		  if(brand==undefined || brand==null || brand==''){
			  layer.tips('根据该商标注册号，没有找到相关信息！', "input[name='gddRegistNum']", { tips: [1, 'rgba(195, 17, 17, 0.58)']});
			  clearDate();
			  return;
		  }
		 /* console.info(data);*/
		  $.each(data.brand,function(i,n){
			  if(n.FlowStatus=="202"){
				  ib++;
			  }else{
				  $.each(data.typeParent,function(i1,n1){
					  if(n1.id==n.IntCls){intClsName=n1.title;}
				  });
 				  $.each(data.hasList,function(i2,n2){
 					 if(n2.goodsDetail.gddBrandCategory==n.IntCls){ hasExist=true; return false; }
 					 else{ hasExist=false; }
				  });
 				  
 				  if(hasExist){
					  spanHtml+='<span data-intCls="'+n.IntCls+'" class="btn btn-warning category disabled" style="margin-top:10px;margin-bottom: 20px;">'+intClsName+'</span>&nbsp;&nbsp;';
 				  }else{
 					 spanHtml+='<span data-intCls="'+n.IntCls+'" class="btn btn-warning category" onclick="queryBrandDetail(this)" style="margin-top:10px;margin-bottom: 20px;">'+intClsName+'</span>&nbsp;&nbsp;';
 				  }
			  }
		  });
		  if(ib==data.brand.length){layer.msg("查询到的该商标信息，都为无效信息！"); }
		  clearDate();
		  $("div.choice_brand_div").html(spanHtml);
		  /* 默认选择第一个没有选择过的数据 */
		  $("span.btn.btn-warning.category").each(function(i,n){
			  if(!$(this).hasClass("disabled")){
				  queryBrandDetail($(this));
			  	  return false;
			  }
		  });
	});
});

function clearDate(){
	 //$("span.tip").text("")
	 $("input[name='gddBrandCategory']").val("");
	 $("input[name='gddBrandCategoryLable']").val("");
	 $("div.choice_brand_div").html("");
	 $("#second").html("");
	 $("#three").html("");
	 $("div.thirdT").html("");
	 $(".selectedthird").html("");
	 $("#brandDetailDiv").css("display", "none");
}

function queryBrandDetail(obj){
	if($(obj).hasClass("disabled")) return; 
	var BrandNum = $("#gddRegistNum").val();
	var inCtsl=$(obj).attr('data-intcls');
	$.ajax( {  
		 url: rootpath+'/goods/queryByNum.shtml',
		 data:{regNo : BrandNum,inCtsl:inCtsl},  
		 type:'post',  
		 cache:false,  
		 dataType:'json',  
		 success:function(data) {
			 if(!data.status){
				 layer.msg(data.info);
				 if(data.exist){ $(obj).addClass("disabled"); }
				 return;
			 }else if(data.status){
				 $("span[data-intcls]").removeClass("btn-success");
				 $("span[data-intcls]").addClass("btn-warning");
				 $(obj).removeClass("btn-warning");
				 $(obj).addClass("btn-success");
				 var d=data.de;
				 var t=data.tp;
				 	/* 图片 */
				   $("img.imageUrl").attr("src",d.ImageUrl);
				   $("input[name='gddImageUrlQcc']").val(d.ImageUrl);
				 	/*商标信息 */
				   $("#brandDetailDiv").css("display", "block");
				   $("span.brandName").text(d.Name);
				   $("input[name='gddBrandCategory']").val(d.IntCls);
				   $("input[name='gddBrandCategoryLable']").val(t.title);
				   $("span.brandCat").text(t.title);
				   $("span.brandServ").text(d.ListGroupItems.toString());
				   $("span.brandRegNum").text(d.RegNo);
				   $("span.brandState").text(d.FlowStatusDesc);
				   $("span.AppDate").text(d.AppDate);
				   /* 申请人信息 */
				   $("span.pName").text(d.ApplicantCn);
				   $("span.pEName").text(d.ApplicantEn);
				   $("span.pAddr").text(d.AddressCn);
				   /* 商标状态流程 */
				   var arr=d.FlowItems;
				   var htmlstr="";
				   if(arr!=null){
					  $.each(arr,function(n,value){
						  htmlstr+=' <span>'+timeStamp2String(value.FlowDate)+'&nbsp;&nbsp;'+value.FlowItem+'</span><br>';
					  });
					  $("div.process").html(htmlstr);
				   }
				   /* 注册信息 */
				   $("span.AnnouncementIssue").text(d.AnnouncementIssue);
				   $("span.AnnouncementDate").text(d.AnnouncementDate);
				   $("span.RegIssue").text(d.RegIssue);
				   $("span.RegDate").text(d.RegDate);
				  
				   /*实用行业信息*/
				   /* 二级 */
				   var typeArr = data.typeChild;
				   if(typeArr==null|| typeArr.length==0){
					   layer.msg("没有找到第二级信息！"); return;
				   }
				   var htmlTypeChild = "";
				   $.each(typeArr,function(n,value){
					   htmlTypeChild+='<li class="list-group-item" data-id="'+value.number+'" onclick="choiceSecond(this)">'+'【'+value.number+'】'+'-'+value.remarks+'</li>';
					  });
				   $("#second").html(htmlTypeChild);
				   /* 3级 */
				   var typeThree = data.typeThree;
				   if(typeThree==null|| typeThree.length==0){
					   layer.msg("没有找到第三级信息！");  return;
				   }
				   var htmlTypeThree = "";
				   if(data.defaultLevel3){
					   $.each(typeThree,function(n,value){
						   htmlTypeThree+=' <li data-id='+value.number+' data-pid='+value.cNumber+' class="list-group-item" onclick="choiceThird(this)">'+'【'+value.number+'】-'+value.name+'</li>';
					    });
					   //$("span.tip").text("在本地没有相对应的三级数据，可能是如下信息！")
					   layer.msg("在本地没有相对应的三级数据，可能是如下信息！");
					   
				   }else{
					   $.each(typeThree,function(n,value){
						   htmlTypeThree+='<li class="list-group-item" data-id="'+value.number+'" data-pid="'+value.cNumber+'" onclick="choiceThird(this)">'+'【'+value.number+'】-'+value.name+'</li>';
					   });
					   //$("span.tip").text("")
				   }
				  $("#dataThree").html(htmlTypeThree);
				  var firstLi=$("#second").find("li.list-group-item:first");
				  choiceSecond(firstLi);
				 // allThird();
			 }
		 }
 	});
}

function timeStamp2String(time){  
    var datetime = new Date();  
    datetime.setTime(time);  
    var year = datetime.getFullYear();  
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
/* 	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();   */
    return year + "-" + month + "-" + date;  
}

  /*
	点击2级
  */
  function choiceSecond(obj){
	  	$("li.list-group-item").removeClass("liactive");
	  	$(obj).addClass("liactive");
	    $("div.thirdT").html("");
		var pid=$(obj).attr('data-id');
		var ptext=$(obj).text();
		
		var index=ptext.indexOf('-');
		ptext=(index>0)?(ptext.substring(index+1)):(ptext)
				
		var li=$("#dataThree").find("li[data-pid='"+pid+"']");
		$.each(li,function(i,obj){
			var id=$(obj).attr('data-id');
			var text=$(obj).text();
			var nli='<li class="list-group-item" data-id="'+id+'" data-pid="'+pid+'" onclick="choiceThird(this)">'+text+'</li>'
			$("div.thirdT").append(nli);
		});
		if($("input[data-s='"+pid+"']").length<=0){
			var input='<input data-s="'+pid+'" type="hidden" name="gddSecondCode" value="'+pid+'">'+
			'<input data-s="'+pid+'" type="hidden" name="gddSecondName" value="'+ptext+'">';
			$(obj).after(input);
		}
		
 }
  /* 点击3级 */
  function choiceThird(obj){
	    $("li.list-group-item").removeClass("liactive");
	    $(obj).addClass("liactive");
		var id=$(obj).attr('data-id');
		var pid=$(obj).attr('data-pid');
		if($("div.selectedthird").find("li[data-id='"+id+"']").length<=0){
			var li=$("div.thirdT").find("li[data-id='"+id+"']");
			var text=$(obj).text();
			var index=text.indexOf('-');
			var text2=(index>0)?(text.substring(index+1)):(text)
			text=(text.length>13)?(text.substring(0,13)+"..."):(text);
			var nli='<li class="list-group-item" data-id="'+id+'" data-pid="'+pid+'" >'+text+'<span class="glyphicon glyphicon-remove" style="float:right;" aria-hidden="true" onclick="dleteItem(\''+id+'\')"></span></li> <input data-t="'+id+'" type="hidden" name="gddAppIndustry" value="'+id+'"><input data-t="'+id+'" type="hidden" name="gddAppIndustryLable" value="'+text2+'">'
			$("div.selectedthird").prepend(nli);
			/* $.each(li,function(i,obj){
				var id=$(obj).attr('data-id');
			}); */
		}
		
		if($("input[data-s='"+pid+"']").length<=0){
			var sobj=$("li[data-id='"+pid+"']");
			var index=sobj.text().indexOf('-');
			var stext=(index>0)?(sobj.text().substring(index+1)):(sobj.text())
			var input='<input data-s="'+pid+'" type="hidden" name="gddSecondCode" value="'+pid+'">'+
			'<input data-s="'+pid+'" type="hidden" name="gddSecondName" value="'+stext+'">';
			$(sobj).after(input);
		}
	}
  
 /* 取消 所有3级 */
 function dAllThird(id){
	 $("div.selectedthird").html("");
	 $("input[data-s]").remove();
 }
 /* 删除3级 */
 function dleteItem(id){
	 $("li.list-group-item").removeClass("liactive");
	 var obj=$("div.selectedthird").find("li[data-id='"+id+"']");
	 $(obj).addClass("liactive");
	 var pid=obj.attr("data-pid");
	 $(obj).remove();
	 $("div.selectedthird").find("input[data-t='"+id+"']").remove();
	 if($("div.selectedthird").find("li[data-pid='"+pid+"']").length<=0){
		 $("input[data-s='"+pid+"']").remove();
	 }
	 
 }
 /*选中所有3级 */
 function allThird(){
	 var li=$("div.thirdT").find("li");
	 var pid='';
	 $.each(li,function(i,obj){
	 	 var id=$(obj).attr('data-id');
		 if($("div.selectedthird").find("li[data-id='"+id+"']").length<=0){
			pid=$(obj).attr('data-pid');
			var text=$(obj).text();
			var index=text.indexOf('-');
			var text2=(index>0)?(text.substring(index+1)):(text)
			text=(text.length>13)?(text.substring(0,13)+"..."):(text);
			var nli='<li class="list-group-item" data-id="'+id+'" data-pid="'+pid+'" >'+text+'<span class="glyphicon glyphicon-remove" style="float:right;" aria-hidden="true" onclick="dleteItem(\''+id+'\')"></span></li><input data-t="'+id+'" type="hidden" name="gddAppIndustry" value="'+id+'"><input data-t="'+id+'" type="hidden" name="gddAppIndustryLable" value="'+text2+'">'
			$("div.selectedthird").prepend(nli);		
		 }
	 });
	 if($("input[data-s='"+pid+"']").length<=0){
		var sobj=$("li[data-id='"+pid+"']");
		var index=sobj.text().indexOf('-');
		var stext=(index>0)?(sobj.text().substring(index+1)):(sobj.text())
		var input='<input data-s="'+pid+'" type="hidden" name="gddSecondCode" value="'+pid+'">'+
		'<input data-s="'+pid+'" type="hidden" name="gddSecondName" value="'+stext+'">';
		$(sobj).after(input);
	}
 }
	 
 //提交是检测 start
  function  checkData(){
	  debugger;
	  if($.trim($("input[id='gddRegistNum']").val())==""){
		  layTip("注册号必填！","input[id='gddRegistNum']");
		  return false;
	  }
	 if($("input[name='gdName']").val().length==0 || $("input[name='gdName']").val().length>32){
		 layTip("商品名称必填,不能大于32个字符！","input[name='gdName']");
		 return false;
	 }
	 if($("input[name='gdImageUrl']").val()==null || $("input[name='gdImageUrl']").val()==""){
		 layTip("封面图必须上传！","input[name='gdImageUrl']");
		 return false;
	 }
	  
	 if(!checkCBJ()){ 
		 return false;
	 }
	 /*
	 if($("input[name='gdStartPrice']").val()==""){
		 layTip("销售价格必填！","input[name='gdStartPrice']");
		 return false;
	 }
	 if($("input[name='gdEndPrice']").val()==""){
		 layTip("销售价格必填！","input[name='gdEndPrice']");
		 return false;
	 }*/
	 
/*	 var gdStartPrice =$("input[name='gdStartPrice']").val();
	 gdStartPrice=parseFloat(gdStartPrice).toFixed(2);
	 if(gdStartPrice.length>0){
		if(gdStartPrice.length>13){
			layTip("销售价格不能超过13位！","input[name='gdStartPrice']");
			return false;  
		 }
		if(!price.test(gdStartPrice)){
			layTip("销售价格请输入数字！","input[name='gdStartPrice']");
			return false;
		}
	 }
	 var gdEndPrice =$("input[name='gdEndPrice']").val();
	 gdEndPrice=parseFloat(gdEndPrice).toFixed(2);
	 if(gdEndPrice.length>0){
		if(gdEndPrice.length>13){
			layTip("销售价格不能超过13位！","input[name='gdEndPrice']");
			return false;  
		 }
		if(!price.test(gdEndPrice)){
			layTip("销售价格请输入数字！","input[name='gdEndPrice']");
			return false;
		}
	 }
	 if(eval(gdStartPrice)>eval(gdEndPrice)){
		 layTip("销售价格区间有误！","input[name='gdStartPrice']");
		 return false; 
	 }*/
	 
	 if($("input[name='gddSellerName']").val()=="" || $("input[name='gddSellerName']").val().length>16){
		 layTip("卖家姓名必填！且不能超过16个字符！","input[name='gddSellerName']");
		 return false;
	 }
	 if($("input[name='gddSellerTel']").val()==""){
		 layTip("联系电话必填！","input[name='gddSellerTel']");
		 return false;
	 }
	 var phone=/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
	 if(!phone.test($("input[name='gddSellerTel']").val())){
			layTip("电话号码格式有误！","input[name='gddSellerTel']");
			return false;
	 }
	
	 if($("input[name='gddSellerPrice']").val()==""){
		 layTip("成本价格必填！","input[name='gddSellerPrice']");
		 return false;
	 }
	 var price=/^\d+(?=\.{0,1}\d+$|$)/;
	 var gddSellerPrice =$("input[name='gddSellerPrice']").val();
	 gddSellerPrice=parseFloat(gddSellerPrice).toFixed(2);
	 if(gddSellerPrice.length>0){
		if(gddSellerPrice.length>13){
			layTip("成本价格不能超过13位！","input[name='gddSellerPrice']");
			return false;  
		 }
		if(!price.test(gddSellerPrice)){
			layTip("成本价格请输入数字！","input[name='gddSellerPrice']");
			return false;
		}
	 }
	 if(classz!='my'){
		 var gdStartPrice =$("input[name='gdStartPrice']").val();
		 gdStartPrice=parseFloat(gdStartPrice).toFixed(2);
		 
		 var gdEndPrice =$("input[name='gdEndPrice']").val();
		 gdEndPrice=parseFloat(gdEndPrice).toFixed(2);
		 if(eval(gddSellerPrice)!=0 &&( eval(gddSellerPrice)>eval(gdStartPrice))){
			 layTip("成本价格应小于销售价格！","input[name='gddSellerPrice']");
			 return false; 
		 }
		 if(eval(gddSellerPrice)<0){
			 layTip("成本价格必须大于0！","input[name='gddSellerPrice']");
			 return false; 
		 }
	 }
	 
	 if($("select[name='gdLabel']").val()==""){
		 layTip("标签必选！","select[name='gdLabel']");
		 return false;
	 }
	 if($("select[name='gddBrandType']").val()==""){
		 layTip("商标组合必选！","select[name='gddBrandType']");
		 return false;
	 }
	/* if($("input[name='gddAppIndustry']").length==0){
		 layTip("适用行业必选！","input[name='gddAppIndustry']");
		 return false;
	 }*/

	 if($("input[name='gdId']").val().length<=0){
		 if($.trim($("input[name='gddBrandCategory']").val())==""){
			 layTip("根据注册号，没有找到相关信息！请仔细核对注册号！","input[name='gddBrandCategory']"); return false;
		 }
		 if($("span.btn.category").length==$("span.btn.disabled").length){
			 layer.msg("该商标的所有大类，都已添加！不用重复添加！");  return false;
		 }
	 }
	 return true;
  }
  
	$(".btn.btn-default.price").click(function(){
		var html='';
		if($(this).hasClass("yk")){
			html='<input type="number" class="input gdStartPrice"  value="" >';
			classz='yk';
		}else if($(this).hasClass("ck")){
			html='<input type="number" class="input gdStartPrice" style="width:49%;" value="">~'+ 
		 	 '<input type="number" class="input gdEndPrice" style="width:49%;" value="">';
		 	classz='ck';
		}else if($(this).hasClass("my")){
			$("input[name='gdStartPrice']").val("");
			$("input[name='gdEndPrice']").val("");
			classz='my';
		}
		$(".btn.btn-default.price").removeClass("chs");
		$(this).addClass("chs");
		$("div.price").html(html);
		
		$(".input.gdStartPrice").change(function(){
			if(classz=='yk'){
				$("input[name='gdStartPrice']").val($(".input.gdStartPrice").val());
				$("input[name='gdEndPrice']").val($(".input.gdStartPrice").val());
			}else{
				$("input[name='gdStartPrice']").val($(".input.gdStartPrice").val());
			}
		});
		$(".input.gdEndPrice").change(function(){
			$("input[name='gdEndPrice']").val($(".input.gdEndPrice").val());
		});
		
	});
	function checkCBJ(){
		//debugger;
		var result=false;//默认是点了
		$(".btn.btn-default.price").each(function(i,obj){
			if($(this).hasClass("chs")){
				result=true;//没有点
				return;
			}
		});
		
		if(result){//点了  判断填写价格没有
			if(classz=='yk'){
				 var price=/^\d+(?=\.{0,1}\d+$|$)/;
				 var gdStartPrice =$(".input.gdStartPrice").val();
				 if(gdStartPrice.length<=0){
					 layTip("请填写销售价格！",".input.gdStartPrice");
					 return false;
				 }
				 gdStartPrice=parseFloat(gdStartPrice).toFixed(2);
				 if(gdStartPrice.length>0){
					if(gdStartPrice.length>13){
						layTip("销售价格不能超过13位！",".input.gdStartPrice");
						return false;  
					 }
					 if(eval(gdStartPrice)<0){
						 layTip("销售价格必须大于0！",".input.gdStartPrice");
						 return false; 
					 }
					if(!price.test(gdStartPrice)){
						layTip("销售价格请输入数字！",".input.gdStartPrice");
						return false;
					}
				 }
			}else if(classz=='ck'){
				var price=/^\d+(?=\.{0,1}\d+$|$)/;
				 var gdStartPrice =$(".input.gdStartPrice").val();
				 if(gdStartPrice.length<=0){
					 layTip("请填写销售价格！",".input.gdStartPrice");
					 return false;
				 }
				 gdStartPrice=parseFloat(gdStartPrice).toFixed(2);
				 if(gdStartPrice.length>0){
					if(gdStartPrice.length>13){
						layTip("销售价格不能超过13位！",".input.gdStartPrice");
						return false;  
					 }
					if(!price.test(gdStartPrice)){
						layTip("销售价格请输入数字！",".input.gdStartPrice");
						return false;
					}
				 }
				 
				 var gdEndPrice =$(".input.gdEndPrice").val();
				 if(gdEndPrice.length<=0){
					 layTip("请填写销售价格！",".input.gdEndPrice");
					 return false;
				 }
				 gdEndPrice=parseFloat(gdEndPrice).toFixed(2);
				 if(gdEndPrice.length>0){
					if(gdEndPrice.length>13){
						layTip("销售价格不能超过13位！",".input.gdEndPrice']");
						return false;  
					 }
					if(!price.test(gdEndPrice)){
						layTip("销售价格请输入数字！",".input.gdEndPrice']");
						return false;
					}
				 }
				 if(eval(gdStartPrice)<0 || eval(gdEndPrice)<0 ){
					 layTip("销售价格必须大于0！",".input.gdStartPrice");
					 return false; 
				 }
				 if(eval(gdStartPrice)>eval(gdEndPrice)){
					 layTip("销售价格区间有误！",".input.gdStartPrice");
					 return false; 
				 }
			} 
		}else{//没有点  提示点
			layTip("请选择一项填写销售价格！",".btn.btn-default.price.yk.fi");
			return false; 
		}
		return true;
	}
  
  function layTip(msg,selector){
	  layer.msg(msg);
	  $(selector).focus();
  }
//提交是检测价格  end


  /* 点击提交按钮 */
  $("button.submit").click(function() {
	 if(!checkData()){  return;}
	 
	 //20161107黄亮加++++++++顾问必填验证
	 var number = $("input[name='gddAdviserNum']").val();
	 if(number == '' || number == null ){
		 layer.msg("请选择顾问");
		 return ;
	 }
	 
	// debugger;
	 var data=$("#goodsForm").serialize();
	 var url=rootpath+"/goods/saveOrUpdate.shtml";
	 $.post(url,data,function(data){
			if (data.status) {
				layer.msg("成功保存"+data.info+"条数据！", {icon: 6});
				setTimeout(function(){
					location.href =rootpath+"/goods/goodsList.shtml";
				},800);
			}else{
				layer.msg(data.info, {icon: 5});
			}
	 });
  });
//图片上传
var url = rootpath+'/fileUpload/uploadImg.shtml';
var deleteUrl = rootpath+'/fileUpload/deleteByName.shtml';
initUploadImg('gdImageUrl',deleteUrl,url,'gdImageUrl','请选择图片',false,20*1024*1024,1,1024*1024*1024,1*1024*1024);