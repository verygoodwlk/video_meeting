package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 45.终端修改id，golang发送给web
 * {"id": "renameid", "oldid":"5", "newid":"3"}
 */
@Component("renameid")
public class SocketMsgRenameidHandler extends SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        clientService.updateId(jsonObject.getInteger("oldid"),  jsonObject.getInteger("newid"));
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
