package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 终端上线请求
 *
 * {"id":"online","userid":"1","terminalname":"1","mac":"00:90:4C:11:22:33",
 * "serverip":"192.168.1.49","ip":"192.168.1.29","volume":"5",
 * "intercomEnable":"1","cameraIntercomEnable":"1",
 * "updateStatus":"1","dhcpStatus":"1","productsModel":"803"}
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("online")
public class SocketMsgOnLineHandler extends SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    /**
     * 处理消息
     * @param msg
     */
    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        //设备上线
        ClientMsg clientMsgOnline = JSON.parseObject(msg, ClientMsg.class);
        clientMsgOnline.setId("online");
        clientMsgOnline.setStatus(1);

        System.out.println("上线新的终端：" + clientMsgOnline);
        clientService.insertOrUpdate(clientMsgOnline);
    }

    /**
     * 处理异常
     * @param t
     */
    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
