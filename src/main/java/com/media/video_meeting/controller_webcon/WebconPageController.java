package com.media.video_meeting.controller_webcon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ken
 * @Date 2019/3/19
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class WebconPageController {

    /**
     * 分控端页面跳转
     * @return
     */
    @RequestMapping("/web/topage/{page}")
    public String toPage(@PathVariable("page") String page){
        return "webcon/" + page;
    }

    /**
     * 分控端欢迎页
     * @return
     */
    @RequestMapping("/web")
    public String welcome(){
        return "webcon/login";
    }
}
