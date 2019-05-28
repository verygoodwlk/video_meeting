package com.media.video_meeting.websocket_aop;

import com.media.video_meeting.entity.Task;
import com.media.video_meeting.util.TaskUtil;

import java.util.Map;

public abstract class ISocketSend {

    public abstract Map<String, Object> sendMsg(Object... objs);


    //任务类型 1 - 定时音乐 2 - 定时采集 3 - 消防报警 4 - 语音合成 5 - 实时音乐
    protected Map<String, Object> task2Map(Task task){
        return TaskUtil.task2Map(task);
    }
}
