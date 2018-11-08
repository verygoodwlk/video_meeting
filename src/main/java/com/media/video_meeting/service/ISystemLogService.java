package com.media.video_meeting.service;

import com.media.video_meeting.entity.SystemLog;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/8 15:29
 * @Version 1.0
 */
public interface ISystemLogService {

    int insertLog(SystemLog systemLog);

    int deleteLog(Integer id);

    int clearLog();

    List<SystemLog> queryAll();
}
