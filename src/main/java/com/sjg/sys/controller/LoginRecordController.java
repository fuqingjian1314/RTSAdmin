package com.sjg.sys.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.ILoginRecordService;
import com.sjg.sys.utils.Pagination;
import com.sjg.sys.utils.contantUtil;

/**
 * 访问记录控制器
 * @author Hel
 *
 */
@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController {

	private Logger logger = LoggerFactory.getLogger(LoginRecordController.class);
	@Resource
	private ILoginRecordService loginRecordService;
	
	/**
	 * 页面跳转至访问日志记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/accessLog")
	public String accessLog(HttpServletRequest request,Model model){
		
		Map<String, User> map = contantUtil.userMap;
		Iterator<String> keys = map.keySet().iterator();  
        while(keys.hasNext()){  
            String key = (String)keys.next();  
            System.out.println(key);
        }  
		
		model.addAttribute("sessionCount", contantUtil.sessionCount);
		return "system/accessLog";
	}
	
	/**
	 * 分页查询访问日志
	 * @param model
	 * @param p
	 * @param user
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/queryLoginRecordList")
	public String pagerLoginRecord(Model model,Pagination<LoginRecord> p,String lrLoginName){
		Pagination<LoginRecord> pagerdata=loginRecordService.getLoginRecordPage(p.getCurIndex(), p.getPageSize(),lrLoginName);
		pagerdata.setBeginandEnd(p);
		pagerdata.setHasOpt(false);
		
		model.addAttribute("pager", pagerdata);
		model.addAttribute("tableheads", "序号,登录名,登录时间,登录IP,登录状态");
		model.addAttribute("tablecolumns", "lrId,lrLoginName,formatLoginTime,lrLoginIp,lrLoginStatus");
		return "pager/listPager";
	}
	
	/**
	 * 查询当前在线的人数
	 * @param model
	 * @param p
	 * @param lrLoginName
	 * @return
	 */
	@RequestMapping("/queryOnlineUsers")
	public String pagerOnlineUsers(Model model,Pagination<User> p,String lrLoginName){
		List<User> listUser = new ArrayList<>();
		
		Map<String, User> map = contantUtil.userMap;
		Iterator<String> keys = map.keySet().iterator();  
        while(keys.hasNext()){  
    		String key = (String)keys.next();  
            User u = map.get(key);
            listUser.add(u);
        }  
		
		Pagination<User> page=new Pagination<User>(p.getCurIndex(),p.getPageSize(),contantUtil.sessionCount);
		page.setItems(listUser);
		page.setBeginandEnd(p);
		page.setHasOpt(false);
		
		model.addAttribute("pager", page);
		model.addAttribute("tableheads", "id,登录名,姓名,性别");
		model.addAttribute("tablecolumns", "id,loginName,name,sex");
		return "pager/listPager";
	}
	
}
