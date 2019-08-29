package com.media.video_meeting.websocket;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.service.ISolutionService;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.util.LogUtil;
import com.media.video_meeting.util.TaskUtil;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/15 15:58
 * @Version 1.0
 */
@Component
public class SocketInitHandler {

    private static final Logger logger = LoggerFactory.getLogger(SocketInitHandler.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private SocketHeartHandler socketHeartHandler;

    @Autowired
    private ISolutionService solutionService;

    @Autowired
    private ITaskService taskService;

    /**
     * 初始化连接的操作
     * @param webSocketClient
     */
    public void init(WebSocketClient webSocketClient){
        //注册消息
        LogUtil.info(logger, "发送注册消息....");
        webSocketClient.send("{\"id\":\"register\",\"name\":\"webserver\"}");

        //发送终端列表
        List<ClientMsg> clientMsgs = clientService.queryPage();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"alluser\", \"users\":[");
        if(clientMsgs != null && clientMsgs.size() > 0){
            for (int i = 0; i < clientMsgs.size(); i++) {
                if(i != 0){
                    sb.append(",");
                }
                sb.append("{");
                sb.append("\"id\":\"").append(clientMsgs.get(i).getUserid()).append("\",");
                sb.append("\"terminalname\":\"").append(clientMsgs.get(i).getTerminalname()).append("\",");

                String limits = clientMsgs.get(i).getLimits();
                int n = 0;
                if(!limits.equals("0")){
                    List<Integer> integers = JSON.parseArray(limits, Integer.class);
                    for (Integer integer : integers) {
                        n += integer;
                    }
                }

                sb.append("\"limits\":").append(n);
                sb.append("}");
            }
        }
        sb.append("],");

        //处理定时音乐
        List<Solution> solutions = solutionService.querySolutionsList();
        sb.append("\"timeTask\":[");
        if(solutions != null && solutions.size() > 0){

            int count = 0;
            for (int i = 0; i < solutions.size(); i++) {

                Solution solution = solutions.get(i);
                if(solution.getIsaction() == 0){
                    continue;
                }

                count++;

                if(count > 1){
                    sb.append(",");
                }

//                if(i != 0){
//                    sb.append(",");
//                }

                //查询该方案的任务列表
                List<Task> tasks = taskService.queryBySolution(solution.getSolutionname(), solution.getAccount());

                sb.append("{\"solution\":\"").append(solution.getSolutionname()).append("\", \"account\":\"" + solution.getAccount()+ "\", \"task\":[");

                for (int j = 0; j < tasks.size(); j++) {
                    Task task = tasks.get(j);
                    Map<String, Object> map = TaskUtil.task2Map(task);

                    if(j != 0){
                        sb.append(",");
                    }

                    sb.append(JSON.toJSONString(map));
                }


                sb.append("]}");
            }
        }

        sb.append("],");

        //处理任务采集
        List<Task> tasks = taskService.queryByAccountAndTaskType(null, 2);
        sb.append("\"collectTask\":[");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Map<String, Object> map = TaskUtil.task2Map(task);

            if(i != 0){
                sb.append(",");
            }

            sb.append(JSON.toJSONString(map));
        }

        sb.append("],");

        //处理火警任务
        List<Task> tasks2 = taskService.queryByAccountAndTaskType(null, 3);
        sb.append("\"fireTask\":[");

        for (int i = 0; i < tasks2.size(); i++) {
            Task task = tasks2.get(i);
            Map<String, Object> map = TaskUtil.task2Map(task);

            if(i != 0){
                sb.append(",");
            }

            sb.append(JSON.toJSONString(map));
        }

        sb.append("],");

        //语音合成
        List<Task> tasks3 = taskService.queryByAccountAndTaskType(null, 4);
        sb.append("\"voiceTask\":[");

        for (int i = 0; i < tasks3.size(); i++) {
            Task task = tasks3.get(i);
            Map<String, Object> map = TaskUtil.task2Map(task);

            if(i != 0){
                sb.append(",");
            }

            sb.append(JSON.toJSONString(map));
        }

        sb.append("],");

        //处理一键
        ClientMsg host = clientService.getHost();
        sb.append("\"talkhost\":\"").append(host.getUserid()).append("\"");

        sb.append("}");
        LogUtil.info(logger, "首次发送终端列表...." + sb.toString());
        webSocketClient.send(sb.toString());

        //获取终端状态
        LogUtil.info(logger, "首次连接同步终端状态请求：{\"id\":\"terminalStatus\"}");
        webSocketClient.send("{\"id\":\"terminalStatus\"}");
    }
}
