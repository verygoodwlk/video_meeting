package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.util.TaskStatusUtil;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 添加任务的
 * {"id":"addTaskResponse","response":"success","account":"admin","taskid":"091e0d16-0027-4c0a-abed-b0d1f69d3566","duration":2}
 *
 * @version 1.0
 * @user ken
 * @date 2019/6/2 0:20
 */
@Component("addTaskResponse")
public class SocketMsgAddTaskResponseHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        String response = jsonObject.getString("response");
        if (response.equals("success")){
            //成功了
            String taskid = jsonObject.getString("taskid");
            int duration = jsonObject.getInteger("duration");

            //修改任务的持续时间
            int result = taskService.updateTaskDuration(taskid, duration);
            if(result > 0){
                //任务总持续时间更新完毕
                TaskStatusUtil.durationTask(taskid, duration);
            }
        }
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }
}
