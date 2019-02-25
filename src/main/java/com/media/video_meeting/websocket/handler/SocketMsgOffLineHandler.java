package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 终端离线通知
 *
 * {"id":"offline","response":"1"}
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("offline")
public class SocketMsgOffLineHandler extends SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        //设备离线
        int userid = jsonObject.getIntValue("response");
        ClientMsg clientMsgOff = new ClientMsg();
        clientMsgOff.setUserid(userid);
        clientMsgOff.setId("offline");
        clientMsgOff.setStatus(0);
        clientService.insertOrUpdate(clientMsgOff);
    }

    @Override
    public void exception(Throwable t) {

    }
}
