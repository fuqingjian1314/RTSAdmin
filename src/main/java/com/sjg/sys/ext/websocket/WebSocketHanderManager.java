package com.sjg.sys.ext.websocket;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.socket.TextMessage;

import com.sjg.sys.utils.SpringContextUtil;

/**
 * Websocket消息管理器的管理器：供平台的类，如Controller、Service等直接调用发送websocket消息
 * 若是直接调用到Websocket消息管理器，则是前端在发起websocket链接或利用websock链接发送消息
 * */
public class WebSocketHanderManager {
	/**
	 * 获取被管理对象:若要换其他消息处理器，在这里调整即可
	 * */
	public static AbstractWebSocketHander getSocketHandler() {
		return DefaultWebSocketHander2.getInstance();
	}
	
	/**
	 * 获取Websocket地址
	 * request.getServerName()：'localhost'
	 * request.getServerPort()：'8080'
	 * request.getContextPath()：'/BAP2.0'
	 * request.getScheme())：'http'
	 * request.getServletPath()：'/msg/talk.do'
	 * */
	public static String getWebsocketUrl(HttpServletRequest request){
		SysWebSocketConfig sysWebSocketConfig = SpringContextUtil.getBean("sysWebSocketConfig");
		// ws://localhost:8080/BAP2.0/websocket.do
		String url = "ws://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()
				+sysWebSocketConfig.getUrl();
		return url;
	}
	/**
	 * 获取模拟Websocket地址
	 * */
	public static String getSockjsUrl(HttpServletRequest request){
		SysWebSocketConfig sysWebSocketConfig = SpringContextUtil.getBean("sysWebSocketConfig");
		// ws://localhost:8080/BAP2.0/sockjs/websocket.do
		String url = "ws://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()
				+"/sockjs"+sysWebSocketConfig.getUrl();
		return url;
	}
	
	/**
	 * 获取当前所有登陆用户
	 * */
	public static Map<String, Object> getWesocketOnlineUsers(){
		return getSocketHandler().getWesocketOnlineUsers();
	}
	
	/**
	 * 发送广播
	 * */
	public static void broadcast(final TextMessage message) throws IOException{
		getSocketHandler().broadcast(message);
	}
	
	/**
	 * 发送信息给指定用户：在记录了websocket链接发起者的情况下才可实现（如：DefaultWebSocketHander2）
	 * 若需要在不记录发起者的情况下实现则只能改写的WebSocketHander实现类的handleMessage()方法在取得当前WebSocketSession对象下进行
	 * */
	public static void sendMessageToAssignUser(String user, final TextMessage message) throws IOException{
		getSocketHandler().sendMessageToAssignUser(message, user);
	}
	
	/**
	 * 发送信息给所有其他用户：在记录了websocket链接发起者的情况下才可实现（如：DefaultWebSocketHander2）
	 * 在不记录发起者的情况下无法实现
	 * */
	public static void sendMessageToOtherUsers(final TextMessage message, final String user) throws IOException{
		getSocketHandler().sendMessageToOtherUsers(message, user);
	}
	
	
	
}
