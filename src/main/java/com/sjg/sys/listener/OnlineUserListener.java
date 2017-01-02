package com.sjg.sys.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.ILoginRecordService;
import com.sjg.sys.utils.contantUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * session过滤
 * @author Hel
 *
 */
public class OnlineUserListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

		HttpSession session = event.getSession();

		User u = (User) session.getAttribute(contantUtil.SESSION_USER);
		//从session中拿到iLoginRecordService对象
		//ILoginRecordService iLoginRecordService = (ILoginRecordService) session.getAttribute("iLoginRecordService");

		ILoginRecordService iLoginRecordService=(ILoginRecordService)this.getObjectFromApplication(session.getServletContext(), "loginRecordServiceImpl");

		if (u != null) {
			// 记录当前用户的退出的日志
			// 获取当前的IP（销毁时ip为空）
			String ip = "";
			if (u != null) {
				LoginRecord loginRecord = new LoginRecord();
				loginRecord.setLrLoginName(u.getLoginName());
				loginRecord.setLrLoginIp(ip);
				loginRecord.setlrLoginStatus(contantUtil.lOGIN_E);
				loginRecord.setLrLoginTime(new Date());
				contantUtil.sessionCount--;
				//清理缓存中的用户对象
				if(contantUtil.userMap.containsKey(String.valueOf(u.getId()))){
					contantUtil.userMap.remove(String.valueOf(u.getId()));
				}
				
				// 保存登录信息
				iLoginRecordService.save(loginRecord);
			}
		}
	}

	/**
	 * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。
	 * @param servletContext  ：ServletContext上下文。
	 * @param beanName  :要取得的Spring容器中Bean的名称。
	 * @return 返回Bean的实例。
	 */
	private Object getObjectFromApplication(ServletContext servletContext,String beanName){
		//通过WebApplicationContextUtils 得到Spring容器的实例。
		ApplicationContext application= WebApplicationContextUtils.getWebApplicationContext(servletContext);
		//返回Bean的实例。
		return application.getBean(beanName);
	}

}
