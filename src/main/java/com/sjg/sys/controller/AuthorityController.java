package com.sjg.sys.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.Resources;
import com.sjg.sys.entity.Role;
import com.sjg.sys.entity.RoleResources;
import com.sjg.sys.service.IAuthorityService;
import com.sjg.sys.service.IResourceService;
import com.sjg.sys.service.IRoleService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.StringUtils;
import com.sjg.sys.utils.tree.TreeObjectNew;
@Controller
@RequestMapping("authority")
public class AuthorityController {
	@Resource
	private IResourceService iResourceService;
	
	@Resource
	private IRoleService iRoleService;
	
	
	@Resource
	private IAuthorityService iAuthorityService;
	
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	
	/*所以url*/
	private static List<Resources> SYS_ALL_URL=new ArrayList<Resources>();
	/**
	 * 跳转菜单页
	 * @author wenjie
	 * @param model
	 * @return String jsp路径
	 */
	@RequestMapping(value="/authorityMenulist")
	public String authorityMenuList(Model model) {
		if(SYS_ALL_URL.size()==0){
			Map<RequestMappingInfo, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
			Iterator<Entry<RequestMappingInfo, HandlerMethod>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<RequestMappingInfo, HandlerMethod> entry =  iterator.next();
				RequestMappingInfo rminfo=(RequestMappingInfo) entry.getKey();
				Object[] rminfos= rminfo.getPatternsCondition().getPatterns().toArray();
				for (Object string : rminfos) {
					Resources r=new Resources();
					r.setType(2);//1菜单，2url
					r.setUrl(String.valueOf(string)+".shtml");
					SYS_ALL_URL.add(r);
				}
			}
		}
		return "system/chooseAuthorityMenu";
	}
	/**
	 * 获取系统urls
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sysUrls")
	public String sysUrls(String mlike){
		if(SYS_ALL_URL.size()==0){
			Map<RequestMappingInfo, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
			Iterator<Entry<RequestMappingInfo, HandlerMethod>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<RequestMappingInfo, HandlerMethod> entry =  iterator.next();
				RequestMappingInfo rminfo=(RequestMappingInfo) entry.getKey();
				Object[] rminfos= rminfo.getPatternsCondition().getPatterns().toArray();
				for (Object string : rminfos) {
					Resources r=new Resources();
					r.setType(2);//1菜单，2url
					r.setUrl(String.valueOf(string)+".shtml");
					SYS_ALL_URL.add(r);
				}
			}
		}
		List<Resources> rrs=new ArrayList<Resources>();
		if(StringUtils.isBlank(mlike)){
			return JSON.toJSONString(SYS_ALL_URL, true);
		}else{
			for (Resources resources : SYS_ALL_URL) {
				String url=resources.getUrl();
				if(url.indexOf(mlike)>-1){
					rrs.add(resources);
				}
			}
			return JSON.toJSONString(rrs, true);
		}
	}
	
	@RequestMapping(value="/roleAuthoritylist")
	public String roleAuthorityList(Model model) {
		return "system/chooseRoleAuthority";
	}
	@ResponseBody
	@RequestMapping(value="getrolelist",method=RequestMethod.POST)
	public String getRoleList(Role role){
		List<Role> roles=iRoleService.findRolelist(role);
		return JSONArray.toJSONString(roles);
	}
	@ResponseBody
	@RequestMapping(value="menuList",method=RequestMethod.POST)
	public String menuList(Model model) {
		List<Resources> list = iResourceService.findByPidAndType(null,1L);
		List<TreeObjectNew> treeList = new ArrayList<TreeObjectNew>();
		TreeObjectNew tr = new TreeObjectNew();
		tr.setId(0L);
		tr.setName("菜单根目录");
		tr.setOpen(true);
		tr.setParent(true);
		tr.setpId(-1L);
		treeList.add(tr);
		if(list != null && list.size() > 0){
			for(Resources d:list){
				if(d.getPid().longValue()==0){
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName(d.getName());
					t.setOpen(true);
					t.setParent(true);
					t.setpId(d.getPid());
					
					//t.setIcon("css/zTreeStyle/img/diy/1_open.png");
					treeList.add(t);
				} else {
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName(d.getName());
					t.setOpen(true);
					t.setParent(false);
					t.setpId(d.getPid());
					t.setChecked(true);
					//t.setIcon("css/zTreeStyle/img/diy/3.png");
					treeList.add(t);
				}
			}
		}
		return JSONArray.toJSONString(treeList);
	}
	@ResponseBody
	@RequestMapping(value="roleMenuList",method=RequestMethod.POST)
	public String roleMenuList(Model model,Long roleId) {
		if(roleId==null){
			roleId=1L;
		}
		List<Resources> AllMenu = iResourceService.findByPidAndType(null,1L);
		List<Resources> HasMenu = iResourceService.queryResourcesListByRole(roleId);
		List<TreeObjectNew> treeList = new ArrayList<TreeObjectNew>();
		TreeObjectNew tr = new TreeObjectNew();
		tr.setId(0L);
		tr.setName("菜单根目录");
		tr.setOpen(true);
		tr.setParent(true);
		tr.setpId(-1L);
		treeList.add(tr);
		if(AllMenu != null && AllMenu.size() > 0){
			
			for(Resources d:AllMenu){
				if(d.getPid().longValue()==0){
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName(d.getName());
					t.setOpen(true);
					t.setParent(true);
					t.setpId(d.getPid());
					if(HasMenu.contains(d)){
						t.setChecked(true);
					}
					//t.setSort(d.getSort());
					//t.setIcon("css/zTreeStyle/img/diy/1_open.png");
					treeList.add(t);
				} else {
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName(d.getName());
					t.setOpen(true);
					t.setParent(false);
					t.setpId(d.getPid());
					if(HasMenu.contains(d)){
						t.setChecked(true);
					}
					//t.setSort(d.getSort());
					//t.setIcon("css/zTreeStyle/img/diy/3.png");
					treeList.add(t);
				}
			}
		}
		return JSONArray.toJSONString(treeList);
	}
	@RequestMapping(value="modifyRoleMenus",method=RequestMethod.POST)
	@ResponseBody
	public String modifyRoleMenus(String rrsstr,Long roleId){
		List<RoleResources> rrs=JSON.parseArray(rrsstr, RoleResources.class);
		iAuthorityService.modifyRoleMenus(rrs, roleId);
		return CommonUtils.msgCallback(true, "修改成功！", "","");
	}
	/**
	 * 根据菜单查询urls
	 * @return
	 */
	@RequestMapping(value="getMenuUrls",method=RequestMethod.POST)
	@ResponseBody
	public String getMenuUrls(String menuId){
		if(StringUtils.isBlank(menuId)){
			return null;
		}
		List<Resources> urlsByMenu = iResourceService.findByPidAndType(Long.parseLong(menuId),2L);
		return JSON.toJSONString(urlsByMenu, true);
	}
	/**
	 * 根据菜单查询按钮
	 * @return
	 */
	@RequestMapping(value="getMenuBtns",method=RequestMethod.POST)
	@ResponseBody
	public String getMenuBtns(String menuId){
		if(StringUtils.isBlank(menuId)){
			return null;
		}
		List<Resources> btnsByMenu = iResourceService.findAllByPidAndType(Long.parseLong(menuId),4L);
		return JSON.toJSONString(btnsByMenu, true);
	}
	/**
	 * 删除url或者btn菜单资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="deleteResourcesURLBTN",method=RequestMethod.POST)
	@ResponseBody
	public String deleteResourcesURLBTN(String res){
		List<Resources> urlOrBtn=JSON.parseArray(res, Resources.class);
		iResourceService.deleteRealList(urlOrBtn);
		return CommonUtils.msgCallback(true, "删除成功！", "","");
	}
	/**
	 * 保存url或者btn资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="saveResourcesURLBTN",method=RequestMethod.POST)
	@ResponseBody
	public String saveResourcesURLBTN(String menuId,String res){
		if(StringUtils.isBlank(menuId)){
			return CommonUtils.msgCallback(false, "没有选择菜单或者菜单为空！", "","");
		}
		List<Resources> urlOrBtn=JSON.parseArray(res, Resources.class);
		for (Resources resources : urlOrBtn) {
			resources.setPid(Long.parseLong(menuId));
			resources.setIsHide(1);//显示
		}
		iResourceService.saves(urlOrBtn);
		return CommonUtils.msgCallback(true, "保存成功！", "","");
	}
	/**
	 * 保存btn资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="saveResourcesBTN",method=RequestMethod.POST)
	@ResponseBody
	public String saveResourcesBTN(Resources btn){
		iResourceService.save(btn);
		return CommonUtils.msgCallback(true, "保存成功！", "","");
	}
	/**
	 * 修改url或者btn资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="updateResourcesURLBTN",method=RequestMethod.POST)
	@ResponseBody
	public String updateResourcesURLBTN(Resources urlOrBtn){
		iResourceService.update(urlOrBtn);
		return CommonUtils.msgCallback(true, "修改成功！", "","");
	}
	/**
	 * 修改url或者btn资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="updateResourcesBTNisHide",method=RequestMethod.POST)
	@ResponseBody
	public String updateResourcesBTNisHide(String rrsno,String rrsyes){
		List<Resources> resources_no=JSON.parseArray(rrsno, Resources.class);
		iResourceService.updatesisHide(resources_no);
		List<Resources> resources_yes=JSON.parseArray(rrsyes, Resources.class);
		iResourceService.updatesisNotHide(resources_yes);
		return CommonUtils.msgCallback(true, "保存成功！", "","");
	}
}
