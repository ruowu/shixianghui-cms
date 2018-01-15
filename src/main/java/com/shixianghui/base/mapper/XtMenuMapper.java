package com.shixianghui.base.mapper;

import com.shixianghui.base.dao.XtMenu;
import com.shixianghui.base.dao.XtMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XtMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int countByExample(XtMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int deleteByExample(XtMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int insert(XtMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int insertSelective(XtMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    List<XtMenu> selectByExample(XtMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    XtMenu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int updateByExampleSelective(@Param("record") XtMenu record, @Param("example") XtMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int updateByExample(@Param("record") XtMenu record, @Param("example") XtMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int updateByPrimaryKeySelective(XtMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xt_menu
     *
     * @mbggenerated Mon Dec 11 14:52:38 CST 2017
     */
    int updateByPrimaryKey(XtMenu record);

    /**
     * 根据用户id获得菜单列表
     * @param userId
     * @return
     */
	List<XtMenu> getMenuByUserId(String userId);
}