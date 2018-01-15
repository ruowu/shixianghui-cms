package com.shixianghui.base.response;

import java.util.List;

import com.shixianghui.base.dao.YwSaleRecord;

public class Page<T> {
	private Integer total;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private List<T> list;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Page(Integer total, Integer currentPage, Integer pageSize, List<T> list) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = (total%pageSize==0)?(total/pageSize):(total/pageSize+1);
		this.list = list;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
