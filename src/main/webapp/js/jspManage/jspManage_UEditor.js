$(function(){
	debugger;
	var editorSetting = {
			toolbars:[['fullscreen']],
			enableContextMenu: false
	}
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor',editorSetting);
	ue.addListener( 'ready', function(data) {
		//编辑器家在完成后，把编辑模式改为html
		//ue.execCommand( 'source' ); 
		ue.setContent("dkldlfjdsl");
		//销毁UEditor后，以textArea代替
		//ue.destroy();
	} );
})

function getContent() {
        var arr = [];
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    
function getContentTxt() {
    var arr = [];
    arr.push("编辑器的纯文本内容为：");
    arr.push(UE.getEditor('editor').getContentTxt());
    alert(arr.join("\n"));
}