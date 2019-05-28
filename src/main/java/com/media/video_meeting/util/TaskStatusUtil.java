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
}
