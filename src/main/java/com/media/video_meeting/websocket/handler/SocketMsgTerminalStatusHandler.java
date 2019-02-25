package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 终端状态同步处理
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("terminalStatusResponse")
public class SocketMsgTerminalStatusHandler extends SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        //同步终端状态
        JSONArray users = jsonObject.getJSONArray("users");

        if(users != null){
            for (int i = 0; i < users.size(); i++){
                JSONObject jsonObject1 = users.getJSONObject(i);
                ClientMsg cmsg = new ClientMsg();
                cmsg.setUserid(jsonObject1.getInteger("id"));
                cmsg.setId(jsonObject1.getInteger("online") == 1 ? "online" : "offline");
                cmsg.setStatus(jsonObject1.getInteger("online"));
                clientService.insertOrUpdate(cmsg);
            }
        }
    }

    @Override
    public void exception(Throwable t) {

    }
}
