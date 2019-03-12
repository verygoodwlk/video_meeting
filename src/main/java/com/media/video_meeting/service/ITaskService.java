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

    int deleteByTaskId(String taskid);

    List<Task> queryBySolution(String solution);

    List<Task> queryByAccountAndTaskType(String account, int taskt);
}
