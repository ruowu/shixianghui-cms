package com.shixianghui.service;

import java.util.List;

import com.shixianghui.base.dao.XtDict;
import com.shixianghui.base.dao.XtGoods;




/**
 * 商品相关服务
 * @author Administrator
 *
 */
public interface IGoodsSercice<T> extends IBaseService{

	/**
	 * 得到此类型下的所有字典
	 * @param type 00001:用户类型
	 */
	List<XtGoods> getListByType(String type);

	/**
	 * 根据商品名称
	 * @param name
	 * @return
	 */
	List<XtGoods> goodsListByName(String name);


}
