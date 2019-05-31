package com.media.video_meeting.util;

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
public class TaskStatusUtil {

    private static Map<String, Boolean> taskMap = new ConcurrentHashMap<>();

    /**
     * 执行任务
     * @param taskId
     */
    public static void actionTask(String taskId){
        taskMap.put(taskId, true);
    }

    /**
     * 停止任务
     */
    public static void stopTask(String taskId){
        if(taskMap.containsKey(taskId)){
            taskMap.remove(taskId);
        }
    }

    /**
     * 是否执行
     * @param taskId
     * @return
     */
    public static boolean isAction(String taskId){
        if(taskMap.containsKey(taskId)){
            return true;
        }
        return false;
    }

    private static Map<String, Map<String, Object>> taskStatusMap = new ConcurrentHashMap<>();

    /**
     * 设置任务状态
     */
    public static void statusTask(String taskid, int status, String mp3){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", status);
        dataMap.put("mp3", mp3);

        taskStatusMap.put(taskid, dataMap);
    }

    /**
     * 根据任务列表查询当前任务最新状态
     * @param taskids
     * @return
     */
    public static List getTaskStatusMap(String[] taskids){
        List rlist = new ArrayList();
        for (String taskid : taskids) {
            if(taskStatusMap.containsKey(taskid)){
                //存在这个任务id
                Map<String, Object> rMap = taskStatusMap.get(taskid);

                Map<String, Object> newMap = new HashMap<>(rMap);
                newMap.put("taskid", taskid);
                rlist.add(newMap);
            }
        }
        return rlist;
    }

}
