package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.util.ClientStatusUtil;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * //被监听端,终端状态status（开始 start, 停止stop）， 设备terminal
 * {"id":"monitored", "status":"stop", "terminal":"1"}
 * 返回
 * {"id":"monitoredResponse","response":"success"}
 * {"id":"monitoredResponse","response":"fail"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/22 21:11
 */
public class MonitoredSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Integer userid = (Integer) objs[0];

        Map map = new HashMap();
        map.put("id", "monitored");
        map.put("status", ClientStatusUtil.setByListener(userid));
        map.put("terminal", userid + "");

        return map;
    }
}
