package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.SystemLogMapper;
import com.media.video_meeting.entity.SystemLog;
import com.media.video_meeting.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/8 15:31
 * @Version 1.0
 */
@Service
public class SystemLogServiceImpl implements ISystemLogService {


    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int insertLog(SystemLog systemLog) {
        return systemLogMapper.insertSelective(systemLog);
    }

    @Override
    public int deleteLog(Integer id) {
        return systemLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int clearLog() {
        return systemLogMapper.clearLog();
    }

    @Override
    public List<SystemLog> queryAll() {
        return systemLogMapper.queryAll();
    }
}
