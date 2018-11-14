package com.media.video_meeting.service;

import com.media.video_meeting.entity.SearchLog;
import com.media.video_meeting.entity.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/8 15:29
 * @Version 1.0
 */
public interface ISystemLogService {

    int insertLog(SystemLog systemLog);

    int deleteLog(Integer id);

    int deleteLogs(Integer[] ids);

    int clearLog();

    List<SystemLog> queryAll();

    List<SystemLog> searchLog(@Param("searchLog") SearchLog searchLog);
}
