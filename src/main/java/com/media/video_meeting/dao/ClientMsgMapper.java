package com.media.video_meeting.dao;

import com.media.video_meeting.entity.ClientMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientMsgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    int insert(ClientMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    int insertSelective(ClientMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    ClientMsg selectByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ClientMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_msg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ClientMsg record);

    List<ClientMsg> queryPage();

    List<Integer> queryUserIds();

//    int updateByGroupId(Integer groupid);
    int update2DefaultGroupId(@Param("cid") Integer cid, @Param("oldgid") Integer oldgid);

    int insertClientGroupTable(@Param("cid") Integer cid, @Param("gid") Integer gid);

    int deleteClientAllGroupTable(@Param("cid") Integer cid);

    int deleteClientGroupTable(@Param("cid") Integer cid, @Param("gid") Integer gid);

    List<Integer> selectClientIdsByGourpId(Integer gid);

    int deleteByCid(@Param("cid") String cid);
}