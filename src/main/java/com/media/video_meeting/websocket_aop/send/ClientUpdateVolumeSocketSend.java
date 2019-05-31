package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * 13.音量调节
 * {"id":"volume", "num":"16","terminal":["1", "2", "3"]}
 * 返回
 * {"id":"volumeResponse","response":"success"}
 * {"id":"volumeResponse","response":"fail"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/29 21:30
 */
public class ClientUpdateVolumeSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {
        String[] uids = (String[]) objs[0];
        int volume = (int) objs[1];

        Map<String, Object> map = new HashMap<>();
        map.put("id", "volume");
        map.put("num", volume + "");
        map.put("terminal", uids);

        return map;
    }
}
