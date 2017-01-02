package com.sjg.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.Pager;
/**
 * 控件例子
 * @author fqj
 *
 */
@Controller
@RequestMapping("/example")
public class ExampleContrller {
	@Resource
	private IUserService iUserService;
	/**
	 * 用户列表页面
	 * @return
	 */
	@RequestMapping("/gridTablePager")
	public String userList(){
		return "system/example/dataGridTablePager";
	}
	/**
	 * 分页列表数据
	 */
	@RequestMapping("/queryUserList")
	@ResponseBody
	public String queryUserList(Model model,Long offSet,Long pageSize,User user){
		List<User> users=iUserService.getUserListLimit(offSet, pageSize,user);
		return JSON.toJSONString(users);
	}
	/**
	 * 分页列表数据
	 */
	@RequestMapping("/queryUserPager")
	@ResponseBody
	public String pagerdemo(Model model,Long offSet,Long pageSize,User user){
		Pager<User> p=iUserService.getUserListPager(offSet, pageSize,user);
		return JSON.toJSONString(p);
	}
}
