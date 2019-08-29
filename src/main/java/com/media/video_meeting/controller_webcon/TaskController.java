package com.media.video_meeting.controller_webcon;

import com.alibaba.fastjson.JSONArray;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.util.TaskStatusUtil;
import com.media.video_meeting.websocket.MyWebSocket;
import com.media.video_meeting.websocket_aop.SocketSend;
import com.media.video_meeting.websocket_aop.send.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private TaskStatusUtil taskStatusUtil;

    /**
     * 添加任务 - 任务类型 1 - 定时音乐 2 - 定时采集 3 - 消防报警 4 - 语音合成 5 - 实时音乐
     * @param task
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @SocketSend(params = "#result", sendClass = MusicAddSocketSend.class)
    public Task insert(Task task, @SessionAttribute("account") Webcon webcon){
        task.setTaskid(UUID.randomUUID().toString());
        task.setAccount(webcon.getAccount());
        System.out.println("添加任务：" + task);
        int result = taskService.insert(task);
        System.out.println("添加的结果：" + result);
        return task;
    }

    @ResponseBody
    @RequestMapping("/update")
    @SocketSend(params = "#result", sendClass = MusicUpdateSocketSend.class)
    public Task update(Task task){
        System.out.println("修改任务：" + task);
        Task tasks = taskService.updateByTaskId(task);
        System.out.println("修改的结果：" + tasks);
        return tasks;
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
     * 定时任务列表 - taskt任务类型， webcon谁的任务 solution方案
     * @return
     */
    @ResponseBody
    @RequestMapping("/timelist")
    public List<Task> timelist(int taskt, @SessionAttribute("account") Webcon webcon, String solution){
        List<Task> tasks = taskService.queryTimeTaskByAccountAndTaskType(webcon.getAccount(), taskt, solution);
        return tasks;
    }

    /**
     * 根据任务id查询任务
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryTask")
    public Task queryTask(String taskid){
        Task task = taskService.queryByTaskId(taskid);
        return task;
    }

    /**
     * 删除任务
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    @SocketSend(params = {"#taskid", "#taskt"}, sendClass = MusicDeleteSocketSend.class)
    public int delete(String taskid, int taskt){
        int result = taskService.deleteByTaskId(taskid);
        return result;
    }

    /**
     * 拷贝任务
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/copy")
    @SocketSend(params = "#result", sendClass = MusicCopySocketSend.class)
    public Task copy(String taskid){
        Task task = taskService.copyTask(taskid);
        return task;
    }

    /**
     * 手动执行任务
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/action")
    @SocketSend(params = "#result", sendClass = MusicActionSocketSend.class)
    public Task action(String taskid){
        Task task = taskService.queryByTaskId(taskid);
        taskStatusUtil.actionTask(taskid);

        //设置任务的进度
//        TaskStatusUtil.durationTask(taskid, task.getDuration());

        return task;
    }

    /**
     * 手动暂停
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/pause")
    @SocketSend(params = "#result", sendClass = MusicPauseSocketSend.class)
    public Task pause (String taskid){
        Task task = taskService.queryByTaskId(taskid);
        return task;
    }

    /**
     * 手动停止
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/stop")
    @SocketSend(params = "#result", sendClass = MusicStopSocketSend.class)
    public Task stop(String taskid){
        Task task = taskService.queryByTaskId(taskid);
        taskStatusUtil.stopTask(taskid);

//        TaskStatusUtil.clearNowDurationTask(taskid);
        return task;
    }

    /**
     * 是否执行
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/isaction")
    public boolean isAction(String taskid){
        return taskStatusUtil.isAction(taskid);
    }

    /**
     * 上一曲
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/pre")
    @SocketSend(params = "#result", sendClass = MusicPreSocketSend.class)
    public Task pre(String taskid){
        Task task = taskService.queryByTaskId(taskid);
        return task;
    }

    /**
     * 下一曲
     * @param taskid
     * @return
     */
    @ResponseBody
    @RequestMapping("/next")
    @SocketSend(params = "#result", sendClass = MusicNextSocketSend.class)
    public Task next(String taskid){
        Task task = taskService.queryByTaskId(taskid);
        return task;
    }


    /**
     * 获得任务信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/info")
    public Map<String, Object> getTaskInfo(int tid){

        Map<String, Object> resultMap = new HashMap<>();


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

        //设置类型
        resultMap.put("looptype", task.getLooptype());
        //设置音量
        resultMap.put("volume", task.getVolume());

        System.out.println("获得任务信息：" + resultMap);

        return resultMap;
    }


    /**
     *
     * 实时更新任务状态
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public List updateTaskStatus(@RequestParam("taskids[]") String[] taskids){
        //获得这些任务的状态信息
        List taskStatusMap = taskStatusUtil.getTaskStatusMap(taskids);
        return taskStatusMap;
    }

    /**
     *
     * 实时更新任务状态
     * @return
     */
    @RequestMapping("/updateClientStatus")
    @ResponseBody
    public List updateClientStatus(@RequestParam("uids[]") String[] uids){
        //获得这些任务的状态信息
        List taskStatusMap = taskStatusUtil.getTaskStatusClientMap(uids);
        return taskStatusMap;
    }

    /**
     * 修改任务音量
     * @return
     */
    @RequestMapping("/updateVolume")
    @ResponseBody
    @SocketSend(sendClass = UpdateVolumeSocketSend.class, params = {"#result", "#volume"})
    public Task updateVolume(String taskid, int volume){
        Task task = taskService.updateTaskVolume(taskid, volume);
        return task;
    }
}
