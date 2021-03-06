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
 * 编辑采集任务
 * {"id":"modifyCollectTask","account":"admin", "taskPriority":0,"taskname":"Region","stopDate":"2019-06-10","startTime":"16:22:53","duration":1800,"playOrder":1,"users":["1"],"status":0,"taskid":"11","startDate":"2018-12-14","weekMask":254}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("modifyCollectTask")
public class SocketMsgUpdateCollectionTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Task task = JSON.parseObject(msg, Task.class);
        taskService.update(task);
    }

    @Override
    public void exception(Throwable t) {

    }
}
