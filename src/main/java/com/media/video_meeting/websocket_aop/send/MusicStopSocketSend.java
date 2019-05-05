package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.entity.Task;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.Map;

/**
 * 实时音乐,手动停止
 * {"id":"stopMusicTask","taskid":"145", "taskname":"Region","volume":"8",
 * "loopType":1, "mp3":["1.mp3","2.mp3","G.E.M.邓紫棋 - 喜欢你.mp3"] ,"terminal":["1", "2", "3"]}
 */
public class MusicStopSocketSend extends ISocketSend {

    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Task task = (Task) objs[0];
        Map<String, Object> map = task2Map(task);
        switch (task.getTaskt()){
            case 5:
                //实时音乐
                map.put("id", "stopMusicTask");
                break;
            case 1:
                //定时音乐
                map.put("id", "stopTask");
                break;
            case 2:
                //定时采集
                map.put("id", "stopCollectTask");
                break;
            case 3:
                map.put("id", "stopFireTask");
                break;
            case 4:
                map.put("id", "stopVoiceTask");
                break;
        }

        return map;
    }
}
