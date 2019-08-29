package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.DeviceStatus;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.util.PushUtil;
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

    @Autowired
    private PushUtil pushUtil;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        //同步终端状态
        JSONArray users = jsonObject.getJSONArray("users");

        if(users != null){
            for (int i = 0; i < users.size(); i++){
                JSONObject jsonObject1 = users.getJSONObject(i);

                try{
                    Integer id = jsonObject1.getInteger("id");
                } catch (Exception e){
                    continue;
                }


                ClientMsg cmsg = new ClientMsg();
                cmsg.setUserid(jsonObject1.getInteger("id"));
                cmsg.setId(jsonObject1.getInteger("online") == 1 ? "online" : "offline");
                cmsg.setStatus(jsonObject1.getInteger("online"));
                int result = clientService.insertOrUpdate(cmsg);

                System.out.println("---->处理终端：" + users.getJSONObject(i).toJSONString() + "  结果：" + result);
            }
        }

        //TODO 调用第三方接口 - 同步终端状态
        DeviceStatus deviceStatus = clientService.queryDeviceStatus();
        pushUtil.pushDeviceStatus(deviceStatus);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
