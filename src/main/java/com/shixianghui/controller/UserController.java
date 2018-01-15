package com.shixianghui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shixianghui.base.dao.XtMenu;
import com.shixianghui.base.dao.XtUser;
import com.shixianghui.base.response.BaseResponse;
import com.shixianghui.base.response.Page;
import com.shixianghui.base.utils.MessageUtils;
import com.shixianghui.base.utils.Utils;
import com.shixianghui.service.IMenuSercice;
import com.shixianghui.service.IUserSercice;

@RestController
@RequestMapping("user")
public class UserController {
	@Value("${application.adminUserIds}")
	private String adminUserIds;
	private static Integer pageSize = 6;
	@Autowired
	private IUserSercice<XtUser> userService;
	@Autowired
	private IMenuSercice<XtMenu> menuService;

	@GetMapping("/login")
	public Object login(HttpServletRequest req, HttpServletResponse resp2,
			String userName, String passWord) {
		BaseResponse resp = new BaseResponse();
		XtUser xtUser = userService.login(userName, passWord);
		if (xtUser != null) {
			String token = Utils.createToken(xtUser.getUserId().toString());
			resp.setObject2(token);
			resp.setObject(xtUser);
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		} else {
			resp.setObject(xtUser);
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_FAILUER);
		}
		return resp;
	}

	@PostMapping("/saveOrUpdate")
	public Object saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, XtUser user) {
		BaseResponse resp = new BaseResponse();
		String userId = Utils.getUserId(request);
		int add = 0;
		if(user.getUserId()==null||user.getUserId()==0){
			user.setCreateUid(userId);
			user.setHigherUid(userId);
			add = userService.add(user);
		}else{
			Boolean checkAuthority = this.checkAuthority(request);
			if(!checkAuthority){
				resp.setCode(MessageUtils.CODE_NO_AUTHORITY);
				resp.setMessage(MessageUtils.MESSAGE_NO_AUTHORITY);
				return resp;
			}
			add = userService.update(user);
		}
		if(add>0){
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		}else{
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 展示所有用户
	 */
	@GetMapping("/allUsers")
	public Object allUsers(HttpServletRequest req,
			HttpServletResponse resp2,String name,Integer currentPage) {
		BaseResponse resp = new BaseResponse();
		List<XtUser> allUsers = null;
		if(Utils.isBlack(name)&&currentPage==null){
			allUsers = userService.getAll();
			resp.setObject(allUsers);
		}else{
			if(name==null){
				name = "";
			}
			Page<XtUser> page = userService.getPageByName(name,pageSize,currentPage);
			resp.setObject(page);
		}
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		return resp;
	}
	
	/**
	 * 通过id获得对象
	 */
	@GetMapping("/getUserById")
	public Object getUserById(HttpServletRequest req,
			HttpServletResponse resp2,String userId) {
		BaseResponse resp = new BaseResponse();
		XtUser user = (XtUser)userService.getById(userId);
		resp.setObject(user);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		return resp;
	}
	
	/**
	 * 删除用户
	 */
	@GetMapping("/deleteById")
	public Object deleteById(HttpServletRequest request,
			HttpServletResponse resp2,Integer userId) {
		BaseResponse resp = new BaseResponse();
		Boolean checkAuthority = this.checkAuthority(request);
		if(!checkAuthority){
			resp.setCode(MessageUtils.CODE_NO_AUTHORITY);
			resp.setMessage(MessageUtils.MESSAGE_NO_AUTHORITY);
			return resp;
		}
		if(adminUserIds.equals(userId)){
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage("此用户不能被删除");
			return resp;
		}
		XtUser xtUser = new XtUser();
		xtUser.setUserId(userId);
		xtUser.setStatus(0);
		int del = userService.update(xtUser);
		if(del>0){
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		}else{
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 检查用户名是否重复
	 */
	@GetMapping("/checkUserName")
	public Object checkUserName(HttpServletRequest req,
			HttpServletResponse resp2,String userName){
		BaseResponse resp = new BaseResponse();
		Boolean isExit = userService.checkByUserName(userName);
		if(!isExit){
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		}else{
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 获得当前用户可以看到用户列表
	 */
	@GetMapping("/getMySalers")
	public Object getMySalers(HttpServletRequest req,
			HttpServletResponse resp2,String userName) {
		BaseResponse resp = new BaseResponse();
		String userId = Utils.getUserId(req);
		List<XtUser> allUsers = userService.getUserLower(userId,userName);
		resp.setObject(allUsers);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		return resp;
	}
	
	/**
	 * 获得当前用户可以看到用户列表
	 */
	@GetMapping("/getAuthority")
	public Object getAuthority(HttpServletRequest req,
			HttpServletResponse resp2) {
		BaseResponse resp = new BaseResponse();
		String userId = Utils.getUserId(req);
		List<XtMenu> menuList = menuService.getMenuByUserId(userId);
		resp.setObject(menuList);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		return resp;
	}
	
	private Boolean checkAuthority(HttpServletRequest request){
		String userId = Utils.getUserId(request);
		if(adminUserIds.indexOf(userId)==-1){//没有此权限
			return false;
		}else{
			return true;
		}
	}
}
