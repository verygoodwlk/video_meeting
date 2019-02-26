package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 新建定时采集的任务
 *
 * {"id":"addCollectTask","account":"admin","taskid":"145",
 * "taskPriority":0,"taskname":"Region","stopDate":"2019-06-10",
 * "startTime":"16:22:53","duration":1800,"playOrder":1,
 * "users":["1"],"status":0,"startDate":"2018-12-14","weekMask":254}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("addCollectTask")
public class SocketMsgAddCollectionTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Task task = JSON.parseObject(msg, Task.class);
        taskService.insert(task);
    }

    @Override
    public void exception(Throwable t) {

    }
}
