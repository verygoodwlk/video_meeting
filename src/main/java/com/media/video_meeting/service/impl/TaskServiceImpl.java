package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.TaskMapper;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 根据方案名称查询所有任务
     * @param solution
     * @return
     */
    @Override
    public List<Task> queryBySolution(String solution) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("solution", solution);
        return taskMapper.selectList(queryWrapper);
    }

    /**
     * 根据账号名称
     * @param account
     * @return
     */
    @Override
    public List<Task> queryByAccountAndTaskType(String account, int taskt) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.eq("taskt", taskt);
        return taskMapper.selectList(queryWrapper);
    }
}
