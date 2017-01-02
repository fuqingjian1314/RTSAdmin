
package com.sjg.sys.ext.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.sjg.sys.entity.User;
import com.sjg.sys.utils.contantUtil;


/**
 * WebSocket建立连接（握手）和断开
 * 
 * 
 */
public class SysHandshakeInterceptor implements HandshakeInterceptor {
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		String user = "guest";
		//从session中获取登陆人员（即发起WebSocket链接人员）
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if(session != null){
				// 获取当前登录的用户
				User currentUser = (User) session.getAttribute(contantUtil.SESSION_USER);
				// 标记用户
				if(currentUser != null){
					user = currentUser.getLoginName();
					attributes.put("user", user);
				}
//				else{
//					return false;
//				}
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
