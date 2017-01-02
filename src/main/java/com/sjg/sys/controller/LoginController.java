package com.sjg.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.ILoginRecordService;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.Base64;
import com.sjg.sys.utils.Button;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.Md5Util;
import com.sjg.sys.utils.Pagination;
import com.sjg.sys.utils.StringUtils;
import com.sjg.sys.utils.contantUtil;

@Controller
public class LoginController {
	@Resource
	private IUserService iUserService;
	@Resource
	private ILoginRecordService iLoginRecordService;
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {

		return "system/login";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginSystem")
	@FuncDescLog(desc="登录系统")
	public String login(HttpServletRequest request, Model model, User user) {
		
		String pwd = Base64.getFromBASE64(user.getLoginPwd());
		
		if("".equals(pwd) || null == pwd ){
			return msgCallback(false, "密码错误", 2);
		}
		user.setLoginPwd(pwd);
		// 传入登录名为空时返回
		if (StringUtils.isNullOrEmpty(user.getLoginName())) {
			return msgCallback(false, "必填", 1);
		}
		// 传入密码为空时返回
		if (StringUtils.isNullOrEmpty(user.getLoginPwd())) {
			return msgCallback(false, "必填", 2);
		}

		// 通过登录名查询用户
		User dbUser = iUserService.queryUnlockedUserByLoginName(user.getLoginName());
		if (dbUser == null) {
			return msgCallback(false, "账号不存在或被禁用", 1);
		}
		// 判断输入密码与数据库保存的密码是否相同
		boolean stauts = Md5Util.validate(user.getLoginPwd(), dbUser.getLoginPwd());
		if (!stauts) {
			return msgCallback(false, "密码错误", 2);
		}
		System.out.println(request.getSession().getId());
		request.getSession().setAttribute(contantUtil.SESSION_USER, dbUser);
		request.getSession().setMaxInactiveInterval(30 * 60);
		return msgCallback(true, "密码错误", 0);
	}

	/**
	 * 验证 验证码
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkValidateCode")
	public String checkValidateCode(String code, HttpServletRequest request) {
		if (code == null || "".equals(code)) {
			return CommonUtils.msgCallback(false, "请输入验证码", null, null);
		}
		Object obj = request.getSession().getAttribute(contantUtil.RANDOMCODEKEY);
		if (obj == null) {
			return CommonUtils.msgCallback(false, "请刷新验证码", null, null);
		}

		String sessionCode = obj.toString();
		if (sessionCode.equals(code) || sessionCode == code) {
			return CommonUtils.msgCallback(true, "验证通过", null, null);
		} else {
			return CommonUtils.msgCallback(false, "验证码输入错误", null, null);
		}
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		return "redirect:login.shtml";
	}

	/**
	 * 登录成功后跳转
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	@FuncDescLog(desc="登录成功,跳转主页")
	public String loginSuccess(HttpServletRequest request, Model model) {
		// 记录当前的登录日志
		// 获取当前的IP
		String ip = getIp(request);

		// 获取当前登录的用户
		User user = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);

		LoginRecord loginRecord = new LoginRecord();
		loginRecord.setLrLoginName(user.getLoginName());
		loginRecord.setLrLoginIp(ip);
		loginRecord.setlrLoginStatus(contantUtil.lOGIN_L);
		loginRecord.setLrLoginTime(new Date());

		// 保存登录信息
		iLoginRecordService.save(loginRecord);
		//登录成功将iLoginRecordService对象放到session中，sesssion销毁时使用
//		request.getSession().setAttribute("iLoginRecordService", iLoginRecordService);
		
		return "system/index";
	}

	@RequestMapping("/pagerdemo")
	public String pagerdemo(Model model, Pagination<User> p, Long roleId) {
		Pagination<User> pagerdata = iUserService.getUserPageInfo(p.getCurIndex());
		pagerdata.setBeginandEnd(p);
		pagerdata.setHasOpt(true);
		Button btn1 = new Button("新增", "adduser");
		Button btn2 = new Button("修改", "modifyuser");
		List<Button> ls = new ArrayList<Button>();
		ls.add(btn1);
		ls.add(btn2);
		pagerdata.setButtons(ls);
		model.addAttribute("tableheads", "用户名,密码,电话,性别,操作");
		model.addAttribute("tablecolumns", "loginName,loginPwd,phone,sex");
		model.addAttribute("pager", pagerdata);
		return "pager/listPager";
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {

		User u = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if (u != null) {
			request.getSession().removeAttribute("user");
		}
		return "redirect:login.shtml";
	}

	public static String msgCallback(Boolean status, String info, int code) {
		Map<String, Object> callback = new HashMap<String, Object>();
		callback.put("status", status);
		callback.put("info", info);
		callback.put("code", code);
		return JSONArray.toJSONString(callback);
	}

	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

}
