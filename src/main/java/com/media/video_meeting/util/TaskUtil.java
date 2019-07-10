package com.media.video_meeting.util;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @user ken
 * @date 2019/5/27 22:30
 */
@Slf4j
public class TaskUtil {

    public static Map<String, Object> task2Map(Task task){
        Map<String, Object> map = new HashMap<>();
        if(task.getTaskt() == 5){
            //实时音乐
            map.put("account", task.getAccount());
            map.put("taskid", task.getTaskid());
            map.put("taskname", task.getTaskname());
            map.put("loopType", task.getLooptype());
            map.put("volume", task.getVolume());
            map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
            map.put("terminal", JSON.parseArray(task.getUsers(), String.class));
        } else if(task.getTaskt() == 1){
            //定时音乐
            map.put("account", task.getAccount());
            map.put("taskid", task.getTaskid());
            map.put("solution", task.getSolution());
            map.put("taskname", task.getTaskname());
            map.put("taskPriority", task.getTaskpriority());
            map.put("loopType", task.getLooptype());
            map.put("stopDate", task.getStopDate());
            map.put("startTime", task.getStartTime());
            map.put("volume", task.getVolume());
            map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
            map.put("duration", task.getDuration());
            map.put("playOrder", task.getPlayOrder());
            map.put("users", JSON.parseArray(task.getUsers(), String.class));
            map.put("status", task.getStatus());
            map.put("samll", task.getSamll());
            map.put("startDate", task.getStartDate());
            map.put("weekMask", task.getWeekMask());
        } else if(task.getTaskt() == 2){
            //定时采集
            map.put("account", task.getAccount());
            map.put("taskid", task.getTaskid());
            map.put("taskname", task.getTaskname());
            map.put("taskPriority", task.getTaskpriority());
            map.put("stopDate", task.getStopDate());
            map.put("startTime", task.getStartTime());
            map.put("duration", task.getDuration());
            map.put("playOrder", task.getPlayOrder());
            map.put("users", JSON.parseArray(task.getUsers(), String.class));
            map.put("status", task.getStatus());
            map.put("samll", task.getSamll());
            map.put("startDate", task.getStartDate());
            map.put("weekMask", task.getWeekMask());
            map.put("terminal", task.getCollectionclient());
        } else if(task.getTaskt() == 3){
            //消防报警
            map.put("account", task.getAccount());
            map.put("taskid", task.getTaskid());
            map.put("taskname", task.getTaskname());
            map.put("area", task.getArea());
            map.put("fireTerminal", task.getFireTerminal());
            map.put("port", task.getPort());
            map.put("isLevel", task.getIsLevel());
            map.put("mp3", JSON.parseArray(task.getMp3(), String.class));
            map.put("users", JSON.parseArray(task.getUsers(), String.class));
            map.put("isExternalMusic", task.getIsExternalMusic());
            map.put("externalFireTerminal", task.getExternalFireTerminal());
        } else if(task.getTaskt() == 4){
            //语音合成
            map.put("account", task.getAccount());
            map.put("taskid", task.getTaskid());
            map.put("taskname", task.getTaskname());
            map.put("info", task.getInfo());
            map.put("reporter", task.getReporter());
            map.put("speed", task.getSpeed());
            map.put("loopNum", task.getLoopnum());
            map.put("taskPriority", task.getTaskpriority());
            map.put("taskType", task.getTasktype());
            map.put("stopDate", task.getStopDate());
            map.put("startTime", task.getStartTime());
            map.put("startDate", task.getStartDate());
            map.put("weekMask", task.getWeekMask());
            map.put("status", task.getStatus());
            map.put("users", JSON.parseArray(task.getUsers(), String.class));
        }
        return map;
    }
}
