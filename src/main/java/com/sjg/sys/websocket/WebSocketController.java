package com.sjg.sys.websocket;

import com.sjg.rts.websocket.WebSocketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 */
@Controller
@RequestMapping("wsocket")
public class WebSocketController {
    @RequestMapping("page")
    public String page(){
        return "app/page";
    }
    @RequestMapping("sd")
    @ResponseBody
    public void send(String username) throws IOException {
        System.out.println(username);
        Session session= WebSocketService.onlineUserSessions.get(username);
        System.out.println(session.getId());
        session.getBasicRemote().sendText("in");
        System.out.println("in");
    }
}
