package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * {"id":"modifyTerminalConnection", "ipaddress":"192.168.1.186", "users":["13","16","17"]}
 *
 * @version 1.0
 * @user ken
 * @date 2019/6/2 23:59
 */
public class ClientIpSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        String ip = (String) objs[0];
        String[] userids = (String[]) objs[1];

        Map<String, Object> map = new HashMap<>();
        map.put("id", "modifyTerminalConnection");
        map.put("ipaddress", ip);
        map.put("users", userids);

        return map;
    }
}
