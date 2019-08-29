package com.media.video_meeting.util;

import com.media.video_meeting.entity.BroadCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 任务状态
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/26 21:52
 */
@Component
public class TaskStatusUtil {

    @Autowired
    private PushUtil pushUtil;

    private Map<String, Boolean> taskMap = new ConcurrentHashMap<>();

    /**
     * 执行任务
     * @param taskId
     */
    public void actionTask(String taskId){
        taskMap.put(taskId, true);
    }

    /**
     * 停止任务
     */
    public void stopTask(String taskId){
        if(taskMap.containsKey(taskId)){
            taskMap.remove(taskId);
        }
    }

    /**
     * 是否执行
     * @param taskId
     * @return
     */
    public boolean isAction(String taskId){
        if(taskMap.containsKey(taskId)){
            return true;
        }
        return false;
    }

    private Map<String, Map<String, Object>> taskStatusMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Object>> taskAllDurationMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Object>> taskClientStatusMap = new ConcurrentHashMap<>();

    /**
     * 设置任务总进度时间
     */
    public void durationTask(String taskid, int duration){

        if(taskAllDurationMap.containsKey(taskid)){
            Map<String, Object> stringObjectMap = taskAllDurationMap.get(taskid);
            stringObjectMap.put("allduration", duration);
            stringObjectMap.put("nowduration", 0);
        } else {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("allduration", duration);
            dataMap.put("nowduration", 0);//设置当前进度时间
            taskAllDurationMap.put(taskid, dataMap);
        }
    }

    /**
     * 所有任务的当前进度加1
     */
    public void addNowDurationTask(){
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : taskAllDurationMap.entrySet()) {
            //获得所有taskid
            String taskid = stringMapEntry.getKey();
            if(taskStatusMap.containsKey(taskid)){
                Map<String, Object> map = taskStatusMap.get(taskid);
                int status = (int) map.get("status");

                Map<String, Object> value = stringMapEntry.getValue();
                int nowduration = (int) value.get("nowduration");
                int allduration = (int) value.get("allduration");

                if(status == 1 && nowduration < allduration){
                    //当前任务正在执行中
                    value.put("nowduration", nowduration + 1);
                }
            }
        }
    }

    /**
     * 清空当前进度
     * @param taskid
     */
    public void clearNowDurationTask(String taskid){
        if(taskAllDurationMap.containsKey(taskid)){
            Map<String, Object> stringObjectMap = taskAllDurationMap.get(taskid);
            stringObjectMap.put("nowduration", 0);
            stringObjectMap.put("allduration", 0);
        }
    }

    /**
     * 设置任务状态
     *
     * status 0-任务空闲  	1-执行中 	  2-停止， 3-暂停
     * type  0:普通状态  1:一次性任务结束 - 删除   2:每天任务结束 - 更新开始时间   3:设置任务的总进度
     */
    public void statusTask(String taskid, int status, String mp3, int duration, String startDate, String[] uids, int type){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", status);
        dataMap.put("mp3", mp3);
        dataMap.put("duration", duration);
        dataMap.put("startDate", startDate);
        dataMap.put("type", type);

        //判断当前任务是否存在
        if(taskStatusMap.containsKey(taskid)){
            Map<String, Object> oldMap = taskStatusMap.get(taskid);
            if(!oldMap.get("mp3").equals(dataMap.get("mp3")) && status == 1){
                //如果歌曲发生了更新，修改任务持续时间
                durationTask(taskid, duration);
            }
        } else {
            //如果歌曲不存在，直接更新任务持续时间
            durationTask(taskid, duration);
        }

        taskStatusMap.put(taskid, dataMap);

        for (String uid : uids) {
            taskClientStatusMap.put(uid, dataMap);
        }

        if(status == 0 || status == 2){
            //普通状态 或者 任务停止
            stopTask(taskid);
            //清空状态
            clearNowDurationTask(taskid);
        } else if(status == 1){
            //任务开始
            actionTask(taskid);

            //TODO 调用第三方结构-任务开始发送请求
            Date date = new Date(duration);
            String dateFormat = new SimpleDateFormat("mm:ss").format(date);
            BroadCast broadCast = new BroadCast(mp3, dateFormat, "00:00");
            pushUtil.pushBroadcast(broadCast);
        }
    }

    /**
     * 根据任务列表查询当前任务最新状态
     * @param taskids
     * @return
     */
    public List getTaskStatusMap(String[] taskids){
        List rlist = new ArrayList();
        for (String taskid : taskids) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("taskid", taskid);
            if(taskStatusMap.containsKey(taskid)){
                //存在这个任务id
                Map<String, Object> rMap = taskStatusMap.get(taskid);
                newMap.putAll(rMap);
            }

            if(taskAllDurationMap.containsKey(taskid)){
                Map<String, Object> duration = taskAllDurationMap.get(taskid);
                newMap.putAll(duration);
//                taskAllDurationMap.remove(taskid);
            }

            rlist.add(newMap);
        }
        return rlist;
    }

    /**
     * 根据终端列表查询当前任务最新状态
     * @return
     */
    public List getTaskStatusClientMap(String[] uids){
        List rlist = new ArrayList();
        for (String uid : uids) {
            if(taskClientStatusMap.containsKey(uid)){
                //存在这个任务id
                Map<String, Object> rMap = taskClientStatusMap.get(uid);

                Map<String, Object> newMap = new HashMap<>(rMap);
                newMap.put("uid", uid);
                rlist.add(newMap);
            }
        }
        return rlist;
    }

}
