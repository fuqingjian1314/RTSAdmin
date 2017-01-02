package com.sjg.sys.ext.websocket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/msg")
public class TestController {

	// 用户登录
	@RequestMapping(value = "talk")
	public ModelAndView talk(HttpServletRequest request) {
		return new ModelAndView("app/talk");
	}

}