package com.media.video_meeting.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ken
 * @Time 2018/12/15 15:17
 * @Version 1.0
 */
@Component
public class SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    /**
     * 连接好之后需要初始化的操作
     */
    public void init(){

    }

    /**
     * 处理接收的消息
     * @param msg
     */
    public void handler(String msg){
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String id = jsonObject.getString("id");

        switch (id){
            case "online":
                //设备上线
                ClientMsg clientMsgOnline = JSON.parseObject(msg, ClientMsg.class);
                clientMsgOnline.setStatus(1);
                clientService.insertOrUpdate(clientMsgOnline);
                break;
            case "offline":
                //设备离线
                int userid = jsonObject.getIntValue("response");
                ClientMsg clientMsgOff = new ClientMsg();
                clientMsgOff.setUserid(userid);
                clientMsgOff.setId("offline");
                clientMsgOff.setStatus(0);
                clientService.insertOrUpdate(clientMsgOff);
                break;
             default:
                 break;
        }
    }
}
