package com.media.video_meeting.websocket;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 心跳处理器
 * @Author ken
 * @Time 2018/12/15 16:27
 * @Version 1.0
 */
@Component
public class SocketHeartHandler {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * 心跳机制
     */
    public void heart(){
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 0, );
    }

    /**
     * 停止心跳
     */
    public void stop(){
        executorService.shutdownNow();
    }
}
