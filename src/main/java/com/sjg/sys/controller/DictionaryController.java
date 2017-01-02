package com.sjg.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.Dictionary;
import com.sjg.sys.entity.vo.DictionaryVo;
import com.sjg.sys.service.IDictionaryService;
import com.sjg.sys.utils.CommonUtils;
 


/**
 * 系统数据类型管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	@Autowired
	private IDictionaryService iDictionaryService;

	/**
	 * 跳转到字典 页面
	 * @author wenjie
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model) {
		Map<String, Object> parm=new HashMap<String, Object>();
		parm.put("pid", 0);
		List<Dictionary> leftList=iDictionaryService.findByMulCond(parm);//无关联查询
		List<DictionaryVo> secondList=null;
		if(leftList!=null && leftList.size()>0){
			parm.put("pid", leftList.get(0).getId());
			secondList = iDictionaryService.findByMulCondRelation(parm);//关联查询
			for(DictionaryVo d:secondList){
				d.setPid(0L);
			}
		} 
		List<DictionaryVo> newSecondList = new ArrayList<DictionaryVo>();
		recursionAdd(secondList,newSecondList);
		model.addAttribute("leftList", leftList);
		model.addAttribute("secondList", newSecondList);
		model.addAttribute("pid", parm.get("pid"));
		return "/system/listDictionary";
	}
	//递归将 孩子的数据加到父亲 后面
	private void recursionAdd( List<DictionaryVo> oldlist,List<DictionaryVo> newlist){
		if(oldlist!=null && oldlist.size()>0){
			for(DictionaryVo d1:oldlist){
				newlist.add(d1);
				if(d1.getChildrenList()!=null && d1.getChildrenList().size()>0){
					recursionAdd(d1.getChildrenList(),newlist);
	 			}
				d1.setChildrenList(null);
			}
		}
	}
	
	/**
	 * ajax 获取所有字典数据
	 * @author wenjie
	 * @return String json字符串
	 */
	@RequestMapping(value="/ajaxlist",method=RequestMethod.POST)
	@ResponseBody
	public String list(Long pid,String dicName,HttpServletRequest request) {
		Map<String, Object> parm=new HashMap<String, Object>();
		List<Dictionary> leftList=null;//点击了查询按钮
		if(pid==null){
			parm.put("value", dicName);
			parm.put("pid", 0);
			leftList=iDictionaryService.findByMulCond(parm);//无关联查询
			if(leftList!=null && leftList.size()>0){
				pid= leftList.get(0).getId();
			} 
		}
		
		List<DictionaryVo> newSecondList = new ArrayList<DictionaryVo>();//点击了一级菜单
		if(pid!=null){
			parm.remove("value");
			parm.put("pid", pid);
			List<DictionaryVo> secondList = iDictionaryService.findByMulCondRelation(parm);//二级 至以后 关联查询
			for(DictionaryVo d:secondList){
				d.setPid(0L);
			}
			recursionAdd(secondList,newSecondList);
		}
		parm.put("leftList", leftList);
		parm.put("secondList", newSecondList);
		return JSONArray.toJSONString(parm);
	}
	
	/**
	 * 保存或更新字典
	 * @author wenjie
	 * @param dictionary
	 * @param request
	 * @return String  json 提示字符串
	 */
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Dictionary dictionary, HttpServletRequest request) {
    	String tips="";
    	boolean result=true;
    	try{
			if(dictionary.getId()==null){
	    		if(dictionary.getPid()==null){ dictionary.setPid(0L); }
	    		dictionary.setIsHide(1L);
	    		iDictionaryService.save(dictionary);
				tips="添加成功！";
	    		result=true;
	    	}else{
	    		Dictionary newdictionary=iDictionaryService.findById(dictionary.getId());
	    		if(newdictionary.getType()!=0){
	    			newdictionary.setValue(dictionary.getValue());
	    			newdictionary.setDescription(dictionary.getDescription());
	    			newdictionary.setType(dictionary.getType());
	    			newdictionary.setSort(dictionary.getSort());
	    			newdictionary.setCode(dictionary.getCode());
	    			iDictionaryService.update(newdictionary);
	    			dictionary.setId(newdictionary.getId());
	    			tips="修改,成功！";
					result=true;
	    		}else{
	    			tips="系统参数，不允许修改！请联系管理员！";
	    			result=false;
	    		}
	    	}
			return CommonUtils.msgCallback(result, tips, "",JSONArray.toJSONString(dictionary));
    	}catch(Exception e){
    		return CommonUtils.msgCallback(false, "操作失败！请联系管理员", "",JSONArray.toJSONString(dictionary));
    	}
    }
    
   
     
	/**
	 * 获取一个 Dictionary
	 * @author wenjie
	 * @param id 主键
	 * @return String json格式字符串
	 */
    @ResponseBody
    @RequestMapping("findDic")
    public String findDic(Long id){
        Dictionary dictionary=iDictionaryService.findById(id);
        return JSONArray.toJSONString(dictionary);
    }
	
	/**
	 * 获取一个 Dictionary
	 * @author wenjie
	 * @param id 主键
	 * @return String json格式字符串
	 */
    @ResponseBody
    @RequestMapping("delete")
    public String delete(Long id){
    	if(id==null){ return CommonUtils.msgCallback(false, "删除的数据异常，请联系管理员！", "",id+"");}
    	try{
    		Map<String, Object> parm=new HashMap<String, Object>();
    		parm.put("id",id);
    		List<DictionaryVo> secondList = iDictionaryService.findByMulCondRelation(parm);//关联查询
    		List<DictionaryVo> newSecondList=new ArrayList<DictionaryVo>();
    		recursionAdd(secondList,newSecondList);
    		iDictionaryService.deleteList(newSecondList);
    		return CommonUtils.msgCallback(true, "删除,成功！", "",id+"");
    	}catch(Exception e){
    		return CommonUtils.msgCallback(false, "删除失败，请联系管理员！", "",id+"");
    	}
    }
    
    @ResponseBody
    @RequestMapping("checkCode")
    public boolean checkCode(Long id,String code){
    	List<Dictionary> dictionary = iDictionaryService.checkCode(id,code);
    	if(dictionary!=null && dictionary.size()>0){
    		return true;
    	}else{
    		return false;
    	}
    }
    @ResponseBody
    @RequestMapping("checkValue")
    public boolean checkValue(Long id,String value){
    	List<Dictionary> dictionary = iDictionaryService.checkValue(id,value);
    	if(dictionary!=null && dictionary.size()>0){
    		return true;
    	}else{
    		return false;
    	}
    }
}
