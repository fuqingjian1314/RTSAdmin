package com.sjg.sys.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.sjg.sys.entity.User;
import com.sjg.sys.utils.contantUtil;

/**
 * 监听session的attrbute事件
 * @author Hel
 *
 */
public class LoginSessionListener implements HttpSessionAttributeListener{

	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		
		if(name.equals(contantUtil.SESSION_USER)){
			User user = (User) event.getValue();
			if(map.get(user.getLoginName()) != null){
				HttpSession session = map.get(user.getLoginName());
				session.removeAttribute(user.getLoginName());
				session.invalidate();//销毁session
			}
			
			//将session重新放到map中
			map.put(user.getLoginName(), event.getSession());
			//查看缓存中是否存在当前对象
			if(contantUtil.userMap.containsKey(String.valueOf(user.getId()))){
				contantUtil.userMap.remove(String.valueOf(user.getId()));
			}
			//将密码置空
			user.setLoginPwd(null);
			//将登录的用户信息添加到缓存中
			contantUtil.userMap.put(String.valueOf(user.getId()), user);
			contantUtil.sessionCount++;
			//将当前在线人数放入session中
			event.getSession().setAttribute("sessionCount", contantUtil.sessionCount);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();  
		  
        if (name.equals("user")) {  
            User user = (User) event.getValue();  
            map.remove(user.getLoginName());  
        }  
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}

	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}

}
