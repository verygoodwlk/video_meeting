package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.Map;

/**
 * 26.执行方案
 * {"id":"startSolutionTask","name":"夏季作息","account":"admin"}
 * 返回
 * {"id":"startSolutionTaskResponse", "response":"success","account":"admin"}
 * {"id":"startSolutionTaskResponse","response":"fail","account":"admin"}
 */
public class StartSolutionTaskSocketSend extends ISocketSend {

    @Override
    public Map<String, Object> sendMsg(Object... objs) {
        Solution solution = (Solution) objs[0];

        Map<String, Object> map = new HashMap<>();
        map.put("id", "startSolutionTask");
        map.put("name", solution.getSolutionname());

        return map;
    }
}
