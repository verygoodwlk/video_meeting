package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除定时任务
 *
 * {"id":"delTask","taskid":"11","account":"admin", }
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("delTask")
public class SocketMsgDelTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String taskid = jsonObject.getString("taskid");
        taskService.deleteByTaskId(taskid);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
