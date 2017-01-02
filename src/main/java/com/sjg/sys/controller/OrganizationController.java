package com.sjg.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sjg.sys.entity.Organization;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IOrganizationService;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.Pager;

@Controller
@RequestMapping("organization")
public class OrganizationController {

	@Resource
	private IOrganizationService organizationService;
	
	@Resource
	private IUserService userService;
	
	/**
	 * 机构首页
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		//加载一级机构PID=0
		/*List<Organization> oList = organizationService.queryByPid(0L);
		List<Organization> oList2 = organizationService.findAll();
		if(oList == null || oList.size()<1){
			model.addAttribute("treeNodes", "null");
		}else{
			model.addAttribute("treeNodes", JSONArray.toJSONString(oList));
		}
		
		if(oList2 == null || oList2.size()<1){
			model.addAttribute("treeNodes2", "null");
		}else{
			model.addAttribute("treeNodes2", JSONArray.toJSONString(oList2));
		}*/
		
		return "system/organizationManage";
	}
	/**
	 * 异步加载树
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="loadTree",produces = {"text/json;charset=UTF-8"})
	public String loadTree(Long pid){
		List<Organization> oList = null;
		if(pid == null){
			oList = organizationService.findAll();
			Organization org = new Organization();
			org.setId(0L);
			org.setName("大玩家集团");
			oList.add(0, org);
		}else{
			oList = organizationService.queryByPid(pid);
		}
		if(oList == null || oList.size()<1){
			return JSONArray.toJSONString(null);
		}else{
			return JSONArray.toJSONString(oList);

		}
	}
	/**
	 * 通过机构ID查询机构信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryNode")
	public String queryNodeInfo(Long id){
		Organization org = organizationService.findById(id);
		return JSONObject.toJSONString(org);
	}
	
	/**
	 * 保存或修改机构
	 * @param org
	 * @return map 操作成功后，返回修改或添加后的对象，主要作用是配合前台做页面级的节点显示，避免递归查询
	 */
	@ResponseBody
	@RequestMapping("saveOrUpdate")
	public Map<String, Object> saveOrUpdate (Organization org){
		
		Map<String, Object> map = new HashMap<>();
		
		if(org == null){
			map.put("status", false);
    		return map;
    	}
    	if(org.getId() == null){
    		//新增
    		org.setIsHide((byte) 1);
    		//附加信息
    		User u = CommonUtils.getSessionUser();
    		org.setCreaterId(u.getId());
    		org.setCreaterName(u.getName());
    		org.setCreateTime(new Date());
    		org.setUpdaterId(u.getId());
    		org.setUpdaterName(u.getName());
    		org.setUpdateTime(new Date());
    		
    		int newId = organizationService.save(org);
    		
    		if(newId >0){
    			Organization organizetion = organizationService.findById(org.getId());
    			map.put("status", true);
    			map.put("data", organizetion);
    			map.put("operateType", "add");
    			return map;
    		}
    		
    	}else{
    		//修改
    		User u = CommonUtils.getSessionUser();
    		org.setUpdaterId(u.getId());
    		org.setUpdaterName(u.getName());
    		org.setUpdateTime(new Date());
    		int count = organizationService.updateSelective(org);
    		if(count >0){
    			map.put("status", true);
    			map.put("data", org);
    			map.put("operateType", "update");
    			return map;
    		}
    	}
    	
    	map.put("status", false);
    	return map;
	}
	/**
	 * 假删机构信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public boolean delete(Long id){
		Organization org = new Organization();
		org.setId(id);
		org.setIsHide((byte)0);
		//修改
		User u = CommonUtils.getSessionUser();
		org.setUpdaterId(u.getId());
		org.setUpdaterName(u.getName());
		org.setUpdateTime(new Date());
		int count = organizationService.updateSelective(org);
		return count >0 ? true : false;
	}
	
	/**
	 * 查询机构下的部门
	 * @param orgId
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryUser")
	public String queryUserByOrg(Long orgId,Long offset){
		User u = new User();
		u.setOrgId(orgId);
		Pager<User> page = userService.queryUserListByOrgId(offset, 15L, u);
		return JSONArray.toJSONString(page);
	}
	
	/**
	 * 查询机构
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchOrg")
	public String searchOrg(Organization organization){
		List<Organization> orgList = organizationService.queryOrg(organization);
		return JSONArray.toJSONString(orgList);
	}
	
}
