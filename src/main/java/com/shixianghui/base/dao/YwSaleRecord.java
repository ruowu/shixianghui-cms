package com.shixianghui.base.dao;

import java.util.Date;

public class YwSaleRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.sale_record_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String saleRecordId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.goods_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String goodsId;
    
    private String goodsName;//商品名称
    private String goodsType;//商品类型名称

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.sale_type
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Integer saleType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.original_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Double originalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.discount
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Double discount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.discount_reason
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String discountReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.sale_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Double salePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.number
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Double number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.total_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Integer totalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.sale_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Date saleTime;
    
    private String saleTimeStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.saler_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String salerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.saler_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String salerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.buyer_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String buyerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.buyer_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String buyerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.creater_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private String createrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.create_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yw_sale_record.status
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.sale_record_id
     *
     * @return the value of yw_sale_record.sale_record_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getSaleRecordId() {
        return saleRecordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.sale_record_id
     *
     * @param saleRecordId the value for yw_sale_record.sale_record_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSaleRecordId(String saleRecordId) {
        this.saleRecordId = saleRecordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.goods_id
     *
     * @return the value of yw_sale_record.goods_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.goods_id
     *
     * @param goodsId the value for yw_sale_record.goods_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.sale_type
     *
     * @return the value of yw_sale_record.sale_type
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Integer getSaleType() {
        return saleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.sale_type
     *
     * @param saleType the value for yw_sale_record.sale_type
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.original_price
     *
     * @return the value of yw_sale_record.original_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.original_price
     *
     * @param originalPrice the value for yw_sale_record.original_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.discount
     *
     * @return the value of yw_sale_record.discount
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.discount
     *
     * @param discount the value for yw_sale_record.discount
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.discount_reason
     *
     * @return the value of yw_sale_record.discount_reason
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getDiscountReason() {
        return discountReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.discount_reason
     *
     * @param discountReason the value for yw_sale_record.discount_reason
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.sale_price
     *
     * @return the value of yw_sale_record.sale_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.sale_price
     *
     * @param salePrice the value for yw_sale_record.sale_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.number
     *
     * @return the value of yw_sale_record.number
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Double getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.number
     *
     * @param number the value for yw_sale_record.number
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setNumber(Double number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.total_price
     *
     * @return the value of yw_sale_record.total_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Integer getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.total_price
     *
     * @param totalPrice the value for yw_sale_record.total_price
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.sale_time
     *
     * @return the value of yw_sale_record.sale_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Date getSaleTime() {
        return saleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.sale_time
     *
     * @param saleTime the value for yw_sale_record.sale_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.saler_id
     *
     * @return the value of yw_sale_record.saler_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getSalerId() {
        return salerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.saler_id
     *
     * @param salerId the value for yw_sale_record.saler_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSalerId(String salerId) {
        this.salerId = salerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.saler_name
     *
     * @return the value of yw_sale_record.saler_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getSalerName() {
        return salerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.saler_name
     *
     * @param salerName the value for yw_sale_record.saler_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.buyer_id
     *
     * @return the value of yw_sale_record.buyer_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.buyer_id
     *
     * @param buyerId the value for yw_sale_record.buyer_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.buyer_name
     *
     * @return the value of yw_sale_record.buyer_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.buyer_name
     *
     * @param buyerName the value for yw_sale_record.buyer_name
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.creater_id
     *
     * @return the value of yw_sale_record.creater_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public String getCreaterId() {
        return createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.creater_id
     *
     * @param createrId the value for yw_sale_record.creater_id
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.create_time
     *
     * @return the value of yw_sale_record.create_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.create_time
     *
     * @param createTime the value for yw_sale_record.create_time
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yw_sale_record.status
     *
     * @return the value of yw_sale_record.status
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yw_sale_record.status
     *
     * @param status the value for yw_sale_record.status
     *
     * @mbggenerated Sun Oct 29 13:36:00 GMT+08:00 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getSaleTimeStr() {
		return saleTimeStr;
	}

	public void setSaleTimeStr(String saleTimeStr) {
		this.saleTimeStr = saleTimeStr;
	}
}