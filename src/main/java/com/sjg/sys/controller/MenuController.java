package com.sjg.sys.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.utils.ReadFiles;
import com.sjg.sys.utils.contantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.Resources;
import com.sjg.sys.service.IResourceService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.tree.TreeObjectNew;

@Controller
@RequestMapping("menu")
public class MenuController {
	@Resource
	private IResourceService iResourceService;
	/**
	 * 跳转菜单页
	 * @author wenjie
	 * @param model
	 * @return String jsp路径
	 */
	@RequestMapping(value="/list")
	public String list(Model model) {
		return "system/listMenu";
	}
	@ResponseBody
	@RequestMapping(value="menuList",method=RequestMethod.POST)
	public String menuList(Model model,HttpServletRequest request) {
		String path=request.getContextPath();
		List<Resources> list = iResourceService.findByPidAndType(null,1L);
		List<TreeObjectNew> treeList = new ArrayList<TreeObjectNew>();
		if(list != null && list.size() > 0){
			for(Resources d:list){
				if(d.getPid().longValue()==0){
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName("<i class='"+d.getIcon()+"'></i>&nbsp;"+d.getName());
					t.setOpen(true);
					t.setParent(true);
					t.setpId(d.getPid());
					t.setIcon(null);
//					t.setIcon(path+"/css/zTreeStyle/img/diy/1_open.png");
					treeList.add(t);
				} else {
					TreeObjectNew t = new TreeObjectNew();
					t.setId(d.getId());
					t.setName("<i class='"+d.getIcon()+"'></i>&nbsp;"+d.getName());
					t.setOpen(false);
					t.setParent(false);
					t.setpId(d.getPid());
					t.setIcon(null);
// 					t.setIcon(path+"/css/zTreeStyle/img/diy/3.png");
					treeList.add(t);
				}
			}
		}
		return JSONArray.toJSONString(treeList);
	}
	/**
	 * 保存一个Resources信息
	 * @author wenjie
	 * @param resources
	 * @param request
	 * @return String  json提示
	 */
    @ResponseBody
    @RequestMapping(value="saveOrUpdate",method=RequestMethod.POST)
    public String saveOrUpdate(Resources resources, HttpServletRequest request) {
    	String tip="";
    	try{
    		if(resources.getId()==null){
        		if(resources.getPid()==null){ resources.setPid(0L); }
        		resources.setType(1);
        		resources.setIsHide(1);
        		iResourceService.save(resources);
        		tip="添加成功！";
        	}else{
        		Resources newResources=iResourceService.findById(resources.getId());
        		newResources.setName(resources.getName());
        		newResources.setDescription(resources.getDescription());
        		newResources.setUrl(resources.getUrl());
        		newResources.setSort(resources.getSort());
                newResources.setIcon(resources.getIcon());
        		iResourceService.update(newResources);
        		tip="修改成功！";
        	}
			resources.setName("<i class='"+resources.getIcon()+"'></i>&nbsp;"+resources.getName());
    		return CommonUtils.msgCallback(true, tip, "",JSONArray.toJSONString(resources));
    	}catch(Exception e){
    		return CommonUtils.msgCallback(false, "操作失败,联系管理员！", "","");
    	}
    }
	 
