package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.util.TaskStatusUtil;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.stereotype.Component;

/**
 *
 * {"id":"refreshTerminalState","account":"admin",
 * "taskid":"f7f51e05-9eea-4ed5-916a-857a318f7daa","status":1,"mp3":"赵雷-我们的时光.mp3","users":["18"]}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/29 23:01
 */
@Component("refreshTerminalState")
public class SocketMsgTaskResponseHandler extends SocketMsgHandler {
    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {

        String taskid = jsonObject.getString("taskid");
        int status = jsonObject.getInteger("status");
        String mp3 = jsonObject.getString("mp3");

        TaskStatusUtil.statusTask(taskid, status, mp3);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
