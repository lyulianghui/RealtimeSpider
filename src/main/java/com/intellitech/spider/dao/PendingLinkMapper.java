package com.intellitech.spider.dao;

import com.intellitech.spider.model.PendingLink;
import com.intellitech.spider.model.PendingLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PendingLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int countByExample(PendingLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int deleteByExample(PendingLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int insert(PendingLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int insertSelective(PendingLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    List<PendingLink> selectByExample(PendingLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    PendingLink selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int updateByExampleSelective(@Param("record") PendingLink record, @Param("example") PendingLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int updateByExample(@Param("record") PendingLink record, @Param("example") PendingLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int updateByPrimaryKeySelective(PendingLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pending_link
     *
     * @mbggenerated Mon Dec 14 23:08:30 CST 2015
     */
    int updateByPrimaryKey(PendingLink record);
}