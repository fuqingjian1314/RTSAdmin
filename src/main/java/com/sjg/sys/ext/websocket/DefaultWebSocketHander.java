package com.sjg.sys.ext.websocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 无需登录发起链接
 * */
public class DefaultWebSocketHander extends AbstractWebSocketHander {
	//实现单例
	private DefaultWebSocketHander(){};
	private static DefaultWebSocketHander defaultWebSocketHander = new DefaultWebSocketHander();
	public static DefaultWebSocketHander getInstance(){
		return defaultWebSocketHander;
	}
	
	private Logger logger = LoggerFactory.getLogger(DefaultWebSocketHander.class);
	
	private final List<WebSocketSession> clients = new ArrayList<>();
	
	public List<WebSocketSession> getClients() {
		return clients;
	}

	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("链接建立成功！");
		clients.add(session);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 * @parameter session：当前发送信息的Socket链接
	 * @parameter message：当前Socket链接发送的信息
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength()==0) return;
		//收到信息后给发送信息的客户端回应
		session.sendMessage(new TextMessage("服务器反馈：收到信息【"+message.getPayload()+"】"));
//		sendMessageToOtherUsers(new TextMessage("【"+message.getPayload()+"】"), session);
//		broadcast(new TextMessage("【"+message.getPayload()+"】"));//在收到信息的同时广播存在一个问题，会给发送信息的客户端返回同样的信息
		
	}

	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("链接出错，关闭链接......");
		if (session.isOpen()) {
			session.close();
		}
		clients.remove(session);
	}

	/**
	 * 关闭连接后
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("链接关闭......" + closeStatus.toString());
		clients.remove(session);
	}
	
	/**
	 * 创建新线程给所有在线用户发送消息
	 */
	public void broadcast(final TextMessage message) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				for (WebSocketSession client : clients) {
				    try {
				        if (client.isOpen()) {
				        	client.sendMessage(message);
				        } else{
				        	clients.remove(client);
				        }
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				}
			}

		}).start();
	}
	
	/**
	 * 创建新线程给所有非发送信息客户端发送消息
	 */
	public void sendMessageToOtherUsers(final TextMessage message, final WebSocketSession session) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				for (WebSocketSession client : clients) {
					try {
						if(!client.getId().equals(session.getId())){
							if (client.isOpen()) {
								client.sendMessage(message);
							} else{
								clients.remove(client);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

	@Override
	public void sendMessageToAssignUser(TextMessage message, String user) throws IOException {
		// 发送消息给指定用户，未记录发起websocket链接的用户，此操作无法实现
		
	}

	@Override
	public void sendMessageToOtherUsers(TextMessage message, String user) throws IOException {
		// 供平台的类调用发送消息给指定用户，未记录发起websocket链接的用户，此操作无法实现
		
	}

	@Override
	public Map<String, Object> getWesocketOnlineUsers() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String,Object>> users = new ArrayList<HashMap<String,Object>>();
		WebSocketSession session;
		for(int i=0;i<clients.size();i++){
			session = clients.get(i);
			HashMap<String, Object> user = new HashMap<String,Object>();
			user.put("user", session.getId());
			users.add(user);
		}
		
		map.put("data", users);
		map.put("total", users.size());
		return map;
	}

}