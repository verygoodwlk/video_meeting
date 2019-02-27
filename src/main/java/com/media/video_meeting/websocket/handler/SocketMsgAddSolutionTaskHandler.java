package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 添加方案
 * {"id":"addSolutionTask","account":"admin", "solutionname":"夏季作息"}
 *
 * @Author ken
 * @Date 2019/2/27
 * @Version 1.0
 */
@Component("addSolutionTask")
public class SocketMsgAddSolutionTaskHandler extends SocketMsgHandler {

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Solution solution = JSON.parseObject(msg, Solution.class);
        solutionMapper.insert(solution);
    }

    @Override
    public void exception(Throwable t) {

    }
}
