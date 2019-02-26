package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 音乐任务拷贝
 *
 * {"id":"copyTask", "account":"admin", "solution":"Default Solution",
 * "taskname":"Region", "taskPriority":0,"loopType":1,
 * "stopDate":"","volume":0,"mp3":["1.mp3","2.mp3"],"startTime":"16:22:53",
 * "duration":1800,"playOrder":1,
 * "users":["1"],"status":0,"taskid":"11","samll":0,"startDate":"2018-12-14","weekMask":254}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("copyTask")
public class SocketMsgCopyTaskHandler extends SocketMsgHandler {

    @Autowired
    private SocketMsgAddTaskHandler socketMsgAddTaskHandler;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        socketMsgAddTaskHandler.handlerMsg(msg, jsonObject);
    }

    @Override
    public void exception(Throwable t) {

    }
}
