package com.sjg.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.sjg.sys.entity.Log;
import com.sjg.sys.entity.vo.LogQueryParm;
import com.sjg.sys.service.ILogService;
import com.sjg.sys.utils.Pager;

/**
 * 系统日志处理
 * @author Hel
 *
 */
@Controller
@RequestMapping("/syslog")
public class SysLogController {

	@Resource
	private ILogService logService;
	
	/**
	 * 跳转到操作日志页面
	 * @return
	 */
	@RequestMapping("/operationLog")
	public String operationLog(){
		return "system/log/operationLog";
	}
	
	/**
	 * 跳转到异常日志页面
	 * @return
	 */
	@RequestMapping("/exceptionLog")
	public String exceptionLog(){
		return "system/log/exceptionLog";
	}
	
	/**
	 * 分页列表数据
	 */
	@RequestMapping("/queryExecLogList")
	@ResponseBody
	public String queryExLogList(Model model,Long offSet,Long pageSize, LogQueryParm param){
		List<Log> log=logService.getLogListLimit(offSet, pageSize, param);
		return JSON.toJSONString(log);
	}
	/**
	 * 分页列表数据(初始化)
	 */
	@RequestMapping("/queryExecLogPager")
	@ResponseBody
	public String queryExecLogPager(Model model,Long offSet,Long pageSize, LogQueryParm param){
		Pager<Log> p=logService.getLogListPager(offSet, pageSize, param);
		return JSON.toJSONString(p);
	}
	
	/**
	 * 根据id查看异常详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryLogDetailById")
	public String queryLogDetailById(HttpServletRequest request,Model model,String id){
		 Log log = logService.getLogDetailById(id);
		 model.addAttribute("log", log);
		return "system/log/logDetail";
	}
}
