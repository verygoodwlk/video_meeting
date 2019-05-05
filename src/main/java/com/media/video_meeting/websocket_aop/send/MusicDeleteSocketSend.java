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
        int taskt = (int) objs[1];
        Map<String, Object> map = new HashMap<>();
        switch (taskt){
            case 5:
                //实时音乐
                map.put("id", "delMusicTask");
                break;
            case 1:
                //定时音乐
                map.put("id", "delTask");
                break;
            case 2:
                //定时采集
                map.put("id", "delCollectTask");
                break;
            case 3:
                //报警
                map.put("id", "delFireTask");
                break;
            case 4:
                //语音合成
                map.put("id", "delVoiceTask");
                break;
        }
        map.put("taskid", taskid);
        return map;
    }
}
