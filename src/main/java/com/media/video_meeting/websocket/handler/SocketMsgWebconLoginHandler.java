package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.service.ISolutionService;
import com.media.video_meeting.service.ITaskService;
import com.media.video_meeting.service.IWebconService;
import com.media.video_meeting.util.GroupUtil;
import com.media.video_meeting.websocket.MyWebSocket;
import com.media.video_meeting.websocket.SocketMsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 分控登录处理
 * @Author ken
 * @Date 2019/2/25
 * @Version 1.0
 */
@Component("webconLogin")
@Slf4j
public class SocketMsgWebconLoginHandler extends SocketMsgHandler {

    @Autowired
    private IWebconService webconService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ISolutionService solutionService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private GroupUtil groupUtil;

    @Autowired
    private MyWebSocket webSocket;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) {

        String account = jsonObject.getString("account");
        String passwd = jsonObject.getString("passwd");

        Webcon webcon = webconService.queryByAccount(account);
        if(webcon != null){
            if (webcon.getPassword().equals(passwd)){
                //登录成功
                log.info("webcon login：登录的分控信息->" + webcon);

                //获取方案名称
                Solution solu = solutionService.queryById(webcon.getSolutionid());

                //获得终端id
                String clients = webcon.getClients();
                //查询终端列表
                List<Map<String, Object>> clientMsgs = new ArrayList<>();
                String[] cts = clients.split("\\|");
                for (String groupclient : cts){
                    String[] client = groupclient.split("-");
                    ClientMsg clientMsg = clientService.queryById(Integer.parseInt(client[1]));

                    Map<String, Object> maps = new HashMap<>();
                    maps.put("id", clientMsg.getUserid() + "");
                    maps.put("terminalname", clientMsg.getTerminalname());
                    maps.put("status", clientMsg.getStatus());
                    clientMsgs.add(maps);
                }

                //获得实时音乐列表
                List<Task> realMusicTasks = taskService.queryByAccountAndTaskType(webcon.getAccount(), 5);


                //获得该分控下的所有的方案信息
                List<Map<String, Object>> maps = new ArrayList<>();
                List<Solution> solutions = solutionService.queryByAccount(webcon.getAccount());
                for (Solution solution : solutions){
                    Map<String, Object> map = new HashMap<>();
                    map.put("solution", solution.getSolutionname());

                    //获取方案对应的任务信息
                    List<Task> soluTask = taskService.queryBySolution(solution.getSolutionname());
                    map.put("task", soluTask);
                }


                //获取定时采集任务
                List<Task> collectTasks = taskService.queryByAccountAndTaskType(webcon.getAccount(), 2);

                //获取报警任务
                List<Task> fireTasks = taskService.queryByAccountAndTaskType(webcon.getAccount(), 3);

                //获取语音合成任务
                List<Task> voiceTasks = taskService.queryByAccountAndTaskType(webcon.getAccount(), 4);

                StringBuilder sb = new StringBuilder();
                sb.append("{\n" +
                        "\"id\": \"webconLoginResponse\",\n" +
                        "\"response\": \"accepted\",\n" +
                        "\"account\": \"" + webcon.getAccount() + "\",\n" +
                        "\"userlist\":" +
                        JSON.toJSONString(clientMsgs) +
                        ",\n" +
                        "\"realMusicTask\": " +
                        JSON.toJSONString(realMusicTasks) +
                        ",\n" +
                        "\"curentSolution\": \"" + solu.getSolutionname() + "\",\n" +
                        "\"timeTask\": " +
                        JSON.toJSONString(maps) +
                        ",\n" +
                        "\"collectTask\":" +
                        JSON.toJSONString(collectTasks) +
                        ",\n" +
                        "\"fireTask\": " +
                        JSON.toJSONString(fireTasks) +
                        ",\n" +
                        groupUtil.getGroups() + "," +
                        "\"voiceTask\": " +
                        JSON.toJSONString(voiceTasks) +
                        ",\n" +
                        "\"mp3\":[{\"name\":\"1.mp3\",\"folder\":\"admin\",\"duration\":\"60\" },{\"name\":\"2.mp3\",\"folder\":\"admin/123\",\"duration\":\"280\" }]\n" +
                        "}");

                //替换特殊字符
                //将"mp3":"[\"1\", \"2\"]" 替换成 "mp3":["1", "2"]
//                String str = sb.toString().replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"");
                String str = sb.toString();

                log.info("webcon login : 分控登录成功 " + str);
                webSocket.send(str);

                return;
            }
        }

        //登录失败
        log.info("webcon login : 分控登录失败 ");
        webSocket.send("{\"id\":\"webconLoginResponse\",\"response\":\"fail\",\"account\":\"" + webcon.getAccount() +"\"}");
    }

    @Override
    public void exception(Throwable t) {

    }
}
