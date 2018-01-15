package com.shixianghui.service;

import java.util.List;

import com.shixianghui.base.dao.YwSaleRecord;

/**
 * 销售记录相关服务
 * @author Administrator
 *
 */
public interface ISaleRecordSercice<T> extends IBaseService{

	/**
	 * 通过商品类型，商品名，销售员列表获得对应的时间内分页销售信息
	 * @param startDate
	 * @param endDate
	 * @param goodsType
	 * @param goodsId
	 * @param salerIds
	 * @param currentPage
	 * @param pageSize
	 */
	List<YwSaleRecord> getSaleRecords(String startDate, String endDate, String goodsType,
			String goodsId, String salerIds, Integer currentPage,
			Integer pageSize);

	/**
	 * 通过商品类型，商品名，销售员列表获得对应的时间内分页销售总数
	 * @param startDate
	 * @param endDate
	 * @param goodsType
	 * @param goodsId
	 * @param salerIds
	 * @param currentPage
	 * @param currentPage2
	 * @return
	 */
	Integer getSaleTotal(String startDate, String endDate, String goodsType,
			String goodsId, String salerIds, Integer currentPage,
			Integer pageSize);

	/**
	 * 通过商品类型，商品名，销售员列表获得对应的时间内分页销售总金额总数
	 * @param startDate
	 * @param endDate
	 * @param goodsType
	 * @param goodsId
	 * @param salerIds
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	List<YwSaleRecord> getTotalSaleRecords(String startDate, String endDate,
			String goodsType, String goodsId, String salerIds,
			Integer currentPage, Integer pageSize);

	/**
	 * 通过商品类型，商品名，销售员列表获得对应的时间内分页销售总金额总数
	 * @param startDate
	 * @param endDate
	 * @param goodsType
	 * @param goodsId
	 * @param salerIds
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Integer getTotalSale(String startDate, String endDate, String goodsType,
			String goodsId, String salerIds, Integer currentPage,
			Integer pageSize);


}
