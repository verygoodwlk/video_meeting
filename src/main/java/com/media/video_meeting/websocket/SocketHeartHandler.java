package com.media.video_meeting.websocket;

import com.media.video_meeting.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 心跳处理器
 * @Author ken
 * @Time 2018/12/15 16:27
 * @Version 1.0
 */
@Component
public class SocketHeartHandler {

    private static final Logger logger = LoggerFactory.getLogger(SocketHeartHandler.class);

    private ScheduledExecutorService executorService;
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 心跳机制 - 10S一次心跳
     */
    public void heart(MyWebSocket webSocket){
        if(executorService == null){
            executorService = Executors.newSingleThreadScheduledExecutor();
        }

        LogUtil.info(logger, "开始发送心跳消息...");
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int andIncrement = count.incrementAndGet();
//                LogUtil.info(logger, "开始发送心跳消息..." + andIncrement);
                webSocket.send("{\"id\":\"ack\",\"name\":\"1\"}");
                if (andIncrement >= 2 && !webSocket.isClosed()) {
                    LogUtil.info(logger, "心跳响应超时，客户端断开连接...");
                    count.set(0);
                    webSocket.close();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    /**
     * 心跳消息回应
     */
    public void response(String msg){
        //计算器减1
        count.decrementAndGet();
    }

    /**
     * 停止心跳
     */
    @PreDestroy
    public void stop(){
        if(executorService != null && !executorService.isShutdown()){
            LogUtil.info(logger, "停止心跳发送...");
            executorService.shutdown();
            executorService = null;
        }
    }
}
