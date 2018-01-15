package com.shixianghui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixianghui.base.dao.XtDict;
import com.shixianghui.base.dao.XtDictExample;
import com.shixianghui.base.mapper.XtDictMapper;
import com.shixianghui.service.IDictSercice;

@Service
public class DictSerciceImpl implements IDictSercice<XtDict> {

	@Autowired
	private XtDictMapper mapper;
	@Override
	public List getAll() {
		return null;
	}

	@Override
	public int add(Object t) {
		return 0;
	}

	@Override
	public int update(Object t) {
		return 0;
	}

	@Override
	public List<XtDict> getListByType(String type) {
		XtDictExample example = new XtDictExample();
		example.createCriteria().andItemCodeLike(type+"%");
		return mapper.selectByExample(example );
	}

	@Override
	public Object getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
