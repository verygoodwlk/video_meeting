package com.media.video_meeting.listener;

import com.media.video_meeting.util.LogUtil;
import com.media.video_meeting.websocket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author ken
 * @Time 2018/11/21 16:50
 * @Version 1.0
 */
@Component
public class ContextInitListener implements CommandLineRunner {

    @Autowired
    private MyWebSocket myWebSocket;

    private static final Logger logger = LoggerFactory.getLogger(ContextInitListener.class);

    @Override
    public void run(String... args) throws Exception {
        LogUtil.info(logger, "开始连接golang服务器....");
        myWebSocket.connect();
    }
}
