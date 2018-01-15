package com.shixianghui.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixianghui.base.dao.XtMenu;
import com.shixianghui.base.mapper.XtMenuMapper;
import com.shixianghui.service.IMenuSercice;

@Service
public class MenuSerciceImpl implements IMenuSercice<XtMenu> {
	@Autowired
	private XtMenuMapper mapper;

	@Override
	public List getAll() {
		return null;
	}

	@Override
	public XtMenu getById(String id) {
		return null;
	}

	@Override
	public int add(Object t) {
		return 0;
	}

	@Override
	public int update(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<XtMenu> getMenuByUserId(String userId) {
		List<XtMenu> returnList = new ArrayList<>();
		
		Map<Integer,XtMenu> map = new HashMap<>();
		List<XtMenu> list = mapper.getMenuByUserId(userId);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			XtMenu menu = list.get(i);
			if(-1==menu.getParentId()){//主菜单
				menu.setMenuDtls(new ArrayList<XtMenu>());
				map.put(menu.getId(),menu);
			}else{//子菜单
				map.get(menu.getParentId()).getMenuDtls().add(menu);
			}
		}
		for (Map.Entry<Integer, XtMenu> entry : map.entrySet()) {
			returnList.add((XtMenu) entry.getValue());
        }
		
		return returnList;
	}

}
