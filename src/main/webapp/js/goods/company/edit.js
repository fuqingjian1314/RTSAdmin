$(function(){
	var divheight=$(window).height();//初始化修改div的高度
	divheight=divheight-55;
	$(".hfit").css({height:divheight});
});
//切换地区
$(".selectpicker.change").on("change",function(){
	var type=$(this).attr("data-id");
	var value=$(this).val();
	var datas={"code":value};
	if(type=="province"){
		$("select[data-id='city']").empty();
		$("select[data-id='city']").html('<option value="">请选择</option>');
		$("select[data-id='city']").selectpicker('refresh');
		
		$("select[data-id='area']").empty();
		$("select[data-id='area']").html('<option value="">请选择</option>');
		$("select[data-id='area']").selectpicker('refresh');
	}else if(type=="city"){
		$("select[data-id='area']").empty();
		$("select[data-id='area']").html('<option value="">请选择</option>');
		$("select[data-id='area']").selectpicker('refresh');
	}			
	var url=rootpath+"/company/findAreaByCode.shtml";
	if((type!="area" && type!="") &&(value!="")){
		$.post(url,datas,function(data){
			var html='<option value="">请选择</option>';
			$.each(data,function(i,obj){
				html+='<option value="'+obj.code+'">'+obj.value+'</option>';
			});
			if(type=="province"){
				$("select[data-id='city']").empty();
				$("select[data-id='city']").html(html);
				$("select[data-id='city']").selectpicker('refresh');
				 
				$("select[data-id='area']").empty();
				$("select[data-id='area']").html('<option value="">请选择</option>');
				$("select[data-id='area']").selectpicker('refresh');
			}else if(type=="city"){
				$("select[data-id='area']").empty();
				$("select[data-id='area']").html(html);
				$("select[data-id='area']").selectpicker('refresh');
			}
		});
	}
	var provinceCode= $("select[data-id='province']").val();
	var province= $("select[data-id='province']").find("option:selected").text()
	var cityCode= $("select[data-id='city']").val();
	var city= $("select[data-id='city']").find("option:selected").text()
	var areaCode= $("select[data-id='area']").val();
	var area= $("select[data-id='area']").find("option:selected").text()
	var codeStr="";
	var str='';
	if(provinceCode!=""){
		codeStr+=","+provinceCode;
		str+=","+province;
		if(cityCode!=""){
			codeStr+=","+cityCode;
			str+=","+city;
			if(cityCode!=""){
				codeStr+=","+areaCode;
				str+=","+area;
			}
		}
	}
	$("input[name='gcRegiAreaCode']").val(codeStr.substring(1));
	$("input[name='gcRegiArea']").val(str.substring(1));
});
//添加无形资产
$(".btn.btn-primary.intanAsset").click(function(){
	var dcode=$(".selectpicker.gcIntanAsset").val();
	var dvalue=$(".selectpicker.gcIntanAsset").find("option:selected").text();
	var inNO=$(".form-input.gcIntanAssetNo").val();
	var url=rootpath+"/company/checkAsset.shtml";
	if($("li[data-id='"+inNO+"']").length>0){
		layer.tips('该注册号已经添加，请核对！', ".form-input.gcIntanAssetNo", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		return;
	}
	if(inNO==""){
		layer.tips('注册号必填，请核对！', ".form-input.gcIntanAssetNo", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		return;
	}
	if(dcode==""){
		layer.tips('必须选择一项！', ".btn-group.bootstrap-select.gcIntanAsset", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		return;
	}
	var result=false;
	$.ajax( {  
		 url :url,
		 data:{dCode:dcode,regNo:inNO},
		 type:'post',  
		 cache:false, 
		 async: false,
		 dataType:'json',  
		 success:function(data) {
			 result=data;
			 if (!data) {
				layer.tips('该注册号不存在，请核对！', ".form-input.gcIntanAssetNo", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
			 }
		 }
	});
	if(!result) return;
	var html='<li class="list-group-item" data-id="'+inNO+'" >'+dvalue+':'+inNO+
	'	<span class="glyphicon glyphicon-remove"style="float:right;" aria-hidden="true"></span>'+
	'	<input type="hidden" name="gcIntanAsset" value="'+dcode+'" > <input type="hidden" name="gcIntanAssetNo"  value="'+inNO+'" >'
	'</li>';
	$(".div.list-li").append(html);
	$(".div.list-li").css("margin-bottom",10);
	$(".glyphicon.glyphicon-remove").click(function(){
		$(this).parent().remove();
	});
});
 
function checkCompany(){
	var url=rootpath+"/company/checkCompany.shtml";
	var cRegNo=$("input[name='gcCompanyNo']").val();
	var result=false;
	if(cRegNo.length<=0){
		//layer.tips('公司注册号必须正确填写！', "input[name='gcCompanyNo']", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
		layer.msg("公司注册号必须正确填写！");
		return result;
	}
	$.ajax( {  
		 url :url,
		 data:{regNo:cRegNo},
		 type:'post',  
		 cache:false,  
		 async: false,
		 dataType:'json',  
		 success:function(data) {
			 if (data.status) {
				 var gcOrgCode="";
				 var obj=data.data;
				 $(".form-control.gcCompanyName").val(obj.name);
				 $(".form-control.gcEstablish").val(obj.startDate);
			  	 //debugger;
				 if(obj.registCapi!=null && obj.registCapi!=""){
					var gcRegCap= parseFloat(obj.registCapi);
					if(gcRegCap!=NaN ){
					  $(".form-control.gcRegCap").val(parseFloat(obj.registCapi));
					}
				 }
				 $(".form-control.gcCompyType").val(obj.econKind);
				 $(".form-control.gcScope").val(obj.scope);
				 $(".form-control.gcOperName").val(obj.operName);
				 $(".form-control.gcScopeStatus").val(obj.status);
				 $(".form-control.gcScoTeamEnd").val(obj.teamEnd);
				 if( obj.changeRecords!=null){
					 if(obj.changeRecords[0]!=null){
						 if(obj.changeRecords[0].beforeContent!=null){
							 var index= obj.changeRecords[0].beforeContent.indexOf("组织机构代码证");
							 if(index >= 0){ gcOrgCode=obj.changeRecords[0].beforeContent.substring(index+8);}
							 $(".form-control.gcOrgCode").val(gcOrgCode);
						 }
					 }
				 }
			 }else{
				 //layer.tips('没有查找到公司，请核对！', "input[name='gcCompanyNo']", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
				 layer.msg("根据该公司注册号，没有查找到公司，请核对！");
			 }
			 result=data.status;
		 }
	});
	return result;
}

//保存操作
$(".btn.btn-primary.commit").click(function(){
	$(".commit").attr("disabled","diaable");
	if(existence){ 
		layer.msg("该公司注册号已被添加！"); 
		$(".commit").removeAttr("disabled");
		return;
	}
	if(!checkCompany()){
		$(".commit").removeAttr("disabled");
		return;
	}
	if(!checkData()) {
		$(".commit").removeAttr("disabled");
		return;
	}
	var url=rootpath+"/company/saveOrUpdate.shtml";
	var datas=$(".form-horizontal").serializeArray();
		$.post(url,datas,function(data){
			 if (data.status) {
				layer.msg("成功保存"+data.info+"条数据！", {icon: 6});
				setTimeout(function(){
					location.href =rootpath+"/company/list.shtml";
				},800);
			 }else{
				$(".commit").removeAttr("disabled");
				layer.msg(data.info, {icon: 5});
			 }
		});
});

//顾问弹窗开始 
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
	queryparams.businessPid=77;
	var url =  rootpath+"/adviser/findAdviserDate.shtml";
	$.post(url,queryparams,function(data){
		$("#adviser_conten_wj").html(data);
	},'html');
}
//顾问初始化方法
getPager(0);
//顾问搜索方法
function searchIntro(){
	getPager(0);	
}
//顾问重置搜索条件
function resetIntro(){
	$("input[name='adviserCond']").val('');
	getPager(0);
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
//选择顾问
$(".btn.btn-primary.adviser").click(function(){
	OpenChoiceAdviser();
});
/*选择顾问  */
function choice(number,name){
	$("input.form-input.adviserName").val(name)
	$("input[name='gcAdviserNum']").val(number);
	layer.msg("选择成功！");
	closChoiceAdviser();
}
var existence=false;
$(".form-control.gcCompanyNo").change(function(){
	existence=false;
	var ivalue=$(this).val();
	if(ivalue.length<=0) return;
	var url=rootpath+"/company/checkCompany.shtml";
	var datas={"regNo":ivalue,"type":"reInspection"};
		$.post(url,datas,function(data){
			 if (!data.status){
				 layer.tips('该公司注册号已被添加！', ".form-control.gcCompanyNo", {tips: [1, 'rgba(195, 17, 17, 0.58)']});
				 existence=true;
			 }
		});
});
//顾问弹窗结束
$(function(){
	$(".list-group-item").each(function(i,obj){
		var dcode=$(obj).find("span[data-id]").attr("data-id");
		var no=$(obj).find("[name='gcIntanAssetNo']").val();
		$(obj).find("span[data-id]").text($("option[value='"+dcode+"']").text()+":"+no);
		$(".glyphicon.glyphicon-remove").click(function(){
			$(this).parent().remove();
		});
	});
});


 //提交是检测 start
  function  checkData(){
	  var price=/^\d+(?=\.{0,1}\d+$|$)/;
	 if($("input[name='gdName']").val().length==0 || $("input[name='gdName']").val().length>32){
		 layTip("公司名称必填,不能大于32个字符！","input[name='gdName']");
		 return false;
	 }
	 if($("select[name='gcSuIn']").val()==""){
		 layTip("投资主体必选！","select[name='gcSuIn']");
		 return false;
	 }
	 var gcPayCapital=$("input[name='gcPayCapital']").val();
	 if(gcPayCapital==""){
		 layTip("实收资本必填！","input[name='gcPayCapital']");
		 return false;
	 }
	 if(gcPayCapital.length>13){
			layTip("实收资本能超过13位！",".input.gdStartPrice");
			return false;  
		 }
	 if(!price.test(gcPayCapital)){
			layTip("实收资本请输入大于0的数字！","input[name='gcPayCapital']");
			return false;
	 }
	 if(eval(gcPayCapital)<=0){
		 layTip("实收资本必须大于0！","input[name='gcPayCapital']");
		 return false; 
	 }
	 if($("select[name='gcTaxType']").val()==""){
		 layTip("纳税类型必选！","select[name='gcTaxType']");
		 return false;
	 }
	 var ind=$("select[name='gcOwnedIndu']").selectpicker('val');
	 if(ind==null || ind==""){
		 layTip("所属行业必选！","select[name='gcOwnedIndu']");
		 return false;
	 }
	 if($("select[data-id='province']").val()==""){
		 layTip("注册区域 省必选！","select[data-id='province']");
		 return false;
	 }
	 if($("select[data-id='city']").val()==""){
		 layTip("注册区域 市必选！","select[data-id='city']");
		 return false;
	 }
	 if($("select[data-id='area']").val()==""){
		 layTip("注册区域 区必选！","select[data-id='area']");
		 return false;
	 }
	 if($("input[name='gcSellerName']").val()=="" || $("input[name='gcSellerName']").val().length>16){
		 layTip("卖家姓名必填！且不能超过16个字符！","input[name='gcSellerName']");
		 return false;
	 }
	 if($("input[name='gcSellerTel']").val()==""){
		 layTip("联系电话必填！","input[name='gcSellerTel']");
		 return false;
	 }
	 var phone=/^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
	 if(!phone.test($("input[name='gcSellerTel']").val())){
			layTip("电话号码格式有误！","input[name='gcSellerTel']");
			return false;
	 }
	 if($("input[name='gcSellerPrice']").val()==""){
		 layTip("成本价格必填！","input[name='gcSellerPrice']");
		 return false;
	 }
	 if($("input[name='gcAdviserNum']").val()=="" ){
		 layTip("顾问必选！","input[name='gcAdviserNum']");
		 return false;
	 }
	 if($("input[name='gdState']").val()=="" ){
		 layTip("状态选！","input[name='gdState']");
		 return false;
	 }
	 if($("input[name='gdStartPrice']").val()==""){
		 layTip("销售价格必填！","input[name='gdStartPrice']");
		 return false;
	 }
	 if($("input[name='gdEndPrice']").val()==""){
		 layTip("销售价格必填！","input[name='gdEndPrice']");
		 return false;
	 }
	 var price=/^\d+(?=\.{0,1}\d+$|$)/;
	 var gdStartPrice =$("input[name='gdStartPrice']").val();
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
	 if(eval(gdStartPrice)<0 || eval(gdEndPrice)<0 ){
		 layTip("销售价格必须大于0！","input[name='gdStartPrice']");
		 return false; 
	 }
	 if(eval(gdStartPrice)>eval(gdEndPrice)){
		 layTip("销售价格区间有误！","input[name='gdStartPrice']");
		 return false; 
	 }
	 var gddSellerPrice =$("input[name='gcSellerPrice']").val();
	 gddSellerPrice=parseFloat(gddSellerPrice).toFixed(2);
	 if(gddSellerPrice.length>0){
		if(gddSellerPrice.length>13){
			layTip("成本价格不能超过13位！","input[name='gcSellerPrice']");
			return false;  
		 }
		if(!price.test(gddSellerPrice)){
			layTip("成本价格请输入数字！","input[name='gcSellerPrice']");
			return false;
		}
	 }
	 if(eval(gddSellerPrice)<0){
		 layTip("成本价格必须大于0！","input[name='gddSellerPrice']");
		 return false; 
	 }
	 if(eval(gddSellerPrice)!=0 &&( eval(gddSellerPrice)>eval(gdStartPrice))){
		 layTip("成本价格应小于销售价格！","input[name='gddSellerPrice']");
		 return false; 
	 }
	 if($("textarea[name='gdRemark']").val()=="" || $("textarea[name='gdRemark']").val().length>512){
		 layTip("备注必填！且不能超过512个字符！","textarea[name='gdRemark']");
		 return false;
	 }
	 return true;
  }
  function layTip(msg,selector){
	  layer.msg(msg);
	  $(selector).focus();
  }
//提交是检测价格  end
 