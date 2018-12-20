package com.media.video_meeting.util;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.websocket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ken
 * @Time 2018/12/19 22:08
 * @Version 1.0
 */
@Component
public class ClientUtil {

    @Autowired
    private MyWebSocket myWebSocket;

    private static final Logger logger = LoggerFactory.getLogger(ClientUtil.class);

    /**
     * 发送终端名称修改信息
     * {"id":"terminalRename","userid":"2","terminalname":"2班"}
     * @param clientMsg
     */
    public void sendUpdateName(ClientMsg clientMsg){

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"terminalRename\",");
        sb.append("\"userid\":\"").append(clientMsg.getUserid()).append("\",");
        sb.append("\"terminalname\":\"").append(clientMsg.getTerminalname()).append("\"");
        sb.append("}");

        LogUtil.info(logger, "发送修改终端名称的消息：" + sb.toString());

        myWebSocket.send(sb.toString());
    }
}
