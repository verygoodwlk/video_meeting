package com.media.video_meeting.controller;

import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    /**
     * 终端列表
     * @param model
     * @param page
     * @return
     */
    @PageHelper
    @RequestMapping("/list")
    public String clientList(Model model, Page page){
        List<ClientMsg> clientMsgs = clientService.queryPage();
        model.addAttribute(clientMsgs);
        return "clientlist";
    }

    /**
     * 群组管理
     * @return
     */
    @RequestMapping("/group")
    @ResponseBody
    public List<ClientGroup> groupManager(){
        //获得所有组已经组内的终端
        List<ClientGroup> clientGroups = clientService.queryAllGroup();
        return clientGroups;
    }

    /**
     * 添加群组
     * @return
     */
    @RequestMapping("/addgroup")
    public String addGroup(ClientGroup clientGroup){
        clientService.addGroup(clientGroup);
        return "redirect:/topage/clientgroup";
    }

    /**
     * 删除群组
     * @param id
     * @return
     */
    @RequestMapping("/deletegroup")
    @ResponseBody
    public boolean deleteGroup(Integer id){
        try {
            clientService.deleteGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 移动群组和设备
     * @return
     */
    @RequestMapping("/move")
    @ResponseBody
    public boolean moveClientGroup(Integer id, Integer pid, boolean isClient){
        System.out.println("需要移动：" + id + "到" + pid + " 子节点：" + isClient);
        try {
            clientService.move(id, pid, isClient);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
