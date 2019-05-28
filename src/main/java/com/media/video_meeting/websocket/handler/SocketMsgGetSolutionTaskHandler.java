package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.MyWebSocket;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * 根据方案名称 和 分控用户名获得方案下的任务数据
 *
 * {"id":"getSolutionTask","account":"admin", "solutionname":"夏季作息"}
 *
 * 返回
 * {"id":"getSolutionTaskResponse", "response":"success","account":"admin",
 * "tasklist":[{"solution":"夏季作息",
 * "taskname":"Region",
 * "taskPriority":0,
 * "loopType":1,
 * "stopDate":"2019-06-10",
 * "volume":0,"mp3":["1.mp3","2.mp3"],
 * "startTime":"16:22:53",
 * "duration":1800,"playOrder":1,
 * "users":["1"],"status":0,
 * "taskid":"11","samll":0,
 * "startDate":"2018-12-14","weekMask":254}]}
 *
 * {"id":"getSolutionTaskResponse", "response":"fail","account":"admin"}
 *
 * @Author ken
 * @Date 2019/2/28
 * @Version 1.0
 */
@Component("getSolutionTask")
public class SocketMsgGetSolutionTaskHandler extends SocketMsgHandler {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private MyWebSocket myWebSocket;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        //获得方案对象
        Solution solution = JSON.parseObject(msg, Solution.class);
        List<Task> tasks = taskService.queryBySolution(solution.getSolutionname());

        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"getSolutionTaskResponse\", \"response\":\"success\",\"account\":\"" + solution.getAccount() + "\",\"tasklist\":");
        if(tasks != null){
//            sb.append(JSON.toJSONString(tasks));

            List<Map<String, Object>> list = new ArrayList<>();
            for (Task task : tasks) {
                Map map = new HashMap();
                map.put("solution", task.getSolution());
                map.put("taskname", task.getTaskname());
                map.put("taskPriority", task.getTaskpriority());
                map.put("loopType", task.getLooptype());
                map.put("stopDate", task.getStopDate());
                map.put("volume", task.getVolume());
                map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
                map.put("startTime", task.getStartTime());
                map.put("duration", task.getDuration());
                map.put("playOrder", task.getPlayOrder());
                map.put("users", JSON.parseArray(task.getUsers()));
                map.put("taskid", task.getTaskid());
                map.put("status", task.getStatus());
                map.put("samll", task.getSamll());
                map.put("startDate", task.getStartDate());
                map.put("weekMask", task.getWeekMask());
                list.add(map);
            }
            sb.append(JSON.toJSONString(list));
        } else {
            sb.append("[]");
        }
        sb.append("}");

        //回复消息
        myWebSocket.send(sb.toString());
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {
        String error = "{\"id\":\"getSolutionTaskResponse\", \"response\":\"fail\",\"account\":\"admin\"}";
        myWebSocket.send(error);
    }
}
