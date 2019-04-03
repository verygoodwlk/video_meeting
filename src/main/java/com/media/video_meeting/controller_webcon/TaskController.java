package com.media.video_meeting.controller_webcon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

@Controller
@RequestMapping("/web/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private MyWebSocket myWebSocket;

    /**
     * 添加任务 - 任务类型 1 - 定时音乐 2 - 定时采集 3 - 消防报警 4 - 语音合成 5 - 实时音乐
     * @param task
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Task insert(Task task, @SessionAttribute("account") Webcon webcon){
        task.setTaskid(UUID.randomUUID().toString());
        task.setAccount(webcon.getAccount());
        System.out.println("添加任务：" + task);
        taskService.insert(task);

        //发送websocket
        if(myWebSocket.isOpen()){
            Map<String, Object> map = new HashMap<>();
            map.put("id", "playMusicTask");
            map.put("account", task.getAccount());
            map.put("taskname", task.getTaskname());
            map.put("loopType", task.getLooptype());
            map.put("volume", task.getVolume());
            map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
            map.put("terminal", JSON.parseArray(task.getUsers(), Integer.class));
            myWebSocket.send(JSON.toJSONString(map));
        }

        return task;
    }

    /**
     * 任务列表 - taskt任务类型， webcon谁的任务
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Task> list(int taskt, @SessionAttribute("account") Webcon webcon){
        List<Task> tasks = taskService.queryByAccountAndTaskType(webcon.getAccount(), taskt);
        return tasks;
    }

    /**
     * 删除任务
     * @param tid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int delete(int tid){
        int result = taskService.deleteById(tid);
        return result;
    }

    /**
     * 获得任务信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/info")
    public Map<String, List<?>> getTaskInfo(int tid){

        Map<String, List<?>> resultMap = new HashMap<>();


        //查询任务
        Task task = taskService.queryById(tid);
        //根据任务获得音乐列表
        String mp3 = task.getMp3();
        resultMap.put("mp3", mp3 == null ? null : JSONArray.parseArray(mp3, String.class));

        //根据任务获得终端列表
        List<ClientMsg> clientMsgs = new ArrayList<>();
        String clients = task.getUsers();
        if(clients != null){
            List<Integer> cids = JSONArray.parseArray(clients, Integer.class);
            for (Integer cid : cids) {
                ClientMsg clientMsg = clientService.queryById(cid);
                clientMsgs.add(clientMsg);
            }
            resultMap.put("clients", clientMsgs);
        } else {
            resultMap.put("clients", null);
        }

        System.out.println("获得任务信息：" + resultMap);

        return resultMap;
    }
}