    /**
     * 查询一个Resources信息
     * @author wenjie
     * @param id
     * @param request
     * @return String  json提示
     */
    @ResponseBody
    @RequestMapping(value="findmenu",method=RequestMethod.POST)
    public String findmenu(Long id, HttpServletRequest request) {
    	Resources resources= iResourceService.findById(id);
    	if(resources!=null){
    		return CommonUtils.msgCallback(true, "", "",JSONArray.toJSONString(resources));
    	}else {
    		return CommonUtils.msgCallback(false, "没有找到该菜单", "","");
		}
    }
    /**
     * 删除 Resources 信息
     * @author wenjie
     * @param id Resources的id
     * @param request
     * @return  String  json提示
     */
    @ResponseBody
    @RequestMapping(value="delete",method=RequestMethod.POST)
    public String delete(Long id, HttpServletRequest request) {
    	try{
    		Resources resource=iResourceService.findById(id);
    		resource.setIsHide(0);
    		recuDelete(resource);
    		iResourceService.update(resource);
    		return CommonUtils.msgCallback(true, "删除成功", "","");
    	}catch(Exception e){
    		return CommonUtils.msgCallback(false, "删除失败", "","");
    	}
     }
   //递归删除子菜单
    private void recuDelete(Resources resources){
    	if(resources!=null){
    		resources.setIsHide(0);
    		iResourceService.update(resources);
    		List<Resources> list=iResourceService.findByPidAndType(resources.getId(),null);
    		for(Resources re:list){
    			recuDelete(re);
    		}
    		
    	}
    }

	/**
	 * 获取所有的图标
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getFont",method=RequestMethod.POST)
	@FuncDescLog(desc="显示图标")
	public String getFont(HttpServletRequest request) {
		//存储所有的图标信息
		List list = new ArrayList();

        //获取项目名称
        String path = request.getSession().getServletContext().getRealPath("/");

        if(path != null && !"".equals(path)){
			if(path.contains("target")){
				//本地路径有target
				path = path.substring(0,path.indexOf("target"));
				path += "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
			}
        }
		path +=  "css" + File.separator + "Font-Awesome-master" + File.separator
				+ "css" + File.separator + "font-awesome.css";

        //先查看缓存中是否有信息
		if(contantUtil.CACHE.containsKey(contantUtil.FONTAWESOME_KEY)){
            list = (List) contantUtil.CACHE.get(contantUtil.FONTAWESOME_KEY);
		}else {
            //从文件中读取图标信息
            list = ReadFiles.readFileByLines(path);
            if (list.size() > 0) {
                //将图标信息放入缓存中
                contantUtil.CACHE.put(contantUtil.FONTAWESOME_KEY, list);
            }
        }
		return CommonUtils.msgCallback(true, null, "",JSONArray.toJSONString (list));
	}

	/**
	 * 图标搜索功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="searchFontIcon", method=RequestMethod.POST)
	@ResponseBody
	@FuncDescLog(desc="搜索图标")
	public String searchFontIcon(HttpServletRequest request){

		//返回查找的信息
		List listSearch = new ArrayList();

		//搜索图标信息
		String searchIcon = request.getParameter("searchIcon");
		if(searchIcon == null || "".equals(searchIcon)){
			return CommonUtils.msgCallback(false, "请输入查找的图标...", null,null);
		}

		List list = null;
		//查询缓存中是否有图标信息
		if(contantUtil.CACHE.containsKey(contantUtil.FONTAWESOME_KEY)){
			list = (List) contantUtil.CACHE.get(contantUtil.FONTAWESOME_KEY);
		}else{
			//获取项目名称
			String path = request.getSession().getServletContext().getRealPath("/");

			if(path != null && !"".equals(path)){
				if(path.contains("target")){
					//本地路径有target
					path = path.substring(0,path.indexOf("target"));
					path += "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
				}
			}
			path +=  "css" + File.separator + "Font-Awesome-master" + File.separator
					+ "css" + File.separator + "font-awesome.css";

			//从文件中读取图标信息
			list = ReadFiles.readFileByLines(path);
			if (list.size() > 0) {
				//将图标信息放入缓存中
				contantUtil.CACHE.put(contantUtil.FONTAWESOME_KEY, list);
			}
		}

		if(list != null){
			for (int i = 0; i < list.size() ; i++ ) {
				String s = (String) list.get(i);
				if(s.contains(searchIcon)){
					listSearch.add(s);
				}
			}
		}else{
			CommonUtils.msgCallback(false, "系统繁忙，请稍后在试！", null,null);
		}

		return CommonUtils.msgCallback(true, null, null,JSONArray.toJSONString(listSearch));
	}

}
