package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 复制报警任务
 *
 * {"id":"copyFireTask","account":"admin","taskid":"145", "taskname":"火警", "area":"A区", "fireTerminal":"1","port":"1","isLevel":"0","mp3":["1.mp3","2.mp3"],"isExternalMusic":"1","externalFireTerminal":"10","users":["1"]}
 *
 * @Author ken
 * @Date 2019/2/26
 * @Version 1.0
 */
@Component("copyFireTask")
public class SocketMsgCopyFireTaskHandler extends SocketMsgHandler {

    @Autowired
    private SocketMsgAddFireTaskHandler socketMsgAddFireTaskHandler;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        socketMsgAddFireTaskHandler.handlerMsg(msg, jsonObject);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
