package com.sjg.rts.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import com.sjg.rts.websocket.WebSocketService;
import com.sjg.sys.entity.UserRole;
import com.sjg.sys.service.IUserRoleService;
import com.sjg.sys.utils.*;
import com.sjg.sys.utils.StringUtils;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sjg.rts.entity.ChargeRecordVO;
import com.sjg.rts.service.ChargeRecordService;
import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.ILoginRecordService;
import com.sjg.sys.service.IUserService;

@Controller
@RequestMapping("/appClient")
public class AppLoginController {
	@Resource
	private IUserService iUserService;
	@Resource
	private IUserRoleService iUserRoleService;
	@Resource
	private ILoginRecordService iLoginRecordService;
	@Autowired
	private ChargeRecordService chargeRecordService;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 登录
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/apploginSystem")
	@FuncDescLog(desc="APP客户端登录系统")
	public String login(HttpServletRequest request, Model model, User user) {
		System.out.println("用户【"+user.getLoginName()+"】从APP申请登录。。。");
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
		UserRole userRole=new UserRole();
		userRole.setUserId(dbUser.getId());
		List<UserRole> userRoles=iUserRoleService.queryList(userRole);
		boolean isadmin=false;
		for (UserRole ur:userRoles) {
			if(org.apache.commons.lang.StringUtils.isNotBlank(ur.getRoleKey())){
				if(ur.getRoleKey().equals("superadmin")||ur.getRoleKey().equals("admin")){
					isadmin=true;
					break;
				}
			}
		}
		request.getSession().setAttribute(contantUtil.SESSION_USER, dbUser);
		redisUtil.set("sys_user"+dbUser.getId(),String.valueOf(dbUser.getRestscore().intValue()));
		request.getSession().setMaxInactiveInterval(30 * 60);
		loginSuccess(request, user);
		return msgCallback(true,"{\"userId\":"+dbUser.getId()+",\"isadmin\":"+isadmin+"}", 0);
	}

	private String msgCallback(Boolean status, String info, int code) {
		Map<String, Object> callback = new HashMap<String, Object>();
		callback.put("status", status);
		callback.put("info", info);
		callback.put("code", code);
		return JSONArray.toJSONString(callback);
	}
	//登录成功,记录当前的登录日志
	private void loginSuccess(HttpServletRequest request, User user) {
		try {
			// 获取登录信息并记录
			String ip = getIp(request);
			LoginRecord loginRecord = new LoginRecord();
			loginRecord.setLrLoginName(user.getLoginName());
			loginRecord.setLrLoginIp(ip+"【APP登录】");
			loginRecord.setlrLoginStatus(contantUtil.lOGIN_L);
			loginRecord.setLrLoginTime(new Date());
			// 保存登录信息
			iLoginRecordService.save(loginRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	private String getIp(HttpServletRequest request) {
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
	
	/**
	 * 注销登录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/apploginOut")
	public Map<String, Object> loginOut(HttpServletRequest request, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		request.getSession().invalidate();
		User user= (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		result.put("result", "注销登录成功！");
		return result;
	}
	
	/**
	 * 获取所有可控用户数据
	 */
	@RequestMapping("/getAllUsers")
	@ResponseBody
	@FuncDescLog(desc="获取所有可控用户")
	public String queryUser(HttpServletRequest request, Model model){
		// 获取当前登录的用户
		User user = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		List<User> p=iUserService.getAllUserList(user);
		return JSON.toJSONString(p);
	}

	/**
	 * 分页列表数据(初始化)
	 */
	@RequestMapping("/queryUserPager")
	@ResponseBody
	@FuncDescLog(desc="分页列表数据(初始化)")
	public String queryUserPagerByOrgId(HttpServletRequest request,User user,Long offSet,Long pageSize){
		/**
		 * APP能获取的用户条件： 1.已登陆;2.管理员;3.同机构下人员（剔除自己）
		 * */
		// 获取当前登录的用户，若未登陆则不允许查询用户
		User currentUser = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if(currentUser==null || currentUser.getOrgId()==null){
			return null;
		}
		//查看登陆用户是否属于特殊机构，特殊机构可操作所有用户
		if(0 != currentUser.getOrgId()){
			//是否管理员，非管理员不允许查询用户
			user.setId(currentUser.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("roleKey", "admin");
			User tmp= iUserService.queryUserByRoleId(map);
			if(tmp == null){
				return null;
			}
			user.setOrgId(currentUser.getOrgId());
		}
		
		Pager<User> p=iUserService.queryUserListByOrgIdFromAPP(offSet, pageSize,user);
		return JSON.toJSONString(p);
	}
	
	/**
	 * 用户充值：保存充值记录并更新用户剩余分数
	 * @param vo 本次充值信息
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCharge")
	public Map<String, Object> saveCharge(HttpServletRequest request, ChargeRecordVO vo) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录的用户
		User currentUser = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		if(currentUser != null){
			vo.setCreaterId(currentUser.getId());
			vo.setCreaterName(currentUser.getName());
		}
		vo.setCreateTime(DateUtils.parse(DateUtils.getNowTime().toString()));
		chargeRecordService.save(vo);
		//查询准确的剩余分数
		User user = iUserService.queryUserById(vo.getUserId());
		Session session=WebSocketService.onlineUserSessions.get(user.getId());
		if(session!=null){
			session.getBasicRemote().sendText(String.valueOf(user.getRestscore()));
		}
		map.put("restScore",user.getRestscore());
		map.put("result", "success");
		map.put("info", "充值成功！");
		return map;
	}
	
	/**
	 * 保存用户充值记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/zeroClean")
	public Map<String, Object> zeroClean(HttpServletRequest request, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		user.setRestscore(new BigDecimal("0"));
		iUserService.updateNotNUll(user);
		map.put("restScore",0);
		map.put("result", "success");
		map.put("info", "下分成功！");
		return map;
	}
	
	/**
	 * 查询指定用户的充值记录
	 * @param vo 业务查询参数
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserChargeList")
	public List<Map<String, Object>> getUserChargeList(ChargeRecordVO vo) {
		List<Map<String, Object>> list = chargeRecordService.findAllInfos(vo);
		return list;
	}
	
	/**
	 * 查询个人详细信息
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserDetailById")
	public Map<String, Object> queryUserDetailById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> datas = iUserService.queryUserDetailById(id);
		map.put("datas",datas);
		map.put("result", "success");
		map.put("info", "查询个人中心信息成功！");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/appVisit")
	@FuncDescLog(desc="APP访问系统")
	public Map<String, Object> appVisit(HttpServletRequest request, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 获取当前的IP
		String ip = getIp(request);

		// 获取当前登录的用户
		User user = (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
		
//		// 测试获取websocket地址
//		String url = WebSocketHanderManager.getWebsocketUrl(request);
//		System.out.println(url);
//		//测试获取socke地址
//		String sockurl = WebSocketHanderManager.getSockjsUrl(request);
//		System.out.println(sockurl);
//		//测试获取websocket在线人数
//		Map<String, Object> map = WebSocketHanderManager.getWesocketOnlineUsers();
//		System.out.println(map);
		
		result.put("result", "Ajax调用成功！");
		return result;
	}
	
	

}
