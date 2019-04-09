package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除实时音乐
 * {"id":"delMusicTask","taskid":"145","account":"admin"}
 */
public class MusicDeleteSocketSend extends ISocketSend {

    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        String taskid = (String) objs[0];
        Map<String, Object> map = new HashMap<>();
        map.put("id", "delMusicTask");
        map.put("taskid", taskid);
        return map;
    }
}
