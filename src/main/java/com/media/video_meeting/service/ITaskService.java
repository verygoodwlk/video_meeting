package com.media.video_meeting.service;

import com.media.video_meeting.entity.Task;

import java.util.List;

/**
 * @Author ken
 * @Time 2019/2/25 22:58
 * @Version 1.0
 */
public interface ITaskService {

    int insert(Task task);

    int update(Task task);

    Task updateByTaskId(Task task);

    int deleteByTaskId(String taskid);

    int deleteById(int tid);

    Task copyTask(String taskid);

    List<Task> queryBySolution(String solution);

    List<Task> queryByAccountAndTaskType(String account, int taskt);

    List<Task> queryTimeTaskByAccountAndTaskType(String account, int taskt, String solution);

    Task queryById(int tid);

    Task queryByTaskId(String taskid);
}
