package com.sjg.sys.ext.websocket;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

/**
 * Websocket 消息处理器模板类
 * */
public abstract class AbstractWebSocketHander implements WebSocketHandler {
	
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
	 * 获取当前所有登陆用户
	 * @return 当前所有Websocket链接用户数据及用户信息
	 * */
	public abstract Map<String, Object> getWesocketOnlineUsers();
	
	/**
	 * 发送广播
	 * @param message
	 * @throws IOException
	 */
	public abstract void broadcast(final TextMessage message) throws IOException;
	
	/**
	 * 发送信息给指定用户
	 * @param message：待发送的信息
	 * @param user：当前登陆用户
	 */
	public abstract void sendMessageToAssignUser(TextMessage message, String user) throws IOException;
	
	/**
	 * 发送信息给其他所有用户：仅消息管理器内部使用
	 * @param message：待发送的信息
	 * @param session：当前要发送信息的链接
	 */
	public abstract void sendMessageToOtherUsers(final TextMessage message, final WebSocketSession session) throws IOException;
	
	/**
	 * 发送信息给其他所有用户
	 * @param message：待发送的信息
	 * @param user：当前要发送信息的用户
	 */
	public abstract void sendMessageToOtherUsers(TextMessage message, String user) throws IOException;
	
	
}