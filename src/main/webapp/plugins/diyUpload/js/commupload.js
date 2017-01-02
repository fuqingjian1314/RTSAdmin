/* 
*	文件上传公用对象
*	@Author houjianghua;
*/

/**
*@param id 绑定文件上传的DIV
*@param deleteUrl 删除文件的URL地址
*@param url 上传图片的URL地址
*@param inputName 上传完成后返回的input名称
*@param buttonText 选择文件按钮的名称
*@param chunked 是否分割文件进行上传 true false
*@param chunkSize 分割文件的大小以字节为单位 512 * 1024 
*@param fileNumLimit 上传的个数限制
*@param fileSizeLimit 一次上传所有文件大小的限制以字节为单位 500000 * 1024
*@param fileSingleSizeLimit 上传时单个文件大小的限制以字节为单位 50000 * 1024
*/
function initUploadImg(id,deleteUrl,url,inputName,buttonText,chunked,chunkSize,fileNumLimit,fileSizeLimit,fileSingleSizeLimit){
	$('#'+id).diyUpload({
		'initId'          :  id, 
		'deleteUrl'       :  deleteUrl,
		'url'             :  url,
		'inputName'       :  inputName,
		'buttonText'      :  buttonText,
		'chunked'         :  chunked,
		'chunkSize'       :  chunkSize,
		'fileNumLimit'    :  fileNumLimit,
		'fileSizeLimit'   :  fileSizeLimit,
		'fileSingleSizeLimit':fileSingleSizeLimit,
		/**
		 * 是否对图片进行压缩操作,只能直接指定图片的宽或是高来进行压缩
		 * 现在系统不采用此方法，压缩图片在后台做操作 
		 */
		/*'compress'        :  {
		    width: 1800,
		    // 图片质量，只有type为`image/jpeg`的时候才有效。
		    quality: 100,
		    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
		    allowMagnify: false,
		    // 是否允许裁剪。
		    crop: false,
		    // 是否保留头部meta信息。
		    preserveHeaders: true,
		    // 如果发现压缩后文件大小比原来还大，则使用原来图片
		    // 此属性可能会影响图片自动纠正功能
		    noCompressIfLarger: false,
		    // 单位字节，如果图片大小小于此值，不会采用压缩。
		    compressSize: 0
		},*/
		'compress'       :  false,
		success:function(data) {
			console.info(data);
		},
		error:function(err) {
			alert(err);
			console.info(err);	
		}
	});
}


/**
*@param id 绑定文件上传的DIV
*@param deleteUrl 删除文件的URL地址
*@param url 上传图片的URL地址
*@param inputName 上传完成后返回的input名称
*@param buttonText 选择文件按钮的名称
*@param chunked 是否分割文件进行上传 true false
*@param chunkSize 分割文件的大小以字节为单位 512 * 1024 
*@param fileNumLimit 上传的个数限制
*@param fileSizeLimit 一次上传所有文件大小的限制以字节为单位 500000 * 1024
*@param fileSingleSizeLimit 上传时单个文件大小的限制以字节为单位 50000 * 1024
*/
function initUploadFile(id,deleteUrl,url,inputName,buttonText,chunked,chunkSize,fileNumLimit,fileSizeLimit,fileSingleSizeLimit){
	$('#'+id).diyUpload({
		'initId'          :  id, 
		'deleteUrl'       :  deleteUrl,
		'url'             :  url,
		'inputName'       :  inputName,
		'buttonText'      :  buttonText,
		'chunked'         :  chunked,
		'chunkSize'       :  chunkSize,
		'fileNumLimit'    :  fileNumLimit,
		'fileSizeLimit'   :  fileSizeLimit,
		'fileSingleSizeLimit':fileSingleSizeLimit,
		success:function(data) {
			console.info(data);
		},
		error:function(err) {
			alert(err);
			console.info(err);	
		},
		accept: {}
	});
}

function deleteFile(fileName,url,id){
	layer.msg('确认删除图片吗?',{time:0,btn:['确定', '取消'],yes: function(index){
		var $diyBar=$("#"+id).find(".diyBar");
		$diyBar.show();
		deleteProgress( 60, $diyBar , '文件删除中!' );
		layer.close(index);
		var obj = $("#"+id);
		var queryData = {};
		fileName=$.trim(fileName);
		queryData.fileName = fileName;
		$.post(url,queryData,function(data){
			if(data.status){
				obj.remove();
			}else{
				layer.msg(data.info);
			}
		},"json");
	  }
	});
}


//操作进度条;
function deleteProgress( progress, $diyBar, text ) {
	if ( progress >= 100 ) {
		progress = progress + '%';
		text = text || '文件删除中!';
	} else {
		progress = progress + '%';
		text = text || progress;
	}
	var $diyProgress = $diyBar.find('.diyProgress');
	var $diyProgressText = $diyBar.find('.diyProgressText');
	$diyProgress.width( progress );
	$diyProgressText.text( text );
}
