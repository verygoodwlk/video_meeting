package com.media.video_meeting.controller;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/15 14:36
 * @Version 1.0
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PageHelper
    @RequestMapping("/list")
    public String clientList(Model model, Page page){
        List<ClientMsg> clientMsgs = clientService.queryPage();
        model.addAttribute(clientMsgs);
        return "clientlist";
    }


}
