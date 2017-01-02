package com.sjg.sys.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.entity.Organization;
import com.sjg.sys.entity.Role;
import com.sjg.sys.entity.User;
import com.sjg.sys.entity.UserRole;
import com.sjg.sys.service.IOrganizationService;
import com.sjg.sys.service.IRoleService;
import com.sjg.sys.service.IUserRoleService;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.Button;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.DateUtils;
import com.sjg.sys.utils.Md5Util;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;
import com.sjg.sys.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

/**
 * 用户管理控制器
 * @author 王
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService iUserService;
	@Resource
	private IOrganizationService iOrganizationService;
	@Resource
	private IRoleService iRoleService;
	@Resource
	private IUserRoleService iUserRoleService;
	/**
	 * 用户列表页面
	 * @return
	 */
	@RequestMapping("/retrunUserList")
	public String userList(){
		return "system/user/userList2";
	}
	/**
	 * 分页列表数据
	 */
	@RequestMapping("/queryUsers")
	@ResponseBody
	@FuncDescLog(desc="分页列表数据(分页)")
	public String queryUsers(Model model,Long offSet,Long pageSize,User user){
		List<User> users=iUserService.getUserListLimit(offSet, pageSize,user);
		return JSON.toJSONString(users);
	}
	/**
	 * 保存用户角色修改
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/saveUserRoleModify")
	@ResponseBody
	@FuncDescLog(desc="保存用户角色修改")
	public String saveUserRoleModify(Long userId,String roleIds,String roleKeys){
		iUserRoleService.modifyUserRoles(userId, roleIds,roleKeys);
		return CommonUtils.msgCallback(true,"保存成功",null, null);
	}
	/**
	 * 分页列表数据(初始化)
	 */
	@RequestMapping("/queryUserPager")
	@ResponseBody
	@FuncDescLog(desc="分页列表数据(初始化)")
	public String queryUserPager(Model model,Long offSet,Long pageSize,User user){
		Pager<User> p=iUserService.getUserListPager(offSet, pageSize,user);
		return JSON.toJSONString(p);
	}
	/**
	 * 打开添加角色窗口
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("addRole")
	@FuncDescLog(desc="添加角色")
	public String addRole(Model model,String userId){
		
		if(userId == null || "".equals(userId)){
			return null;
		}
		List<Role> allroles = iRoleService.findAll();
		List<Role> myroles = iRoleService.queryRoleByUserId(userId);
		model.addAttribute("allroles", allroles); 
		model.addAttribute("myroles", myroles); 
		model.addAttribute("uId", userId);
		return "system/user/addRole";
	}
	/**
	 * 用户状态变化
	 * @return
	 */
	@RequestMapping("toggleUserStatus")
	@ResponseBody
	@FuncDescLog(desc="用户状态变化")
	public String toggleUserStatus(String userId){
		if(StringUtils.isBlank(userId)){
			return CommonUtils.msgCallback(false,"状态修改失败没有ID",null, null);
		}
		Long id=Long.parseLong(userId);
		User oldUser=iUserService.findById(id);
		byte locked=oldUser.getLocked();
		User user=new User();
		user.setId(id);
		if(locked==0){
			user.setLocked((byte) 1);
		}else if(locked==1){
			user.setLocked((byte) 0);//修改注意0会mybatis会默认为空
		}
		//修改（非空字段修改）
		int count = iUserService.updateNotNUll(user); 
		if(count==1){
			return CommonUtils.msgCallback(true,"状态修改成功",null, null);
		}else{
			return CommonUtils.msgCallback(false,"状态修改失败",null, null);
		}
	}
	/**
	 * 角色分页查询
	 * @param model
	 * @param p 分页信息
	 * @param user 用户查询条件
	 * @param roleId 按角色查询条件（目前屏蔽）
	 * @author huangliang 20160822
	 * @return
	 */
	@RequestMapping("/queryUserList")
	@FuncDescLog(desc="角色分页查询")
	public String pagerdemo(Model model,Pagination<User> p,User user,String roleId){
		Pagination<User> pagerdata=iUserService.getUserInfoPage(p.getCurIndex(), p.getPageSize(),user,roleId);
		pagerdata.setBeginandEnd(p);
		pagerdata.setHasOpt(true);
		Button btn1=new Button("删除","deleteUser");
		Button btn2=new Button("修改","editUser");
		Button btn3=new Button("指派角色","managerRole");
		List<Button> ls=new ArrayList<Button>();
		ls.add(btn1);
		ls.add(btn2);
		ls.add(btn3);
		pagerdata.setButtons(ls);
		model.addAttribute("tableheads", "序号,账号,姓名,工号,添加时间,状态,操作");
		model.addAttribute("tablecolumns", "id,loginName,name,seatNumber,createDateFormat,lockFormat");
		model.addAttribute("pager", pagerdata);
		return "pager/listPager";
	}
	
	/**
	 * 跳转用户详情页面
	 * @return
	 */
	@RequestMapping("/returnUserDetail")
	@FuncDescLog(desc="跳转用户详情页面")
	public String userDetail(Model model,Long id){
		User user = iUserService.findById(id);
		model.addAttribute("adminUser", user);
		return "system/user/userDetail";
	}
	
	/**
	 * 跳转用户密码修改
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/retrunUserUpdatePwd")
	@FuncDescLog(desc="跳转用户密码修改")
	public String userUpdatePwd(HttpServletRequest request,Model model,Long id){
		 User user = iUserService.findById(id);
		 model.addAttribute("adminUser", user);
		return "system/user/userUpdatePwd";
	}
	
	/**
	 * 跳转用户添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/retrunUserAdd")
	public String userAdd(Model model){
		 //List<Organization> organizations = iOrganizationService.findAll();
		 //model.addAttribute("organizations", organizations);
		return "system/user/userUpdate";
	}
	/**
	 * 跳转用户修改
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/retrunUserUpdate")
	@FuncDescLog(desc = "用户修改")
	public String userUpdate(HttpServletRequest request,Model model,Long id){
		 User user = iUserService.findById(id);
		 model.addAttribute("adminUser", user);
		return "system/user/userUpdate";
	}
	
	/**
	 * 新增/修改  保存操作
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@FuncDescLog(desc="新增/修改  保存操作")
	public String addUser(HttpServletRequest request,User user){
		
		if(user.getId() == null || user.getId() < 1){
			//新增
			user.setLoginPwd(Md5Util.encrypt(user.getLoginPwd()));
			user.setCreateTime(DateUtils.parse(DateUtils.getNowTime().toString()));
			user.setLocked((byte)0);
			if(user.getRestscore()==null){
				user.setRestscore(new BigDecimal("0"));
			}
			int count = iUserService.save(user);
			if(count==1){
				return CommonUtils.msgCallback(true,"添加成功",null, null);
			}else{
				return CommonUtils.msgCallback(false,"添加失败",null, null);
			}
		}else{
			if(user.getLoginPwd() != null && !"".equals(user.getLoginPwd())){
				//发现原密码与当前密码不一样才去修改密码
				User oldUser=iUserService.findById(user.getId());
				if(!oldUser.getLoginPwd().equals(user.getLoginPwd())){
					user.setLoginPwd(Md5Util.encrypt(user.getLoginPwd()));
				}
			}
			//修改（非空字段修改）
			int count = iUserService.updateNotNUll(user); 
			if(count==1){
				return CommonUtils.msgCallback(true,"修改成功",null, null);
			}else{
				return CommonUtils.msgCallback(false,"修改失败",null, null);
			}
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delectUser")
	@FuncDescLog(desc="删除用户(单个删除)")
	public String delectUser(HttpServletRequest request,Long id){
		int count = iUserService.delete(id);
		 if(count==1){
			return CommonUtils.msgCallback(true,"删除成功",null, null);
		}else{
			return CommonUtils.msgCallback(false,"删除失败",null, null);
		}
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delectUsers")
	@FuncDescLog(desc="删除用户(批量删除)")
	public String delectUsers(@RequestParam(value = "ids[]") Long[] ids){
		try {
			int count = iUserService.deleteUses(ids);
			return CommonUtils.msgCallback(true,"成功删除"+count+"个！",null, null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false,"删除失败",null, null);
		}
	}
	@RequestMapping("/userRoleList")
	public String userRoleManager(Model model,String userId){
		
		if(userId == null || "".equals(userId)){
			return null;
		}
		List<Role> roleList = iRoleService.queryRoleByUserId(userId);
		model.addAttribute("roleList",roleList);
		return "system/user/userRoleRelation";
	}
	
	/**
	 * 验证登录名是否可用
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkLoginName")
	@FuncDescLog(desc="验证登录名可用")
	public String checkLoginName(String loginName,Long id){
		User user=iUserService.queryUserByLoginName(loginName);
		
		if(id == null){
			if(user == null || user.getId()==null){
				return CommonUtils.msgCallback(true, "可用", null, null);
			}else{
				return CommonUtils.msgCallback(false, "登录名已存在", null, null);
			}
		}else{
			if(user == null || user.getId()==null){
				return CommonUtils.msgCallback(true, "可用", null, null);
			}else if(user.getId()==id){
				return CommonUtils.msgCallback(true, "可用", null, null);
			}else{
				return CommonUtils.msgCallback(false, "登录名已存在", null, null);
			}
		}
		
	}
	/**
	 * 删除用户已指派的角色
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteUserRole")
	@FuncDescLog(desc="删除用户已指派的角色")
	public String deleteUserRole(Long id){
		
		if(id == null || id<1 ){
			return CommonUtils.msgCallback(false, "ID不存在", null, null);
		}
		int count = iUserRoleService.delete(id);
		if(count == 1){
			return CommonUtils.msgCallback(true, "删除成功", null, null);
		}else{
			return CommonUtils.msgCallback(false, "删除失败", null, null);
		}
	}
	

	/**
	 * 保存选中的角色
	 * @param roleIds
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRole")
	@FuncDescLog(desc="保存选中角色")
	public String saveRole(Long[]roleIds,Long userId){
		if(userId == null ){
			return CommonUtils.msgCallback(false, "未指定用户", null, null);
		}
		if(roleIds.length<1){
			return CommonUtils.msgCallback(false,"未做任何保存", null, null);
		}
		for(Long temp:roleIds){
			UserRole record = new UserRole();
			record.setUserId(userId);
			record.setRoleId(temp);
			iUserRoleService.save(record);
		}
		return CommonUtils.msgCallback(true, "添加完成", null, null);
	}
	
	/**
	 * 验证输入原密码是否正确
	 * @param oldLoginPwd
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/verificationPwd")
	@FuncDescLog(desc="验证原密码")
	public String verificationPwd(String oldLoginPwd,Long id){
		User user = iUserService.findById(id);
		if(!oldLoginPwd.isEmpty()){
			oldLoginPwd = Md5Util.encrypt(oldLoginPwd);
			if(oldLoginPwd.equals(user.getLoginPwd())){
				return CommonUtils.msgCallback(true, "正确", null, null);
			}else{
				return CommonUtils.msgCallback(false, "密码错误", null, null);
			}
		}else{
			return CommonUtils.msgCallback(false, "原密码为空", null, null);
		}
	}
	
	/**
	 * 机构浏览(后期如果要根据某个权限浏览，请把参数封进organization对象)，目前不带条件浏览
	 * @param orgKey
	 * @author huangliang
	 * @return
	 */
	@RequestMapping("browseOrg")
	public String browseOrg(Organization organization,Model model){
		
		List<Organization> orgList = iOrganizationService.queryOrg(organization);
		Organization org = new Organization();
		org.setId(0L);
		org.setName("顶呱呱集团");
		orgList.add(0, org);
		model.addAttribute("orgList", JSONArray.toJSONString(orgList));
		
		return "system/user/orgTree";
	}
	
	/**
	 * 异步加载用户机构
	 * @param organization
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryOrgById")
	public String queryById(Organization organization,Model model){
		
		List<Organization> orgList = iOrganizationService.queryOrg(organization);
		if(orgList.size()==0){
			return JSONArray.toJSONString(null);
		}
		return JSONArray.toJSONString(orgList.get(0));
	}
	
	
	
	
}
