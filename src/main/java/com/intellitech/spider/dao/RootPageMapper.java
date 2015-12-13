package com.intellitech.spider.dao;

import com.intellitech.spider.model.RootPage;
import com.intellitech.spider.model.RootPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RootPageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int countByExample(RootPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int deleteByExample(RootPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int insert(RootPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int insertSelective(RootPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    List<RootPage> selectByExample(RootPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    RootPage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int updateByExampleSelective(@Param("record") RootPage record, @Param("example") RootPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int updateByExample(@Param("record") RootPage record, @Param("example") RootPageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int updateByPrimaryKeySelective(RootPage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table root_page
     *
     * @mbggenerated Thu Dec 10 22:13:17 CST 2015
     */
    int updateByPrimaryKey(RootPage record);
}