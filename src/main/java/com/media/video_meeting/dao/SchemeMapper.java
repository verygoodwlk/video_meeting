package com.media.video_meeting.dao;

import com.media.video_meeting.entity.Scheme;

public interface SchemeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    int insert(Scheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    int insertSelective(Scheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    Scheme selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Scheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Scheme record);
}