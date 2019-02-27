package com.media.video_meeting.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.media.video_meeting.entity.Task;

public interface TaskMapper extends BaseMapper<Task> {

    int deleteByTaskId(String task);
}