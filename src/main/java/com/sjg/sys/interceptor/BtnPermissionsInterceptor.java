package com.sjg.sys.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sjg.sys.entity.Resources;
import com.sjg.sys.service.IResourceService;
/**
 * 拦截器处理按钮权限问题
 * @author hel
 *
 */
public class BtnPermissionsInterceptor implements HandlerInterceptor {

	//日志记录
	private Logger logger = LoggerFactory.getLogger(BtnPermissionsInterceptor.class);
	
	@Resource
	private IResourceService iResourceService;
	
	//标识当前的url是否存在 false：表示不存在；true：表示存在
	private	boolean urlIsExist = false;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		urlIsExist = false;
		//获取url中的参数
		String mid = request.getParameter("mid");
		
		if(mid != null && !"".equals(mid)){
			//获取当前菜单的按钮
			List<Resources> btnsByMenu = iResourceService.findAllByPidAndType(Long.parseLong(mid),4L);
			//获取当前页的所有url
			List<Resources> urlsByMenu = iResourceService.findAllByPidAndType(Long.parseLong(mid),2L);
			
			request.setAttribute("btnjsons", JSON.toJSONString(btnsByMenu));
			//缓存当前页的url
			request.getSession().setAttribute("urljsons", urlsByMenu);
			
			request.getSession().setAttribute("menuId", mid);
			urlIsExist = true;
		}
//		else{
//			
//			String menuId = (String) request.getSession().getAttribute("menuId");
//			String url = request.getServletPath();
//			
//			@SuppressWarnings("unchecked")
//			List<Resources> urlsByMenu = (List<Resources>) request.getSession().getAttribute("urljsons");
//			
//			if((!"".equals(menuId) && menuId != null) && (!"".equals(url) && url != null)){
//			//if(!menuId.isEmpty() && !url.isEmpty()){
//				for (Resources resources : urlsByMenu) {
//					//是否为列表
//					if("query_list".equals(resources.getResKey()) && url.equals(resources.getUrl())){
//						urlIsExist = true;
//					}
//					//判断url是否存在
//					if(url.equals(resources.getUrl()) && resources.getPid() == Long.parseLong(menuId)){
//						urlIsExist = true;
//					}
//				}
//				if(!urlIsExist){
//					response.setContentType("text/html;charset=UTF-8"); 
//					response.getWriter().write("false");
//					return false;
//				}
//			}
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		if(!urlIsExist){
//			modelAndView.setViewName("/user/toPromptPage");
//		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
