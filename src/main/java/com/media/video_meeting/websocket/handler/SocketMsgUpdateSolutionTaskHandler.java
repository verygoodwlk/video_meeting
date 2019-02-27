package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 修改方案名
 * {"id":"modifySolutionTask","account":"admin", "oldname":"夏季作息", "newname":"冬季作息"}
 *
 * @Author ken
 * @Date 2019/2/27
 * @Version 1.0
 */
@Component("modifySolutionTask")
public class SocketMsgUpdateSolutionTaskHandler extends SocketMsgHandler {

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String oldname = jsonObject.getString("oldname");
        String newname = jsonObject.getString("newname");
        solutionMapper.updateByName(oldname, newname);
    }

    @Override
    public void exception(Throwable t) {

    }
}
