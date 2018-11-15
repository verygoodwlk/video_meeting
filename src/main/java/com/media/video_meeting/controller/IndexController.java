package com.media.video_meeting.controller;

import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.util.LogUtil;
import com.media.video_meeting.util.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${server.os}")
    private String os;

    @RequestMapping("/")
    public String welcome(){
        return "login";
    }

    @RequestMapping("/topage/{page}")
    public String toPage(@PathVariable("page") String page){
        return page;
    }

    @SysLog(value = LogType.LOGIN, info = "进行登录操作")
    @RequestMapping("/user/login")
    public String login(String username, String password){
        return "index";
    }

    @RequestMapping("/ping")
    @ResponseBody
    public String ping(String ip, Integer number){
        LogUtil.info(logger, "ping的地址：" + ip + " 次数：" + number);
        String str = NetworkUtil.ping(ip, number, os);
        return str;
    }
}
