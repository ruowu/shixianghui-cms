package com.shixianghui.base.mapper;

import com.shixianghui.base.dao.XtGoods;
import com.shixianghui.base.dao.XtGoodsExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface XtGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int countByExample(XtGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int deleteByExample(XtGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int deleteByPrimaryKey(String goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int insert(XtGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int insertSelective(XtGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    List<XtGoods> selectByExample(XtGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    XtGoods selectByPrimaryKey(String goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int updateByExampleSelective(@Param("record") XtGoods record, @Param("example") XtGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int updateByExample(@Param("record") XtGoods record, @Param("example") XtGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int updateByPrimaryKeySelective(XtGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_goods
     *
     * @mbggenerated Wed Oct 25 23:17:44 GMT+08:00 2017
     */
    int updateByPrimaryKey(XtGoods record);

    /**
     * 根据商品类型获得商品列表
     * @param type
     * @return
     */
	List<XtGoods> getListByType(Map<String,Object> map);
}