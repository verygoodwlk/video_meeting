package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.MyWebSocket;
import com.media.video_meeting.websocket.SocketMsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除终端信息
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("delTerminal")
@Slf4j
public class SocketMsgRemoveTerminal extends SocketMsgHandler {

    @Autowired
    private IClientService clientService;

    @Autowired
    private MyWebSocket myWebSocket;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject)  {
        String cid = jsonObject.getString("terminal");
        int result = clientService.deleteClientByCid(cid);
        if(result > 0){
            //成功
            myWebSocket.send("{\"id\":\"delTerminalResponse\",\"response\":\"success\", \"account\":\"admin\"}");
        }
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {
        log.error("删除终端异常", t);
        //异常方法
        myWebSocket.send("{\"id\":\"delTerminalResponse\",\"response\":\"fail\", \"account\":\"admin\"}");
    }
}
