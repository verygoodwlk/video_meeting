package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时音乐 新建任务
 *
 * {"id":"addTask", "account":"admin", "solution":"Default Solution",
 * "taskid":"145", "taskname":"Region","taskPriority":0,"loopType":1,
 * "stopDate":"2019-06-10","volume":0,"mp3":["1.mp3","2.mp3"],"startTime":"16:22:53",
 * "duration":1800,"playOrder":1,"users":["1"],"status":0,"samll":0,
 * "startDate":"2018-12-14","weekMask":254}
 *
 * solution归属方案，taskPriority优先级，loopType循环类型，stopDate结束日期，
 * 音量stopDate， 开始时间startTime，持续时间duration， playOrder任务类型，
 * status任务状态，taskid任务id，samll音量微调，开始日期startDate，星期选择weekMask
 *
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("addTask")
public class SocketMsgAddTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        Task task = JSON.parseObject(msg, Task.class);
        taskService.insert(task);
    }

    @Override
    public void exception(Throwable t) {

    }
}
