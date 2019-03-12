package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.ISolutionService;
import com.media.video_meeting.service.IWebconService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 执行方案
 * {"id":"startSolutionTask","name":"夏季作息","account":"admin" }
 *
 * @Author ken
 * @Date 2019/3/12
 * @Version 1.0
 */
@Component("startSolutionTask")
public class SocketMsgStartSolutionTaskHandler extends SocketMsgHandler {

    @Autowired
    private ISolutionService solutionService;

    @Autowired
    private IWebconService webconService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        //获得方案名称
        String name = jsonObject.getString("name");
        String account = jsonObject.getString("account");
        //根据名称查询方案
        Solution solution = solutionService.queryByName(name);
        //修改分控的默认方案
        Webcon webcon = webconService.queryByAccount(account);
        webcon.setSolutionid(solution.getId());
        webconService.updateWebcon(webcon);
    }

    @Override
    public void exception(Throwable t) {

    }
}
