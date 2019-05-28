package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.util.ClientStatusUtil;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * //监听端（开始 start, 停止stop）
 * {"id":"monitor", "status":"start", "terminal":"10"}
 * 返回
 * {"id":"monitorResponse","response":"success"}
 * {"id":"monitorResponse","response":"fail"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/22 21:11
 */
public class MonitorSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Integer userid = (Integer) objs[0];

        Map map = new HashMap();
        map.put("id", "monitor");
        map.put("status", ClientStatusUtil.setListener(userid));
        map.put("terminal", userid + "");

        return map;
    }
}
