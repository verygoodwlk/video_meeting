package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改音乐任务
 *
 * {"id":"modifyMusicTask","account":"admin", "taskid":"145","taskname":"Region","volume":"8",
 * "mp3":["1.mp3","2.mp3","G.E.M.邓紫棋 - 喜欢你.mp3"] ,"terminal":["1", "2", "3"]}
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("modifyMusicTask")
public class SocketMsgUpdatePlayMusicTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        //新建实时音乐
        Task task = JSON.parseObject(msg, Task.class);
        //终端列表
        JSONArray jsonArrayClient = jsonObject.getJSONArray("terminal");
        task.setUsers(jsonArrayClient.toString());

        taskService.update(task);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
