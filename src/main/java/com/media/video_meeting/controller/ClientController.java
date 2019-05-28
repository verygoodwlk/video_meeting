package com.media.video_meeting.controller;

import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.TreeNode;
import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket_aop.SocketSend;
import com.media.video_meeting.websocket_aop.send.ClientPowerSocketSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
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
    public List<TreeNode> groupManager(){
        //获得所有组已经组内的终端
        List<TreeNode> treeNodes = clientService.queryAllGroup();
        return treeNodes;
    }

    /**
     * 添加群组
     * @return
     */
    @SysLog(value = LogType.INSERT, info = "添加了群组")
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
    @SysLog(value = LogType.DELETE, info = "删除群组")
    @RequestMapping("/deletegroup")
    @ResponseBody
    public boolean deleteGroup(Integer id, Integer gid, boolean isClient){
        try {
            clientService.deleteGroupOrClient(id, gid, isClient);
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
    @SysLog(value = LogType.UPDATE, info = "移动群组和设备")
    @RequestMapping("/move")
    @ResponseBody
    public boolean moveClientGroup(Integer[] ids, Integer pid,  Integer oldpid, boolean isClient, boolean isCopy){
        System.out.println("需要移动：" + Arrays.toString(ids) + "到" + pid + " 子节点：" + isClient + " 是否复制：" + isCopy);
        try {
            clientService.move(ids, pid, oldpid, isClient, isCopy);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 修改终端名称
     * @return
     */
    @SysLog(value = LogType.UPDATE, info = "修改终端名称")
    @RequestMapping("/updatename")
    @ResponseBody
    public int updateName(Integer userid, String uname){
        int result = clientService.updateName(userid, uname);
        return result;
    }

    /**
     * 根据id查询终端信息
     * @param userid
     * @return
     */
    @RequestMapping("/querybyid")
    @ResponseBody
    public ClientMsg queryClientInfoById(Integer userid){
        ClientMsg clientMsg = clientService.queryById(userid);
        return clientMsg;
    }

    @RequestMapping("/updatePower")
    @ResponseBody
    @SocketSend(params = {"#userid", "#limits"}, sendClass = ClientPowerSocketSend.class)
    public String updatePower(Integer userid, Integer[] limits){
        clientService.updatePowers(userid, limits);
        return "succ";
    }
}
