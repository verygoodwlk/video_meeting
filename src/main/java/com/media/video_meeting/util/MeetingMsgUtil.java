package com.media.video_meeting.util;

import com.media.video_meeting.entity.Meeting;
import com.media.video_meeting.websocket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ken
 * @Time 2018/12/18 17:42
 * @Version 1.0
 */
@Component
public class MeetingMsgUtil {

    @Autowired
    private MyWebSocket myWebSocket;

    private static final Logger logger = LoggerFactory.getLogger(GroupUtil.class);

    /**
     * 发送会议创建信息
     * @param meeting
     */
    public void sendCreateMeeting(Meeting meeting){
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        if(meeting.getType().equals(0)){
            //主麦模式
            sb.append("\"id\":\"createOneToMany\",");
        } else {
            //多方讨论模式
            sb.append("\"id\":\"createGroup\",");
            sb.append("\"room\":\"" + meeting.getClient_start() + "\",");
        }

        sb.append("\"name\":\"" + meeting.getClient_start() + "\",");
        sb.append("\"users\":[");
        int j = 0;
        for (int i = 0; i < meeting.getClient_ids().length; i++) {
            Integer client_id = meeting.getClient_ids()[i];
            if(client_id.equals(meeting.getClient_start())){
                continue;
            }

            if(j != 0){
                sb.append(",");
            }

            sb.append("\"").append(client_id).append("\"");
            j++;
        }
        sb.append("]");
        sb.append("}");

        System.out.println("会议的json：" + sb.toString());
        myWebSocket.send(sb.toString());
    }

}
