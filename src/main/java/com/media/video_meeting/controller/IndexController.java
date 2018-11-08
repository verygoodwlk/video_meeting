package com.media.video_meeting.controller;

import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

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
}
