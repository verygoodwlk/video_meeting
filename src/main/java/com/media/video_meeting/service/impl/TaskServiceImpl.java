package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.TaskMapper;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int insert(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public int update(Task task) {
        return taskMapper.updateById(task);
    }

    @Override
    public int deleteByTaskId(String taskid) {
        return taskMapper.deleteByTaskId(taskid);
    }
}
