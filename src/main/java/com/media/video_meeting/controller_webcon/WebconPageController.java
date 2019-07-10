package com.media.video_meeting.controller_webcon;

import com.media.video_meeting.entity.Webcon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @Author ken
 * @Date 2019/3/19
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class WebconPageController {

    @Value("${golang.serverpath}")
    private String wsServerPath;

    /**
     * 分控端页面跳转
     * @return
     */
    @RequestMapping("/web/topage/{page}")
    public String toPage(
            @PathVariable("page") String page,
            @SessionAttribute("account") Webcon webcon,
            Model model){
        model.addAttribute("wsUrl", wsServerPath);
        model.addAttribute("account", webcon);
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
