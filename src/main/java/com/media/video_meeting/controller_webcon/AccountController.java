package com.media.video_meeting.controller_webcon;

import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IWebconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/web/account")
@SessionAttributes("account")
public class AccountController {

    @Autowired
    private IWebconService webconService;

    @Value("${golang.serverpath}")
    private String golangServer;

    /**
     * 进行登录
     * @return
     */
    @RequestMapping("/login")
    public String login(String account, String password, Model model){

        //分控登录
        Webcon webcon = webconService.queryByAccount(account);
        if (webcon != null){
            if (webcon.getPassword().equals(password)){

                //保存进session中
                model.addAttribute("account", webcon);
                model.addAttribute("golang", golangServer);
                //登录成功
                return "webcon/index";
            } else {
                //密码错误
                model.addAttribute("error", "密码错误！");
            }
        } else {
            //账号不存在
            model.addAttribute("error", "分控账号不存在！");
        }
        return "webcon/login";
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "webcon/login";
    }
}
