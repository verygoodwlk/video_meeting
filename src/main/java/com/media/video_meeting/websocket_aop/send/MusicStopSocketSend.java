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
        map.put("id", "stopMusicTask");
        return map;
    }
}
