package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Record;
import com.media.video_meeting.service.IRecordService;
import com.media.video_meeting.websocket.MyWebSocket;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取会议记录，返回terminal参与过的会议记录
 * {"id":"getRecordlist", "terminal":"1"}
 * 返回
 * {"id":"recordlist", "list":[{"host":"1", "starttime":"2019-12-17 23:36:38", "endtime":"2019-12-17 23:36:38", "record":"1_2018-12-03_14-54-39.webm", "users":["1", "3", "7"]}] }
 * 失败
 * {"id":"recordlist", "list":[ ] }
 */
@Component("getRecordlist")
public class SocketMsgGetRecordlistHandler extends SocketMsgHandler {

    @Autowired
    private IRecordService recordService;

    @Autowired
    private MyWebSocket webSocket;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Integer cid = jsonObject.getInteger("terminal");
        List<Record> records = recordService.queryByCid(cid);

        Map map = new HashMap();
        map.put("id", "recordlist");
        map.put("terminal", cid);
        map.put("list", records);

        System.out.println("发送的接送：" + JSON.toJSONString(map));
        webSocket.send(JSON.toJSONString(map));
    }


    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {
        Integer cid = jsonObject.getInteger("terminal");
        Map map = new HashMap();
        map.put("id", "recordlist");
        map.put("terminal", cid);
        map.put("list", "[]");

        webSocket.send(JSON.toJSONString(map));
    }
}
