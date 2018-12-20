package com.media.video_meeting.util;

import com.media.video_meeting.dao.ClientGroupMapper;
import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.websocket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/18 14:11
 * @Version 1.0
 */
@Component
public class GroupUtil {

    @Autowired
    private ClientGroupMapper clientGroupMapper;

    @Autowired
    private MyWebSocket myWebSocket;

    private static final Logger logger = LoggerFactory.getLogger(GroupUtil.class);

    /**
     * 发送群组消息
     */
    public void sendGroupInfo(){
        //组装信息发送
        List<ClientGroup> clientGroups = clientGroupMapper.queryAllGroupJoinClient();

        StringBuilder sb = new StringBuilder("{");
        //编号
        sb.append("\"id\":").append("\"terminalGroup\",");
        //分组
        sb.append("\"groups\":[");

        sb.append(getGroupJson(clientGroups, -1));

        sb.append("]");
        sb.append("}");

        LogUtil.info(logger, "发送的分组消息：" + sb.toString());

        myWebSocket.send(sb.toString());
//        System.out.println("分组json：" + sb.toString());
    }

    private String getGroupJson(List<ClientGroup> clientGroups, Integer pid){

        StringBuilder sb = new StringBuilder();
        for (ClientGroup clientGroup : clientGroups) {
            if(clientGroup.getPid() == pid){

                if(sb.length() != 0){
                    sb.append(",");
                }
                //瓶装一个分组
                sb.append("{");
                sb.append("\"gname\":").append("\"").append(clientGroup.getGname()).append("\",");
                sb.append("\"level\":").append("" + countNum(clientGroups, clientGroup.getId()) + ",");
                sb.append("\"clients\":").append("[");

                //获得分组下的所有
                List<ClientMsg> clientMsgs = clientGroup.getClientMsgs();
                for (int i = 0; i < clientMsgs.size(); i++) {
                    if(i != 0){
                        sb.append(",");
                    }
                    sb.append("\"").append(clientMsgs.get(i).getUserid()).append("\"");
                }

                sb.append("],");

                sb.append("\"groups\":[");
                sb.append(getGroupJson(clientGroups, clientGroup.getId()));
                sb.append("]");

                sb.append("}");
            }
        }

        return sb.toString();
    }

    /**
     * 计算子组的层级
     * @param clientGroups
     * @param id
     * @return
     */
    private int countNum(List<ClientGroup> clientGroups, Integer id){

//        int number = 0;
//        if(clientGroups != null && clientGroups.size() > 0){
//            for (ClientGroup clientGroup : clientGroups) {
//                if(clientGroup.getPid() == id){
//                    number++;
//                    break;
//                }
//            }
//        }
//
//        int n = 0;
//        for (ClientGroup clientGroup : clientGroups) {
//            if(clientGroup.getPid() == id){
//                int m = countNum(clientGroups, clientGroup.getId());
//                n = Math.max(n, m);
//            }
//        }
//        return number + n;

        int number = 0;
        if(clientGroups != null && clientGroups.size() > 0){
            for (ClientGroup clientGroup : clientGroups) {
                if(clientGroup.getId() == id){
                    if(clientGroup.getPid() != -1){
                        number += countNum(clientGroups, clientGroup.getPid());
                    }
                    number++;
                    break;
                }
            }
        }

        return number;
    }
}
