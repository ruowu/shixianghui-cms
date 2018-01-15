package com.shixianghui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixianghui.base.dao.XtUser;
import com.shixianghui.base.dao.XtUserExample;
import com.shixianghui.base.mapper.XtUserMapper;
import com.shixianghui.base.response.Page;
import com.shixianghui.service.IUserSercice;


@Service
public class UserSerciceImpl implements IUserSercice<XtUser> {
	@Autowired
	private XtUserMapper userMapper;

	@Override
	public List<XtUser> getAll() {
		XtUserExample example = new XtUserExample();
		example.createCriteria().andStatusNotEqualTo(0);
		example.setOrderByClause("status desc");
		List<XtUser> users = userMapper.selectByExample(example );
		int size = users.size();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < size; i++) {
			users.get(i).setCreateTimeStr(format.format(users.get(i).getCreateTime()));
		}
		return users;
	}

	@Override
	public XtUser login(String userName, String passWord) {
		XtUserExample example = new XtUserExample();
		example.createCriteria().andUserNameEqualTo(userName)
		.andPassWordEqualTo(passWord).andStatusEqualTo(1);
		List<XtUser> list = userMapper.selectByExample(example );
		if(list.size()>0){
			return(list.get(0));
		}
		return null;
	}

	@Override
	public int add(Object t) {
		XtUser user = (XtUser)t;
		return userMapper.insertSelective(user);
	}

	@Override
	public int update(Object t) {
		XtUser user = (XtUser)t;
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public XtUser getById(String id) {
		return userMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public Boolean checkByUserName(String userName) {
		XtUserExample example = new XtUserExample();
		example.createCriteria().andUserNameEqualTo(userName)
		.andStatusEqualTo(1);
		List<XtUser> list = userMapper.selectByExample(example );
		return list!=null && list.size()>0;
	}

	@Override
	public List<XtUser> getUserLower(String userId,String userName) {
		Map<String,String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userName", userName);
		return userMapper.getUserLower(map);
	}

	@Override
	public Page<XtUser> getPageByName(String name,Integer pageSize,Integer currentPage) {
		XtUserExample example = new XtUserExample();
		example.createCriteria().andNameLike("%"+name+"%")
		.andStatusEqualTo(1);
		Integer total = userMapper.countByExample(example );
		Map<String,Object> map = new HashMap();
		map.put("name", name);
		map.put("pageSize", pageSize);
		map.put("currentPage", (currentPage-1)*pageSize);
		List<XtUser> list = userMapper.getPageByName(map);
		Page<XtUser> page = new Page<XtUser>(total,currentPage,pageSize,list);
		return page;
	}
}
