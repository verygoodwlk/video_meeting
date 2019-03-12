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
 * 添加语音合成任务
 *
 * {"id":"addVoiceTask","account":"admin", "taskid":"145", "info":"欢迎光临", "reporter":"1", "speed":"50", "loopNum":"1", "taskname":"Region","taskPriority":0,"taskType":1,"stopDate":"2019-06-10","startTime":"16:22:53","users":["1"],"startDate":"2018-12-14","weekMask":254, "status":"0"}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("addVoiceTask")
public class SocketMsgAddVoiceTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Task task = JSON.parseObject(msg, Task.class);
        task.setTaskt(4);//语音合成
        taskService.insert(task);
    }

    @Override
    public void exception(Throwable t) {

    }
}
