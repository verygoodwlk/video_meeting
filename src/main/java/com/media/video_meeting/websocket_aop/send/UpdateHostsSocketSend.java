package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * 57.设置一键对讲主机
 * {"id":"talkhost", "terminal":"16"}
 * 返回
 * {"id":"talkhostResponse","response":"success"}
 * {"id":"talkhostResponse","response":"fail"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/7/30 17:28
 */
public class UpdateHostsSocketSend extends ISocketSend {

    @Override
    public Map<String, Object> sendMsg(Object... objs) {
        Integer cid = (Integer) objs[0];
        Map<String, Object> map = new HashMap<>();
        map.put("id", "talkhost");
        map.put("terminal", cid + "");
        return map;
    }

}
