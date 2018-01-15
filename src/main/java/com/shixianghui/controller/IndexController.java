package com.shixianghui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	@Value("${application.adminUserIds}")
	private String adminUserIds;
	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "forward:/html/introduce.html";
	}
	
	/**
	 * 页面跳转
	 * @param url
	 * @return
	 */
	@RequestMapping("/url/{url}")
	public String url(@PathVariable("url") String url) {
		adminUserIds = adminUserIds+1;
		url = "forward:/html/"+url+".html";
		return url;
	}
	
	@RequestMapping("/index2")
	public String index2(ModelMap map) {
		List<String> list = new ArrayList<String>();
		list.add("hello Thymeleaf!");
		list.add("ModelMap map");
		map.addAttribute("hello2", "hello2 Thymeleaf!");
		map.addAttribute("hello", list);
		return "ss/index2";
	}
}
