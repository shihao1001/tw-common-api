package com.thinkingwriter.common.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {
	
	@RequestMapping("/testRequest")
	@	ResponseBody
	public Object test(HttpServletRequest request){
		return "testRequest";
	}

}
