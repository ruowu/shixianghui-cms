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

import com.shixianghui.base.dao.XtGoods;
import com.shixianghui.base.response.BaseResponse;
import com.shixianghui.base.utils.MessageUtils;
import com.shixianghui.base.utils.Utils;
import com.shixianghui.service.IGoodsSercice;

@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private IGoodsSercice<XtGoods> goodsSercice;

	/**
	 * 获得对应商品列表，根据商品类型获得
	 * 
	 * @param type
	 *            商品类型
	 */
	@GetMapping("goodsListByType")
	public Object goodsListByType(String type) {
		BaseResponse resp = new BaseResponse();
		List<XtGoods> items = goodsSercice.getListByType(type);
		resp.setObject(items);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		return resp;
	}

	/**
	 * 获得对应商品列表，根据商品类型获得
	 * 
	 * @param name
	 *            商品名称
	 */
	@GetMapping("goodsListByName")
	public Object goodsListByName(String name) {
		BaseResponse resp = new BaseResponse();
		List<XtGoods> items = goodsSercice.goodsListByName(name);
		resp.setObject(items);
		return resp;
	}

	/**
	 * 商品新增/修改
	 */
	@PostMapping("saveOrUpdate")
	public Object saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, XtGoods xtGoods) {
		BaseResponse resp = new BaseResponse();
		String userId = Utils.getUserId(request);
		int add = 0;
		if (Utils.isBlack(xtGoods.getGoodsId())) {
			xtGoods.setCreaterId(userId);
			xtGoods.setGoodsId(UUID.randomUUID().toString());
			add = goodsSercice.add(xtGoods);
		} else {
			add = goodsSercice.update(xtGoods);
		}
		if (add > 0) {
			resp.setCode(MessageUtils.CODE_SUCCEED);
			resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		} else {
			resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
		}
		return resp;
	}
	
	/**
	 * 根据id获得商品
	 */
	@GetMapping("getById")
	public Object getById(String goodsId){
		BaseResponse resp = new BaseResponse();
		
		XtGoods goods = (XtGoods) goodsSercice.getById(goodsId);
		resp.setObject(goods);
		resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
		
		return resp;
	}
}
