package com.media.video_meeting.controller;

import com.media.video_meeting.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ken
 * @Time 2018/12/15 11:06
 * @Version 1.0
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    public MyWebSocket myWebSocket;

    @RequestMapping("/isconnection")
    @ResponseBody
    public boolean isConnect(){
        return myWebSocket.isOpen();
    }
}
