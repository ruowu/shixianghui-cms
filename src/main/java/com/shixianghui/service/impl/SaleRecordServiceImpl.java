package com.shixianghui.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixianghui.base.dao.YwSaleRecord;
import com.shixianghui.base.mapper.YwSaleRecordMapper;
import com.shixianghui.service.ISaleRecordSercice;

@Service
public class SaleRecordServiceImpl implements ISaleRecordSercice<YwSaleRecord> {

	@Autowired
	private YwSaleRecordMapper mapper;
	@Override
	public List getAll() {
		return null;
	}

	@Override
	public Object getById(String id) {
		return null;
	}

	@Override
	public int add(Object t) {
		return mapper.insertSelective((YwSaleRecord)t);
	}

	@Override
	public int update(Object t) {
		return mapper.updateByPrimaryKeySelective((YwSaleRecord)t);
	}

	@Override
	public List<YwSaleRecord> getSaleRecords(String startDate, String endDate,
			String goodsType, String goodsId, String salerIds,
			Integer currentPage, Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("goodsType", goodsType);
		map.put("goodsId", goodsId);
		String[] split = salerIds.split(",");
		List<String> salerIdList = java.util.Arrays.asList(split);
		map.put("salerIds", salerIdList);
		map.put("currentPage", (currentPage-1)*pageSize);
		map.put("pageSize", pageSize);
		return mapper.getSaleRecords(map);
	}

	@Override
	public Integer getSaleTotal(String startDate, String endDate,
			String goodsType, String goodsId, String salerIds,
			Integer currentPage, Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("goodsType", goodsType);
		map.put("goodsId", goodsId);
		String[] split = salerIds.split(",");
		List<String> salerIdList = java.util.Arrays.asList(split);
		map.put("salerIds", salerIdList);
		map.put("currentPage", (currentPage-1)*pageSize);
		map.put("pageSize", pageSize);
		return mapper.getSaleTotal(map);
	}

	@Override
	public List<YwSaleRecord> getTotalSaleRecords(String startDate,
			String endDate, String goodsType, String goodsId, String salerIds,
			Integer currentPage, Integer pageSize) {Map<String,Object> map = new HashMap<>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("goodsType", goodsType);
			map.put("goodsId", goodsId);
			String[] split = salerIds.split(",");
			List<String> salerIdList = java.util.Arrays.asList(split);
			map.put("salerIds", salerIdList);
			map.put("currentPage", (currentPage-1)*pageSize);
			map.put("pageSize", pageSize);
			return mapper.getSaleRecords(map);
	}

	@Override
	public Integer getTotalSale(String startDate, String endDate,
			String goodsType, String goodsId, String salerIds,
			Integer currentPage, Integer pageSize) {
			Map<String,Object> map = new HashMap<>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("goodsType", goodsType);
			map.put("goodsId", goodsId);
			String[] split = salerIds.split(",");
			List<String> salerIdList = java.util.Arrays.asList(split);
			map.put("salerIds", salerIdList);
			map.put("currentPage", (currentPage-1)*pageSize);
			map.put("pageSize", pageSize);
			return mapper.getSaleTotal(map);
	}

}
