package com.sjg.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.entity.Role;
import com.sjg.sys.service.IRoleService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.StringUtils;

/**
 * 后台：角色管理controller
 * @author huangliang
 *
 */
@Controller
@RequestMapping("admin/role")
public class RoleController {
	
	@Resource
	private IRoleService roleService;
	
	/**
	 * 角色首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/redirect")
	public String roleIndex(){
		return "system/roleList";
	}
	
	/**
	 * 分页查询数据(分页)
	 * @param model
	 * @param offSet
	 * @param pageSize
	 * @param role
	 * @return
	 */
	@RequestMapping("/roleQueryLimit")
	@ResponseBody
	@FuncDescLog(desc="分页查询数据(分页)")
	public String roleQueryLimit(Model model, Long offSet, Long pageSize, Role role){
		List<Role> roles=roleService.findListLimit(offSet, pageSize, role);
		return JSON.toJSONString(roles);
	}
	
	/**
	 * 分页数据查询(初始化)
	 * @param model
	 * @param offSet
	 * @param pageSize
	 * @param role
	 * @return
	 */
	@RequestMapping("/rloeQueryPager")
	@ResponseBody
	@FuncDescLog(desc="分页查询数据(初始化)")
	public String rloeQueryPager(Model model, Long offSet, Long pageSize, Role role){
		Pager<Role> page = roleService.findListPager(offSet, pageSize, role);
		return JSON.toJSONString(page);
	}
	
	/**
	 * 角色查询
	 * @param request
	 * @param role 所有查询条件必须符合ROLE对象
	 * @param model
	 * @return
	 */
//	@RequestMapping("/roleQuery")
//	public String roleQuery(HttpServletRequest request,Role role,Model model,Pagination<Role> page){
//		page = roleService.queryByPage(role,page);
//		page.setBeginandEnd(page);
//		page.setHasOpt(true);
//		Button btn1=new Button("删除","deleteRole");
//		Button btn2=new Button("修改","modifyRole");
//		List<Button> ls=new ArrayList<Button>();
//		ls.add(btn2);
//		ls.add(btn1);
//		page.setButtons(ls);
//		
//		model.addAttribute("pager", page);
//		model.addAttribute("tableheads", "ID,名称,KEY,描述,创建时间,创建人,操作");
//		model.addAttribute("tablecolumns", "id,name,roleKey,description,formatCreateDate,createrName");
//		
//		return "pager/listPager";
//	}
	/**
	 * 添加页面
	 * @param role
	 * @return
	 */
	@RequestMapping("/addRedirct")
	public String addRedirct(){
		return "system/roleAdd";
	}
	/**
	 * 保存角色（新增 和 修改）
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	@FuncDescLog(desc="保存角色（新增 和 修改）")
	public String saveRole(Role role){
		if(role == null || StringUtils.isNullOrEmpty(role.getName()) || StringUtils.isNullOrEmpty(role.getRoleKey())){
			return CommonUtils.msgCallback(false, "菜单名和KEY不能为空", null, null);
		}
		//角色 ID为空时做新增，否则做修改操作
		if(role.getId() == null || role.getId()<0){
			//验证roleKey是否可用
			if( !roleService.isEnableKey(role.getRoleKey())){
				return CommonUtils.msgCallback(false, "key已存在", null, null);
			}
			roleService.save(role);
			return CommonUtils.msgCallback(true,"添加成功", null, null);
		}else{
			//验证roleKey是否存在，如果存在且不是正在修改的记录时，返回KEY已存在
			 Role oldRole = roleService.findByRoleKey(role.getRoleKey());
			 if(oldRole!=null && oldRole.getId().longValue()!=role.getId().longValue()){
				 return CommonUtils.msgCallback(false, "key已存在", null, null);
			 }
			int num = roleService.updateById(role);
			if(num == -1 ){
				return CommonUtils.msgCallback(true,"登录失效，请登录", null, null);
			}else if (num == 0){
				return CommonUtils.msgCallback(true,"修改失败", null, null);
			}
			return CommonUtils.msgCallback(true,"修改成功", null, null);
		}
		
	}
	@ResponseBody
	@RequestMapping("/checkRoleKey")
	@FuncDescLog(desc="验证角色的KEY是否可用")
	public String checkRoleKey(String roleKey,Long id){
		if(roleKey==null || "".equals( roleKey)){
			return CommonUtils.msgCallback(false,"请输入key", null, null);
		}
		
		boolean isExist=false;
		if(id == null || "".equals(id)){
			isExist = roleService.isEnableKey(roleKey);
		}else{
			//验证roleKey是否存在，如果存在且不是正在修改的记录时，返回KEY已存在
			 Role oldRole = roleService.findByRoleKey(roleKey);
			 if(oldRole!=null && oldRole.getId().longValue()!=id.longValue()){
				 isExist = false;
			 }else{
				 isExist = true;
			 }
		}
		
		return CommonUtils.msgCallback(isExist,"成功返回", null, null);
	}
	
	/**
	 * 删除角色（是否还存在关联记录，目前并未处理）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@FuncDescLog(desc="删除角色")
	public String deleteById(Long id){
		
		
		if(id == null || id <1L ){
			return CommonUtils.msgCallback(false, "请选择角色", null, null);
		}
		roleService.delete(id);
		
		return CommonUtils.msgCallback(true, "删除成功", null, null);
	}
	/**
	 * 修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateRedirect")
	public String updateRedirect(Long id,Model model){
		Role role = roleService.getById(id);
		model.addAttribute("role",role);
		return "system/roleAdd";
	}
}
