package com.sjg.sys.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sjg.sys.entity.User;
import com.sjg.sys.utils.contantUtil;

/**
 * 登录过滤器
 * @author huangliang
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
     * 日志记录者
     */
    private Logger logger = Logger.getLogger(LoginInterceptor.class);
    /**
     * 排除拦截处理的范围
     */
    private Set<String> excludeUris = new HashSet<String>();
    /**
     * 拦截处理后需跳转的地址
     */
    private String gotoUri;
	
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//得到项目路径
    	String projectPath = request.getContextPath();
    	//得到访问地址
    	String requestUri = request.getRequestURI();
    	//过滤通过xml注入的指定路径
    	for(String temp : excludeUris){
    		if(requestUri.matches(projectPath + temp)){
    			return true;
    		}
    	}
    	//判断session里user是否存在
    	User user = (User)request.getSession().getAttribute(contantUtil.SESSION_USER);
    	if(user == null || user.getId()==null){
    		response.sendRedirect(projectPath+gotoUri);
    		return false;
    	}
    	
		return true;
	}
    
    
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public Set<String> getExcludeUris() {
		return excludeUris;
	}


	public void setExcludeUris(Set<String> excludeUris) {
		this.excludeUris = excludeUris;
	}


	public String getGotoUri() {
		return gotoUri;
	}


	public void setGotoUri(String gotoUri) {
		this.gotoUri = gotoUri;
	}

	

}
