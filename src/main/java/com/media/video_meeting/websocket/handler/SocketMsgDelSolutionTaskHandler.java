package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 删除方案
 * {"id":"delSolutionTask","name":"夏季作息","account":"admin" }
 *
 * @Author ken
 * @Date 2019/2/27
 * @Version 1.0
 */
@Component("delSolutionTask")
public class SocketMsgDelSolutionTaskHandler extends SocketMsgHandler {

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String name = jsonObject.getString("name");
        solutionMapper.deleteByName(name);
    }

    @Override
    public void exception(Throwable t) {

    }
}
