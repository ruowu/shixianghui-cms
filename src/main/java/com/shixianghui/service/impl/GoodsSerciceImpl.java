package com.shixianghui.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixianghui.base.dao.XtGoods;
import com.shixianghui.base.dao.XtGoodsExample;
import com.shixianghui.base.mapper.XtGoodsMapper;
import com.shixianghui.service.IGoodsSercice;

@Service
public class GoodsSerciceImpl implements IGoodsSercice<XtGoods> {

	@Autowired
	private XtGoodsMapper mapper;
	@Override
	public List getAll() {
		return null;
	}

	@Override
	public int add(Object record) {
		return mapper.insertSelective((XtGoods)record);
	}

	@Override
	public int update(Object record) {
		return mapper.updateByPrimaryKeySelective((XtGoods)record);
	}

	@Override
	public List<XtGoods> getListByType(String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		return mapper.getListByType(map);
	}

	@Override
	public XtGoods getById(String id) {
		XtGoods goods = mapper.selectByPrimaryKey(id);
		return goods;
	}

	@Override
	public List<XtGoods> goodsListByName(String name) {
		XtGoodsExample example = new XtGoodsExample();
		example.createCriteria().andGoodsNameLike("%"+name+"%");
		example.setOrderByClause("goods_name");
		return mapper.selectByExample(example );
	}

}
