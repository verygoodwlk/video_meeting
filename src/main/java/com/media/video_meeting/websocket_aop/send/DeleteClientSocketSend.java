package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * 11.删除终端
 * {"id":"delTerminal", "terminal":"5" , "account":"admin"}
 * 返回
 * {"id":"delTerminalResponse","response":"success", "account":"admin"}
 * {"id":"delTerminalResponse","response":"fail", "account":"admin"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/22 23:42
 */
public class DeleteClientSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Integer userid = (Integer) objs[0];
        Map<String, Object> map = new HashMap<>();
        map.put("id", "delTerminal");
        map.put("terminal", userid + "");
        return map;
    }
}
