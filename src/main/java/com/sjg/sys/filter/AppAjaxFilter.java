package com.sjg.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解决ajax调用的限域问题
 * */
public class AppAjaxFilter implements Filter{
	private Logger logger = LoggerFactory.getLogger(AppAjaxFilter.class);
	public void destroy(){
		this.logger.info("LogbackFilter destroied!");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		try {
			HttpServletResponse re = (HttpServletResponse)response;
			re.setContentType("text/json;charset=utf-8");
			re.setHeader("Access-Control-Allow-Origin", "*");
			re.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			re.setHeader("Cache-control", "no-cache");
			
			chain.doFilter(request, response);
		} finally {
		}
	}
	
	public void init(FilterConfig arg0) throws ServletException{
		this.logger.info("AppAjaxFilter init successfully!");
	}
	
}

