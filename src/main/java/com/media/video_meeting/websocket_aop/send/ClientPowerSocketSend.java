package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 52. 网页发送修改的权限给golang,
 * 视频对讲权限 = 2,
 * 语音对讲权限 = 4，
 * 自由模式视频权限 = 8
 * 自由模式语音权限 = 16,
 * 主麦模式视频权限 = 32,
 * 主麦模式语音权限 = 64,
 * {"id":"terminalLimits","terminal":"1", "limits":"14"}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/18 15:14
 */
public class ClientPowerSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Integer userid = (Integer) objs[0];

        int n = 0;
        if(objs.length > 1){
            Integer[] limits = (Integer[]) objs[1];
            for (Integer limit : limits) {
                n += limit;
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("id", "terminalLimits");
        map.put("terminal", userid);
        map.put("limits", n);
        return map;
    }
}
