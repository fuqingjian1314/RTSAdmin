/**
 * 演示程序当前的 “注册/登录” 等操作，是基于 “本地存储” 完成的
 * 当您要参考这个演示程序进行相关 app 的开发时，
 * 请注意将相关方法调整成 “基于服务端Service” 的实现。
 **/
(function($, owner) {
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.account = loginInfo.account || '';
		loginInfo.password = loginInfo.password || '';
		if (loginInfo.account.length < 2) {
			return callback('账号最短为2 个字符');
		}
		if (loginInfo.password.length < 6) {
			return callback('密码最短为 6 个字符');
		}
        // localStorage.setItem("serverPath","http://localhost:8080/RTSAdmin");
        // localStorage.setItem("websocketPath","ws://localhost:8080/RTSAdmin");
        localStorage.setItem("serverPath","http://120.77.39.202:8080/RTSAdmin");
        localStorage.setItem("websocketPath","ws://120.77.39.202:8080/RTSAdmin");
        // localStorage.setItem("serverPath","http://172.16.1.179:8080/RTSAdmin");
        // localStorage.setItem("websocketPath","ws://172.16.1.179:8080/RTSAdmin");
		localStorage.setItem("account", loginInfo.account);//本地记录登录账号
		var url=localStorage.getItem("serverPath")+'/appClient/apploginSystem.shtml';
		mui.post(url,{
						loginName:loginInfo.account,
						loginPwd:loginInfo.password
					},function(data){

						if(data.status=='true' || data.status==true){
                            var info=data.info;
                            //登录成功后根据是否设置了自动登录进行本地记录操作
                            alert(info.userId);
                            localStorage.setItem("userid", info.userId);//记录登陆用户ID到本地
                            localStorage.setItem("isadmin", info.isadmin);//记录登陆用户ID到本地
							var settingsText = localStorage.getItem('$settings') || "{}";
							var settings = JSON.parse(settingsText);
							if(settings.autoLogin){
								localStorage.setItem("password", loginInfo.password);
							} /*else{
								localStorage.setItem("account", loginInfo.account)
								localStorage.setItem("password", '')
							}*/
														
							return owner.createState(loginInfo.account, callback);
						} else{
							return callback(data.info);
						}
					},'json');
		
	};

	owner.createState = function(name, callback) {
		var state = owner.getState();
		state.account = name;
		state.token = "token123456789";
		owner.setState(state);
		return callback();
	};


	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};

	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		localStorage.setItem('$state', JSON.stringify(state));
		//var settings = owner.getSettings();
		//settings.gestures = '';
		//owner.setSettings(settings);
	};

	var checkEmail = function(email) {
		email = email || '';
		return (email.length > 3 && email.indexOf('@') > -1);
	};

	/**
	 * 找回密码
	 **/
	owner.forgetPassword = function(email, callback) {
		callback = callback || $.noop;
		if (!checkEmail(email)) {
			return callback('邮箱地址不合法');
		}
		return callback(null, '新的随机密码已经发送到您的邮箱，请查收邮件。');
	};

	/**
	 * 获取应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
	}

	/**
	 * 设置应用本地配置
	 **/
	owner.getSettings = function() {
			var settingsText = localStorage.getItem('$settings') || "{}";
			return JSON.parse(settingsText);
		}
}(mui, window.app = {}));