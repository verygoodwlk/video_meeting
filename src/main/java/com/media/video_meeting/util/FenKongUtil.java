package com.media.video_meeting.util;

import com.media.video_meeting.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 分控的工具方法
 *
 * @Author ken
 * @Date 2019/1/20
 * @Version 1.0
 */
@Component
public class FenKongUtil {


    @Autowired
    private MyWebSocket myWebSocket;

    @Autowired
    private GroupUtil groupUtil;
    /**
     * 当分控登录时，返回的响应
     */
    public void sendWebconLoginResponse(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n" +
                "\"id\": \"webconLoginResponse\",\n" +
                "\"response\": \"accepted\",\n" +
                "\"account\": \"admin\",\n" +
                "\"userlist\": [{\n" +
                "\"id\": \"3\",\n" +
                "\"terminalname\": \"3\",\n" +
                "\"status\": 0\n" +
                "}, {\n" +
                "\"id\": \"4\",\n" +
                "\"terminalname\": \"4\",\n" +
                "\"status\": 0\n" +
                "}, {\n" +
                "\"id\": \"9\",\n" +
                "\"terminalname\": \"9\",\n" +
                "\"status\": 0\n" +
                "}, {\n" +
                "\"id\": \"2\",\n" +
                "\"terminalname\": \"bai\",\n" +
                "\"status\": 0\n" +
                "}, {\n" +
                "\"id\": \"12\",\n" +
                "\"terminalname\": \"12\",\n" +
                "\"status\": 1\n" +
                "}, {\n" +
                "\"id\": \"1\",\n" +
                "\"terminalname\": \"1\",\n" +
                "\"status\": 0\n" +
                "}],\n" +
                "\"realMusicTask\": [{\n" +
                "\"taskid\": \"145\",\n" +
                "\"taskname\": \"Region\",\n" +
                "\"volume\": \"8\",\n" +
                "\"duration\": 1800,\n" +
                "\"mp3\": [\"1.mp3\", \"2.mp3\", \"G.E.M.邓紫棋 - 喜欢你.mp3\"],\n" +
                "\"terminal\": [\"1\", \"2\", \"3\"]\n" +
                "}, {\n" +
                "\"taskid\": \"187\",\n" +
                "\"taskname\": \"123\",\n" +
                "\"volume\": \"8\",\n" +
                "\"duration\": 1800,\n" +
                "\"mp3\": [\"1.mp3\", \"G.E.M.邓紫棋 - 喜欢你.mp3\"],\n" +
                "\"terminal\": [\"1\", \"2\", \"3\"]\n" +
                "}],\n" +
                "\"timeTask\": [{\n" +
                "\"solution\": \"Default Solution\",\n" +
                "\"taskid\": \"112\",\n" +
                "\"taskname\": \"Region\",\n" +
                "\"taskPriority\": 0,\n" +
                "\"loopType\": 1,\n" +
                "\"stopDate\": \"2019-06-10\",\n" +
                "\"volume\": 0,\n" +
                "\"mp3\": [\"1.mp3\", \"2.mp3\"],\n" +
                "\"startTime\": \"16:22:53\",\n" +
                "\"duration\": 1800,\n" +
                "\"playOrder\": 1,\n" +
                "\"users\": [\"1\"],\n" +
                "\"status\": 0,\n" +
                "\"samll\": 0,\n" +
                "\"startDate\": \"2018-12-14\",\n" +
                "\"weekMask\": 254\n" +
                "}, {\n" +
                "\"solution\": \"Default Solution\",\n" +
                "\"taskid\": \"112\",\n" +
                "\"taskname\": \"Region\",\n" +
                "\"taskPriority\": 0,\n" +
                "\"loopType\": 1,\n" +
                "\"stopDate\": \"2019-06-10\",\n" +
                "\"volume\": 0,\n" +
                "\"mp3\": [\"1.mp3\", \"2.mp3\"],\n" +
                "\"startTime\": \"16:22:53\",\n" +
                "\"duration\": 1800,\n" +
                "\"playOrder\": 1,\n" +
                "\"users\": [\"1\"],\n" +
                "\"status\": 0,\n" +
                "\"samll\": 0,\n" +
                "\"startDate\": \"2018-12-14\",\n" +
                "\"weekMask\": 254\n" +
                "}],\n" +
                "\"collectTask\": [{\n" +
                "\"taskid\": \"112\",\n" +
                "\"taskPriority\": 0,\n" +
                "\"taskname\": \"Region\",\n" +
                "\"stopDate\": \"2019-06-10\",\n" +
                "\"startTime\": \"16:22:53\",\n" +
                "\"duration\": 1800,\n" +
                "\"playOrder\": 1,\n" +
                "\"users\": [\"1\"],\n" +
                "\"status\": 0,\n" +
                "\"startDate\": \"2018-12-14\",\n" +
                "\"weekMask\": 254\n" +
                "}],\n" +
                "\"fireTask\": [{\n" +
                "\"taskid\": \"112\",\n" +
                "\"taskname\": \"火警\",\n" +
                "\"area\": \"A区\",\n" +
                "\"fireTerminal\": \"1\",\n" +
                "\"port\": \"1\",\n" +
                "\"isLevel\": \"0\",\n" +
                "\"mp3\": [\"1.mp3\", \"2.mp3\"],\n" +
                "\"isExternalMusic\": \"1\",\n" +
                "\"externalFireTerminal\": \"10\",\n" +
                "\"users\": [\"1\"]\n" +
                "}],\n" +
                groupUtil.getGroups() + "," +
                "\"voiceTask\": [{\n" +
                "\"taskid\": \"112\",\n" +
                "\"info\": \"欢迎光临\",\n" +
                "\"reporter\": \"1\",\n" +
                "\"speed\": \"50\",\n" +
                "\"loopNum\": \"1\",\n" +
                "\"taskname\": \"Region\",\n" +
                "\"taskPriority\": 0,\n" +
                "\"taskType\": 1,\n" +
                "\"stopDate\": \"2019-06-10\",\n" +
                "\"startTime\": \"16:22:53\",\n" +
                "\"users\": [\"1\"],\n" +
                "\"startDate\": \"2018-12-14\",\n" +
                "\"weekMask\": 254,\n" +
                "\"status\": \"0\"\n" +
                "}],\n" +
                "\"mp3\": [\"1.mp3\", \"2.mp3\"]\n" +
                "}");

        myWebSocket.send(sb.toString());
    }
}
