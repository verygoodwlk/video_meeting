package com.media.video_meeting.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.media.video_meeting.entity.Meeting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingMapper extends BaseMapper<Meeting> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    /*int insert(Meeting record);*/

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    int insertSelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    Meeting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meeting
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Meeting record);


    /**
     * 查询所有会议
     * @return
     */
    List<Meeting> queryAll();

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<Meeting> queryByKeyWord(@Param("keyword") String keyword);

    int insertMeetingClient(@Param("mid") Integer mid, @Param("cids") Integer[] cids, @Param("sid") Integer sid);
}