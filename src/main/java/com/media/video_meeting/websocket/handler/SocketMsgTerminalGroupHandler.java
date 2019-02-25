package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.util.GroupUtil;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获得分组信息处理器
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("getTerminalGroup")
public class SocketMsgTerminalGroupHandler extends SocketMsgHandler {

    @Autowired
    private GroupUtil groupUtil;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        //发送分组信息
        groupUtil.sendGroupInfo();
    }

    @Override
    public void exception(Throwable t) {

    }
}
