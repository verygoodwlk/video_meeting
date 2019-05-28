package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.TaskMapper;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Task updateByTaskId(Task task) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("taskid", task.getTaskid());
        taskMapper.update(task, queryWrapper);
        return this.queryByTaskId(task.getTaskid());
    }

    @Override
    public int deleteByTaskId(String taskid) {
        return taskMapper.deleteByTaskId(taskid);
    }

    @Override
    public int deleteById(int tid) {
        return taskMapper.deleteById(tid);
    }

    /**
     * 拷贝任务
     * @return
     */
    @Override
    public Task copyTask(String taskid) {
        Task task = queryByTaskId(taskid);
        if(task != null){
            task.setTaskid(UUID.randomUUID().toString());
            insert(task);
        }
        return task;
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
        if(account != null){
            queryWrapper.eq("account", account);
        }
        queryWrapper.eq("taskt", taskt);
        return taskMapper.selectList(queryWrapper);
    }

    @Override
    public List<Task> queryTimeTaskByAccountAndTaskType(String account, int taskt, String solution) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.eq("taskt", taskt);
        queryWrapper.eq("solution", StringUtil.isNotEmpty(solution) ? solution : "Default Solution");
        return taskMapper.selectList(queryWrapper);
    }

    /**
     * 根据id查询任务
     * @param tid
     * @return
     */
    @Override
    public Task queryById(int tid) {
        Task task = taskMapper.selectById(tid);
        return task;
    }

    /**
     * 根据任务
     * @param taskid
     * @return
     */
    @Override
    public Task queryByTaskId(String taskid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("taskid", taskid);
        return taskMapper.selectOne(queryWrapper);
    }
}
