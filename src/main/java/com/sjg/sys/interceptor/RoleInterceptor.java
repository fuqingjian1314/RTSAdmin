package com.sjg.sys.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.Resources;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IResourceService;
import com.sjg.sys.utils.contantUtil;

/**
 * 角色过滤器
 * 1.当用户登录成功进入后
 * 2.根据session里的用户信息查询该用户对应的角色信息，及对页的权限
 * 		包括：菜单权限、页面权限、按钮权限等
 * @author huangliang
 *
 */
public class RoleInterceptor implements HandlerInterceptor {

	@Resource
	private IResourceService resourceService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//获取用户信息
		User user = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if(user == null){
			return false;
		}
		
		//不能使用判断session里是否有菜单
		//场景：当用户未注销，刷新页面时，如果菜单权限有所改动，则无法加载实时数据
		/*List<Resources> menu = (List<Resources>) request.getSession().getAttribute("menu");
		if(menu != null && menu.size() > 0){
			return true;
		}*/
		
		List<Resources> resList = resourceService.queryResources(user.getId().toString(), contantUtil.RESOURCE_MENU,contantUtil.RESOURCE_ROOTNODE_PID);
		List<Resources> resList1 = resourceService.queryAllResources(user.getId(), Long.parseLong(contantUtil.RESOURCE_MENU));
		request.getSession().setAttribute("menu", resList);
		request.setAttribute("resjsons", JSON.toJSONString(resList1));
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
