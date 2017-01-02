package com.sjg.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sjg.sys.utils.ReadItemConfigUtil;


public class ApplicationLoader implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		  // 将配置文件里的值装入context属性,这样可以在JSP,SERVLET里调用
        ServletContext context = sce.getServletContext();
        context.setAttribute("version", ReadItemConfigUtil.getProperty("project.version"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.removeAttribute("versioin");//移除当前的属性
	}

}
