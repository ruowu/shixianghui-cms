package com.shixianghui.service;

import java.util.List;

import com.shixianghui.base.dao.XtDict;




/**
 * 字典相关服务
 * @author Administrator
 *
 */
public interface IDictSercice<T> extends IBaseService{

	/**
	 * 得到此类型下的所有字典
	 * @param type 00001:用户类型
	 */
	List<XtDict> getListByType(String type);


}
