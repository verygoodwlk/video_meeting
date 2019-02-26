package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消防报警 添加任务
 *
 * {"id":"addFireTask","account":"admin", "taskid":"145","taskname":"火警",
 * "area":"A区", "fireTerminal":"1","port":"1",
 * "isLevel":"0","mp3":["1.mp3","2.mp3"],"isExternalMusic":"1",
 * "externalFireTerminal":"10","users":["1"]}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("addFireTask")
public class SocketMsgAddFireTaskHandler extends SocketMsgHandler {

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
