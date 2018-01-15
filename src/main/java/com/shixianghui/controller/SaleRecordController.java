package com.shixianghui.controller;


import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shixianghui.base.dao.XtUser;
import com.shixianghui.base.dao.YwSaleRecord;
import com.shixianghui.base.response.BaseResponse;
import com.shixianghui.base.response.Page;
import com.shixianghui.base.utils.MessageUtils;
import com.shixianghui.base.utils.Utils;
import com.shixianghui.service.ISaleRecordSercice;
import com.shixianghui.service.IUserSercice;

@RestController
@RequestMapping("saleRecord")
public class SaleRecordController {
	@Autowired
	private ISaleRecordSercice<YwSaleRecord> saleRecordService;
	@Autowired
	private IUserSercice<XtUser> userService;
	private static Integer pageSize = 6;


	/**
	 * 通过条件筛选获得销售记录
	 * @param req
	 * @param resp2
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @param goodsType 商品类型
	 * @param goodsId 商品id
	 * @param salerId 销售员id
	 * @return
	 */
	@GetMapping("/getList")
	public Object getList(HttpServletRequest req, HttpServletResponse resp2,
			String startDate, String endDate,Integer currentPage,
			String goodsType,String goodsId,String salerIds) {
		BaseResponse resp = new BaseResponse();
		
		String userId = Utils.getUserId(req);
		//当salerId为空的时候，获得此用户可以看到的销售员id列
		if(Utils.isBlack(salerIds)){
			List<XtUser> lowerUsers = userService.getUserLower(userId,"");
			int size = lowerUsers.size();
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < size; i++) {
				sb.append(lowerUsers.get(i).getUserId());
				if(i<size-1){
					sb.append(",");
				}
			}
			salerIds = sb.toString();
		}
		List<YwSaleRecord> saleRecords = saleRecordService.getSaleRecords(startDate,endDate,goodsType,goodsId,salerIds,currentPage,pageSize);
		Integer total = saleRecordService.getSaleTotal(startDate, endDate, goodsType, goodsId, salerIds, currentPage, pageSize);
		Page<YwSaleRecord> page = new Page<YwSaleRecord>(total ,currentPage,pageSize,saleRecords);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_SUCCEED);
		resp.setObject(page);
		
		return resp;
	}

	/**
	 * 新增/修改
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@PostMapping("/saveOrUpdate")
	public Object saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, YwSaleRecord record) {
		BaseResponse resp = new BaseResponse();
		String userId = Utils.getUserId(request);
		record.setSaleTime(Utils.getTimeByStr(record.getSaleTimeStr()));
		int add = 0;
		if(Utils.isBlack(record.getSaleRecordId())){
			record.setCreaterId(userId);
			record.setSaleRecordId(UUID.randomUUID().toString());
			add = saleRecordService.add(record);
		}else{
			//判断修改权限
			add = saleRecordService.update(record);
		}
		if(add>0){
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		}else{
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 删除用户
	 */
	@GetMapping("/deleteById")
	public Object deleteById(HttpServletRequest req,
			HttpServletResponse resp2,String recordId) {
		BaseResponse resp = new BaseResponse();
		YwSaleRecord record = new YwSaleRecord();
		record.setSaleRecordId(recordId);
		record.setStatus(0);
		int del = saleRecordService.update(record);
		if(del>0){
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		}else{
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 通过条件筛选获得销售总金额记录
	 * @param req
	 * @param resp2
	 * @param startDate 查询开始时间
	 * @param endDate 查询结束时间
	 * @param goodsType 商品类型
	 * @param goodsId 商品id
	 * @param salerId 销售员id
	 * @return
	 */
	@GetMapping("/getTotalList")
	public Object getTotalList(HttpServletRequest req, HttpServletResponse resp2,
			String startDate, String endDate,Integer currentPage,
			String goodsType,String goodsId,String salerIds) {
		BaseResponse resp = new BaseResponse();
		
		String userId = Utils.getUserId(req);
		//当salerId为空的时候，获得此用户可以看到的销售员id列
		if(Utils.isBlack(salerIds)){
			List<XtUser> lowerUsers = userService.getUserLower(userId,"");
			int size = lowerUsers.size();
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < size; i++) {
				sb.append(lowerUsers.get(i).getUserId());
				if(i<size-1){
					sb.append(",");
				}
			}
			salerIds = sb.toString();
		}
		List<YwSaleRecord> saleRecords = saleRecordService.getTotalSaleRecords(startDate,endDate,goodsType,goodsId,salerIds,currentPage,pageSize);
		Integer total = saleRecordService.getTotalSale(startDate, endDate, goodsType, goodsId, salerIds, currentPage, pageSize);
		Page<YwSaleRecord> page = new Page<YwSaleRecord>(total ,currentPage,pageSize,saleRecords);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_SUCCEED);
		resp.setObject(page);
		
		return resp;
	}
}
