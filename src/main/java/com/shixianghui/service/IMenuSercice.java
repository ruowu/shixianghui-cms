package com.shixianghui.service;

import java.util.List;

import com.shixianghui.base.dao.XtMenu;

/**
 * 用户相关服务
 * @author Administrator
 *
 */
public interface IMenuSercice<T> extends IBaseService{
	/**
	 * 通过用户id获得用户菜单列表
	 * @param userId
	 * @return
	 */
	List<XtMenu> getMenuByUserId(String userId);

}
