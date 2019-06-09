package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.util.TaskStatusUtil;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * {"id":"refreshTerminalState","account":"admin", "taskid":"145", "status":"1", "mp3":"1.mp3",  "duration": 90, "users":["1", "2", "3"]}
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/29 23:01
 */
@Component("refreshTerminalState")
public class SocketMsgTaskResponseHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {

        String taskid = jsonObject.getString("taskid");
        int status = jsonObject.getInteger("status");
        String mp3 = jsonObject.getString("mp3");
        int duration = jsonObject.getInteger("duration");
        String startDate = jsonObject.getString("startDate");
        JSONArray users = jsonObject.getJSONArray("users");
        String[] strings = users.toArray(new String[1]);

        //更新数据库
        int result = taskService.updateTaskStatus(taskid, status, startDate);

        //result - 0:普通状态  1:一次性任务结束 - 删除   2:每天任务结束 - 更新开始时间
        TaskStatusUtil.statusTask(taskid, status, mp3, duration, startDate, strings, result);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
