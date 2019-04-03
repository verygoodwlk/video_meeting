package com.media.video_meeting.controller_webcon;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IWebconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @Author ken
 * @Date 2019/3/19
 * @Version 1.0
 */
@Controller
@RequestMapping("/web/clients")
public class ClientsController {

    @Autowired
    private IWebconService webconService;

    /**
     * 分控查询终端列表
     * @return
     */
    @RequestMapping("/list")
    public String clientList(Model model, @SessionAttribute("account") Webcon webcon){
        List<ClientMsg> clientMsgs = webconService.queryClientsByWebcon(webcon.getAccount());
        model.addAttribute("clients", clientMsgs);
        return "webcon/clients";
    }
}
