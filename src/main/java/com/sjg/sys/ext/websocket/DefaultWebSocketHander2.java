package com.sjg.sys.ext.websocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 必须登录发起链接
 * 记录每个session的拥有者，即发起WebSocket链接的用户
 * 但没登录的话userid为guest就会覆盖其他未登陆用户的链接
 * */
public class DefaultWebSocketHander2 extends AbstractWebSocketHander {
	//实现单例
	private DefaultWebSocketHander2(){};
	private static DefaultWebSocketHander2 defaultWebSocketHander = new DefaultWebSocketHander2();
	public static DefaultWebSocketHander2 getInstance(){
		return defaultWebSocketHander;
	}
	
	private Logger logger = LoggerFactory.getLogger(DefaultWebSocketHander2.class);

	public final Map<String, WebSocketSession> clients = new HashMap<String, WebSocketSession>();
	
	public Map<String, WebSocketSession> getClients() {
		return clients;
	}

	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String user = (String) session.getAttributes().get("user");
		logger.info("用户【"+user+"】成功建立Websocket链接！");
		if (clients.get(user) == null) {
			clients.put(user, session);
		}
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength()==0) return;
		session.sendMessage(new TextMessage("服务器反馈：收到信息【"+message.getPayload()+"】"));
//		sendMessageToOtherUsers(new TextMessage("【"+message.getPayload()+"】"), session);
//		broadcast(new TextMessage("服务器反馈：收到信息【"+message.getPayload()+"】"));//在收到信息的同时广播存在一个问题，会给发送信息的客户端返回同样的信息
	}

	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = clients.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				clients.remove(entry.getKey());
				logger.info("发生错误，用户【" + entry.getKey()+"】Socket会话已关闭");
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<String, WebSocketSession>> it = clients.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				clients.remove(entry.getKey());
				logger.info("用户【" + entry.getKey()+"】Socket会话已关闭");
				break;
			}
		}
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = clients.entrySet().iterator();
		// 多线程群发
		while (it.hasNext()) {
			final Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		}
	}

	/**
	 * 给某个用户发送消息
	 */
	public void sendMessageToAssignUser(TextMessage message, String user) throws IOException {
		WebSocketSession session = clients.get(user);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}

	@Override
	public void sendMessageToOtherUsers(final TextMessage message, final WebSocketSession session) throws IOException {
		
		Iterator<Entry<String, WebSocketSession>> it = clients.entrySet().iterator();
		// 多线程群发
		while (it.hasNext()) {
			final Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().isOpen()) {
				new Thread(new Runnable() {
					public void run() {
						try {
							if(!session.getId().equals(entry.getValue().getId())){
								if (entry.getValue().isOpen()) {
									entry.getValue().sendMessage(message);
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		}
	}

	@Override
	public void sendMessageToOtherUsers(final TextMessage message, final String user) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = clients.entrySet().iterator();
		// 多线程群发
		while (it.hasNext()) {
			final Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().isOpen()) {
				new Thread(new Runnable() {
					public void run() {
						try {
							if(!entry.getKey().equals(user)){
								if (entry.getValue().isOpen()) {
									entry.getValue().sendMessage(message);
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		}
	}

	@Override
	public Map<String, Object> getWesocketOnlineUsers() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String,Object>> users = new ArrayList<HashMap<String,Object>>();
		Iterator<String> it = clients.keySet().iterator();
		String key = null;;
		while(it.hasNext()){
			key = it.next();
			HashMap<String, Object> user = new HashMap<String,Object>();
			user.put("user", key);
			users.add(user);
		}
		
		map.put("data", users);
		map.put("total", users.size());
		return map;
	}
	
}