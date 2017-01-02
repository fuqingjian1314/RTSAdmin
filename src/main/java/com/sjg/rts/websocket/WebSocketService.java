package com.sjg.rts.websocket;

import com.alibaba.fastjson.JSONObject;
import com.sjg.sys.entity.Message;
import com.sjg.sys.utils.RedisUtil;
import com.sjg.sys.utils.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/rtswebsocket")
public class WebSocketService {
  public final static Map<String,Session> onlineUserSessions=new HashMap<>();
  private String userId;
 
  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, InterruptedException {
   
    System.out.println("Received: " + message);
    Message messageInfo= JSONObject.parseObject(message,Message.class);
    if(messageInfo.getType().intValue()==1){
        if(StringUtils.isBlank(messageInfo.getMessageinfo())){
          this.userId="13";
        }else{
          this.userId=messageInfo.getMessageinfo();
        }

        //当type为1的时候，messageinfo为登陆人账号
        onlineUserSessions.put(this.userId,session);
        ApplicationContext wac= SpringContextUtil.getApplicationContext();
        RedisUtil redisUtil= (RedisUtil) wac.getBean("redisUtil");
        //发送剩余分
        session.getBasicRemote().sendText(String.valueOf(redisUtil.get("sys_user"+this.userId)));
    }
  }
   
  @OnOpen
  public void onOpen(Session session) {
    System.out.println(session.getId());
    System.out.println(this);
  }
 
  @OnClose
  public void onClose() {
    System.out.println(this.userId);
    onlineUserSessions.remove(this.userId);
    System.out.println(onlineUserSessions.size());
    System.out.println("Connection closed");
  }
}
