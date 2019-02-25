package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.websocket.SocketHeartHandler;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 心跳相应机制
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("ack")
public class SocketMsgHeartHandler extends SocketMsgHandler {

    @Autowired
    private SocketHeartHandler socketHeartHandler;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        socketHeartHandler.response(msg);
    }

    @Override
    public void exception(Throwable t) {

    }
}
