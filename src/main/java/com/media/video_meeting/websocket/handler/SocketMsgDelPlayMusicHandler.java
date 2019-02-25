package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 删除音乐任务
 *
 * {"id":"delMusicTask","taskid":"145","account":"admin" }
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("delMusicTask")
public class SocketMsgDelPlayMusicHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String taskid = jsonObject.getString("taskid");

        //删除音乐任务
        taskService.deleteByTaskId(taskid);
    }

    @Override
    public void exception(Throwable t) {

    }
}
