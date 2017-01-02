package com.sjg.sys.controller;

import com.sjg.sys.utils.contantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 仪表盘管理
 * @author Hel
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	
	
	@RequestMapping("/dashBoard")
	public String dashBoard(Model model){

		//在线人数
		model.addAttribute("sessionCount", contantUtil.sessionCount);
		return "system/dashBoard";
	}
	
	
	


	
}
