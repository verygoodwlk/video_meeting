package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 实时音乐,复制任务
 *
 * {"id":"copyMusicTask","account":"admin", "taskid":"146", "taskname":"Region","volume":"8",
 * "mp3":["1.mp3","2.mp3","G.E.M.邓紫棋 - 喜欢你.mp3"] ,"terminal":["1", "2", "3"]}
 *
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("copyMusicTask")
public class SocketMsgCopyPlayMusicHandler extends SocketMsgHandler {

    @Autowired
    private SocketMsgPlayMusicTaskHandler socketMsgPlayMusicTaskHandler;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        socketMsgPlayMusicTaskHandler.handlerMsg(msg, jsonObject);
    }

    @Override
    public void exception(Throwable t) {
        socketMsgPlayMusicTaskHandler.exception(t);
    }
}
