package com.shixianghui.service;

import java.util.List;

/**
 * 公共服务
 * @author Administrator
 *
 */
public interface IBaseService<T> {

	public List<T> getAll();
	
	public T getById(String id);
	
	public int add(T t);
	
	public int update(T t);
}
