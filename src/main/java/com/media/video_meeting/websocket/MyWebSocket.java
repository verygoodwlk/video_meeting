package com.media.video_meeting.websocket;

import com.media.video_meeting.util.LogUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ken
 * @Time 2018/12/15 8:23
 * @Version 1.0
 */
public class MyWebSocket extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService2 = Executors.newFixedThreadPool(5);

    @Autowired
    private SocketMsgHandler socketMsgHandler;
    @Autowired
    private SocketInitHandler socketInitHandler;

    public MyWebSocket(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LogUtil.info(logger, "连接状态：" + isOpen());
        socketInitHandler.init(this);
    }

    @Override
    public void onMessage(String s) {
        LogUtil.info(logger, "接收到消息：" + s);
        //交给线程池处理相关的业务
        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                socketMsgHandler.handler(s);
            }
        });
    }


    @Override
    public void onClose(int i, String s, boolean b) {
        LogUtil.info(logger, "关闭一个连接：" + s);
        again();
    }

    @Override
    public void onError(Exception e) {
        LogUtil.info(logger, "连接发生异常！！" + e.getMessage());
    }

    @PreDestroy
    public void destory(){
        executorService.shutdown();
        executorService2.shutdown();
    }

    /**
     * 重连机制
     */
    public void again(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LogUtil.info(logger, "开始发起重连..." + Thread.currentThread().getName());
                reconnect();
            }
        });
    }
}
