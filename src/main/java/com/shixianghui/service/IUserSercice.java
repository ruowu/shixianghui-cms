package com.shixianghui.service;

import java.util.List;

import com.shixianghui.base.dao.XtUser;
import com.shixianghui.base.response.Page;



/**
 * 用户相关服务
 * @author Administrator
 *
 */
public interface IUserSercice<T> extends IBaseService{

	/**
	 * 登录
	 * @param userName
	 * @param passWord
	 * @return
	 */
	XtUser login(String userName, String passWord);

	/**
	 * 判断用户名是否存在，存在返回true
	 * @param userName
	 * @return
	 */
	Boolean checkByUserName(String userName);

	/**
	 * 获得此用户下属的销售人员
	 * @param userId
	 * @return
	 */
	List<XtUser> getUserLower(String userId,String userName);

	/**
	 * 通过用户姓名查询用户列表
	 * @param name
	 * @return
	 */
	Page<XtUser> getPageByName(String name, Integer pageSize,
			Integer currentPage);

}
