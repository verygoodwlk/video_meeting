package com.media.video_meeting.controller;

import com.media.video_meeting.entity.User;
import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.service.IUserService;
import com.media.video_meeting.util.LogUtil;
import com.media.video_meeting.util.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("loginuser")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${server.os}")
    private String os;

    @Autowired
    private IUserService userService;

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
    public String login(String username, String password, Model model){

        User user = userService.login(username, password);
        if(user == null){
            model.addAttribute("error", 1);
            return "login";
        }

        model.addAttribute("loginuser", user);

        return "index";
    }

    @SysLog(value = LogType.LOGOUT, info = "注销账户")
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "login";
    }

    /**
     * 修改密码
     * @return
     */
    @SysLog(value = LogType.UPDATE, info = "修改管理员密码")
    @RequestMapping("/user/updatepassword")
    public String updatePassword(String newpassword, String oldpassword, Integer id, Model model){

        //修改密码
        int result = userService.updatePassword(newpassword, oldpassword, id);

        if(result == 0){
            model.addAttribute("error", 0);
            return "updatepassword";
        }

        return "succ";
    }

    @RequestMapping("/ping")
    @ResponseBody
    public String ping(String ip, String number){
        LogUtil.info(logger, "ping的地址：" + ip + " 次数：" + number);
        String str = NetworkUtil.ping(ip, number, os);
        return str;
    }
}
