package com.shixianghui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jspTest")
public class JSPTestController {
	// 从 application.properties 中读取配置，如取不到默认值为HelloShanhy

	@Value("${application.hello:Hello Angel}")
	private String hello;

	@RequestMapping("/helloJsp")
	public Object StringhelloJsp(Map<String, Object> map) {
		System.out.println("HelloController.helloJsp().hello=" + hello);
		map.put("hello", hello);
		return "helloJsp";
	}
}
