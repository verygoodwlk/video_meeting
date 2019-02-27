package com.media.video_meeting.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.media.video_meeting.entity.Solution;
import org.apache.ibatis.annotations.Param;

public interface SolutionMapper extends BaseMapper<Solution> {

    /**
     * 修改方案名根据旧名称
     * @param oldName
     * @param newName
     * @return
     */
    int updateByName(@Param("oldname") String oldName, @Param("newname") String newName);

    /**
     * 根据方案名称删除
     * @param name
     * @return
     */
    int deleteByName(String name);
}