package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.util.FenKongUtil;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 分控登录处理
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("webconLogin")
public class SocketMsgWebconLoginHandler extends SocketMsgHandler {

    @Autowired
    private FenKongUtil fenKongUtil;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {
        fenKongUtil.sendWebconLoginResponse();
    }

    @Override
    public void exception(Throwable t) {

    }
}
