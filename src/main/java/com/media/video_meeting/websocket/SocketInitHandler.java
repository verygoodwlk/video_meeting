package com.media.video_meeting.websocket;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.util.LogUtil;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/15 15:58
 * @Version 1.0
 */
@Component
public class SocketInitHandler {

    private static final Logger logger = LoggerFactory.getLogger(SocketInitHandler.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private SocketHeartHandler socketHeartHandler;

    /**
     * 初始化连接的操作
     * @param webSocketClient
     */
    public void init(WebSocketClient webSocketClient){
        //注册消息
        LogUtil.info(logger, "发送注册消息....");
        webSocketClient.send("{\"id\":\"register\",\"name\":\"webserver\"}");
        //发送终端列表
        LogUtil.info(logger, "首次发送终端列表....");
        List<ClientMsg> clientMsgs = clientService.queryPage();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"alluser\", \"users\":[");
        if(clientMsgs != null && clientMsgs.size() > 0){
            for (int i = 0; i < clientMsgs.size(); i++) {
                if(i != 0){
                    sb.append(",");
                }
                sb.append("\"").append(clientMsgs.get(i).getUserid()).append("\"");
            }
        }
        sb.append("]}");
        System.out.println("发送的终端列表：" + sb.toString());
        webSocketClient.send(sb.toString());
    }
}
