package com.media.video_meeting.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @user ken
 * @date 2019/5/22 21:29
 */
public class ClientStatusUtil {

    /**
     * 监听集合
     *
     * monitored - userid
     * monitor - userid
     *
     */
    public static Map<String, Integer> clientListenerMap = new ConcurrentHashMap<>();

    /**
     * 设置被监听模式
     * @return
     */
    public static String setByListener(Integer userid){

        //获得被监听终端的id号
        Integer monitored = clientListenerMap.get("monitored");
        //判断当前这个终端是否为被监听模式
        if(monitored != null && monitored == userid){
            //已经是被监听的终端了
            clientListenerMap.remove("monitored");
            //返回停止状态
            return "stop";
        }

        //当前终端不是被监听的终端
        clientListenerMap.put("monitored", userid);

        return "start";
    }

    /**
     * 设置监听模式
     * @param userid
     * @return
     */
    public static String setListener(Integer userid){
        //获得监听终端的id号
        Integer monitored = clientListenerMap.get("monitor");
        //判断当前这个终端是否为被监听模式
        if(monitored != null && monitored == userid){
            //已经是被监听的终端了
            clientListenerMap.remove("monitor");
            //返回停止状态
            return "stop";
        }

        //当前终端不是被监听的终端
        clientListenerMap.put("monitor", userid);

        return "start";
    }

    public static Map<String, Integer> getClientListenerMap(){
        return clientListenerMap;
    }
}
