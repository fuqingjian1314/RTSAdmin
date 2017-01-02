$(function(){
		var divheight=$(window).height();//初始化修改div的高度
		divheight=divheight-55;
		$(".hfit").css({height:divheight});
	});
	/* 初始化ue */
  	var ue = UE.getEditor('editor');
	//保存
  	$(".btn.btn-primary").click(function(){
  		if(!checkInput()) return;
  		var  atAtpid= $("[name='atAtpId']").val();
  		var atimg = $("[name='atImgSrc']").val();
	  	/*	if(atAtpid=='consult'){ 
	  			if(atimg==null){
	  				layer.msg('发布资讯时必须上传图片！'); 
	  				return;
	  			 }
	  		}*/
  		var url=rootpath+"/article/saveOrUpdate.shtml"
  		var date=$("#articleEditForm").serializeArray();
  		$.post(url,date,function(data){
  			 if (data.status) {
					layer.alert("成功保存"+data.info+"条数据！", {icon: 6, title: '提示'});
					setTimeout(function(){
						location.href =rootpath+"/article/articleList.shtml";
					},800);
			 }else{
					layer.alert(data.info, {icon: 5, title: '提示'});
			 }
  		});
  	});
	
	function checkInput(){
		var result=true;
		if($("#atTitle").val().length==0){
  			layer.msg('请输入标题！');
  			result=false;
  		}
  		if($("#atTitle").val().length>20){
  			layer.msg('标题不要超过20个字符！');
  			result=false;
  		}
  		if(!UE.getEditor('editor').hasContents()){layer.tips('请输入内容！', "#editor", {tips: [1, 'rgba(195, 17, 17, 0.58)']});result=false;}
  		if(UE.getEditor('editor').getContentTxt().length>10000){
  			layer.msg('内容不要超过10000个字符！');
  			result=false;
  		}
  		if($("input[name='atBelong']:checked").length==0){layer.tips('请选择所属站点', "input[name='atBelong']:first", {tips: [1, 'rgba(195, 17, 17, 0.58)']});result=false;}
  		if($("#atKeyword").val().length>20){layer.tips('不要超过20个字符！', "#atKeyword", {tips: [1, 'rgba(195, 17, 17, 0.58)']}); result=false;}
  		if($("#description").val().length>50){layer.tips('不要超过50个字符！', "#description", {tips: [1, 'rgba(195, 17, 17, 0.58)']}); result=false;}
  		if($("#atSource").val().length>10){layer.tips('不要超过10个字符！', "#atSource", {tips: [1, 'rgba(195, 17, 17, 0.58)']}); result=false;}
  		if($("#atAuthor").val().length>10){layer.tips('不要超过10个字符！', "#atAuthor", {tips: [1, 'rgba(195, 17, 17, 0.58)']}); result=false;}
		return result;
	}
	
	//图片上传
	var url = rootpath+'/fileUpload/uploadImg.shtml';
	var deleteUrl = rootpath+'/fileUpload/deleteByName.shtml';
	initUploadImg('atImgSrc',deleteUrl,url,'atImgSrc','请选择图片',false,20*1024*1024,1,1024*1024*1024,1*1024*1024);
	