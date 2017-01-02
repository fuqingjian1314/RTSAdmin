package com.sjg.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.Dictionary;
import com.sjg.sys.service.IDictionaryService;
 
/**
 * 系统数据类型管理
 * @author huangliang
 *
 */
@Controller
@RequestMapping("/articleType")
public class ArticleTypeController {
	@Autowired
	private IDictionaryService iDictionaryService;

	/**
	 * 文章栏目管理首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		Map<String, Object> param = new HashMap<>();
		param.put("pid", 45);//45为文章栏目的ID
		List<Dictionary> dList = iDictionaryService.findByMulCond(param);
		model.addAttribute("dList", dList);
		return "system/articleType";
	}
	
	/**
	 * 获取一个 Dictionary
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
     * 修改或新增
     * @param dictionary
     * @return
     */
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Dictionary dictionary) {
    	
    	if(dictionary == null){
    		return null;
    	}
    	if(dictionary.getId() == null){
    		//新增
    		dictionary.setPid(45L);
    		dictionary.setType(1L);
    		dictionary.setIsHide(1L);
    		iDictionaryService.save(dictionary);
    	}else{
    		//修改
    		iDictionaryService.updateSelective(dictionary);
    	}
    	
    	return "redirect:index.shtml";
    }
    
    
	/**
	 * 获取一个 Dictionary
	 * @param id 主键
	 * @return String json格式字符串
	 */
	@RequestMapping("delete")
	public String delete(Long id){
		/*iDictionaryService.delete(id);*/
		if(id == null){
			return "redirect:index.shtml";
		}
		Dictionary dictionary = new Dictionary();
		dictionary.setId(id);
		dictionary.setIsHide(0L);
		iDictionaryService.updateSelective(dictionary);
		return "redirect:index.shtml";
	}
	
	/**
	 * 验证code 
	 * @param id
	 * @param code
	 * @return
	 */
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
}
    
