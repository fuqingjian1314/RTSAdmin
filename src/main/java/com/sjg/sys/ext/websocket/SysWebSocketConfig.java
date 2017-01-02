package com.sjg.sys.ext.websocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Websocket 配置bean（通过注解配置websocket）
 * @Configuration 表示是一个配置信息提供类
 * @EnableWebSocket 注解标志开启websocket
 * 
 * */

@Configuration
@EnableWebSocket
public class SysWebSocketConfig implements WebSocketConfigurer {
	
	private String url = "/websocket.shtml";
//	@Autowired
//	private WebSocketHandler socketHandler = null;
//	@Autowired
//	private HandshakeInterceptor handshakeInterceptor = null;
//
	public String getUrl() {
		return this.url;
	}

//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public WebSocketHandler getSocketHandler() {
//		return socketHandler;
//	}
//
//	public void setSocketHandler(WebSocketHandler socketHandler) {
//		this.socketHandler = socketHandler;
//	}
//
//	public HandshakeInterceptor getHandshakeInterceptor() {
//		return handshakeInterceptor;
//	}
//
//	public void setHandshakeInterceptor(HandshakeInterceptor handshakeInterceptor) {
//		this.handshakeInterceptor = handshakeInterceptor;
//	}
//	

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	//注册WebSocket Server实现类，第二个参数是访问WebSocket的地址，同时添加拦截器
    	//setAllowedOrigins方法用来设置来自那些域名的请求可访问，默认为localhost
        registry.addHandler(WebSocketHanderManager.getSocketHandler(),url).addInterceptors(new SysHandshakeInterceptor()).setAllowedOrigins("*"); //支持websocket 的访问链接
        
        //允许客户端使用SockJS
        registry.addHandler(WebSocketHanderManager.getSocketHandler(),"/sockjs"+url).addInterceptors(new SysHandshakeInterceptor()).setAllowedOrigins("*").withSockJS(); //不支持websocket的访问链接
    }
}