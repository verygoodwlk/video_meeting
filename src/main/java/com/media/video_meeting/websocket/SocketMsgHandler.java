package com.media.video_meeting.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理器父类
 * @Author ken
 * @Time 2018/12/15 15:17
 * @Version 1.0
 */
@Slf4j
public abstract class SocketMsgHandler {

    /**
     * 获得接收的消息
     * @param msg
     */
    public void handler(String msg){
        JSONObject jsonObject = JSONObject.parseObject(msg);

        try {
            handlerMsg(msg, jsonObject);
        } catch (Throwable t) {
            log.error("websocket_handle_error:处理websocket数据异常", t);
            exception(t);
        }
    }

    /**
     * 处理接收的消息
     * @param msg
     */
    public abstract void handlerMsg(String msg, JSONObject jsonObject) throws Exception;

    /**
     * 处理相应的异常
     */
    public abstract void exception(Throwable t);
}
