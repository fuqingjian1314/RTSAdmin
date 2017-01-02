package com.sjg.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjg.sys.entity.User;
import com.sjg.sys.entity.vo.ResourceMenuVo;
import com.sjg.sys.service.IResourceService;
import com.sjg.sys.utils.contantUtil;

/**
 * 资源操作controller
 * @author huangliang
 *
 */
@Controller
public class ResourceController {
	
	@Resource
	private IResourceService resourceService;
	
	/**
	 * 顶部菜单点击进入，加载右侧菜单
	 * @param pid 上级ID
	 * @param pName 上级菜单名称
	 * @param request 
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadMenu")
	public String loadMenuByPid(String pid,String pName,HttpServletRequest request,Model model){
		List<ResourceMenuVo> menuList = new ArrayList<>();
		User u = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if(u == null ){
			return null;
		}
		menuList = resourceService.queryChildMenuList(u.getId().toString(),contantUtil.RESOURCE_MENU, pid);
		model.addAttribute("menuList", menuList);
		model.addAttribute("pName", pName);
		return "system/rightMenu";
	}
	/**
	 * 右侧菜单加载子项
	 * @param pid 上级菜单ID
	 * @param className 本级菜单使用的CLASS名称
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/rightLoadMenu")
	public String rightLoadMenuByPid(String pid,String className,HttpServletRequest request,Model model){
		List<ResourceMenuVo> menuList = new ArrayList<>();
		User u = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if(u == null ){
			return null;
		}
		menuList = resourceService.queryChildMenuList(u.getId().toString(),contantUtil.RESOURCE_MENU, pid);
		model.addAttribute("menuList", menuList);
		model.addAttribute("className", className);
		return "system/rightChildMenu";
	}
	
	
	
}
