package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.IRecordService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除会议记录
 * {"id":"delrecord", "recordname":"1_2018-12-03_14-54-39.webm"}
 */
@Component("delrecord")
public class SocketMsgDelRecordHandler extends SocketMsgHandler {


    @Autowired
    private IRecordService recordService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String recordname = jsonObject.getString("recordname");
        recordService.deleteRecordByName(recordname);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
