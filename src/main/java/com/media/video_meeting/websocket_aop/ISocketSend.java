package com.media.video_meeting.websocket_aop;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.Task;

import java.util.HashMap;
import java.util.Map;

public abstract class ISocketSend {

    public abstract Map<String, Object> sendMsg(Object... objs);

    /**
     * 将任务对象转换成Map集合
     * @param task
     * @return
     */
    protected Map<String, Object> task2Map(Task task){
        Map<String, Object> map = new HashMap<>();
        map.put("account", task.getAccount());
        map.put("taskid", task.getTaskid());
        map.put("taskname", task.getTaskname());
        map.put("loopType", task.getLooptype());
        map.put("volume", task.getVolume());
        map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
        map.put("terminal", JSON.parseArray(task.getUsers(), Integer.class));
        return map;
    }
}
