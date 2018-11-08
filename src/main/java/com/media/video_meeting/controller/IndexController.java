package com.media.video_meeting.controller;

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

    @RequestMapping("/user/login")
    public String login(String username, String password){
        return "index";
    }
}
