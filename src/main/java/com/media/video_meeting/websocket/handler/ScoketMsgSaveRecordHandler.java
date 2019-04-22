package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Record;
import com.media.video_meeting.service.IRecordService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 保存会议录像,host发起方，type会议类型（1.一对一，2.多方讨论，3.主麦模式），record录像文件名，users参与终端
 * {"id":"saveRecord", "host":"1", "type":"1", "starttime":"2019-12-17 23:36:38",
 * "endtime":"2019-12-17 23:36:38", "record":"1_2018-12-03_14-54-39.webm", "users":["1", "3", "7"]}
 */
@Component("saveRecord")
public class ScoketMsgSaveRecordHandler extends SocketMsgHandler {

    @Autowired
    private IRecordService recordService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Record record = JSON.parseObject(msg, Record.class);
        recordService.insertRecord(record);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
