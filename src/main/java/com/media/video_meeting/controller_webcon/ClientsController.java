package com.media.video_meeting.controller_webcon;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.service.IWebconService;
import com.media.video_meeting.util.ClientStatusUtil;
import com.media.video_meeting.websocket_aop.SocketSend;
import com.media.video_meeting.websocket_aop.send.ClientUpdateVolumeSocketSend;
import com.media.video_meeting.websocket_aop.send.DeleteClientSocketSend;
import com.media.video_meeting.websocket_aop.send.MonitorSocketSend;
import com.media.video_meeting.websocket_aop.send.MonitoredSocketSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private IClientService clientService;

    /**
     * 分控查询终端列表
     * @return
     */
    @RequestMapping("/list")
    public String clientList(Model model, @SessionAttribute("account") Webcon webcon){
        List<ClientMsg> clientMsgs = webconService.queryClientsByWebcon(webcon.getAccount());
        model.addAttribute("clients", clientMsgs);
        model.addAttribute("listenStatus", ClientStatusUtil.getClientListenerMap());
        return "webcon/clients";
    }

    /**
     * 设置被监听终端
     * @param userid
     * @return
     */
    @RequestMapping("/byListener")
    @SocketSend(sendClass = MonitoredSocketSend.class, params = {"#userid"})
    @ResponseBody
    public String byListener(Integer userid){
        return "succ";
    }

    /**
     * 设置监听终端
     * @param userid
     * @return
     */
    @RequestMapping("/listener")
    @SocketSend(sendClass = MonitorSocketSend.class, params = {"#userid"})
    @ResponseBody
    public String listener(Integer userid){
        return "succ";
    }

    /**
     * 删除终端
     * @return
     */
    @RequestMapping("/delete")
    @SocketSend(sendClass = DeleteClientSocketSend.class, params = {"#userid"})
    @ResponseBody
    public String delete(Integer userid){
        clientService.deleteClientByUserId(userid);
        return "succ";
    }

    /**
     * 修改终端音量
     * @return
     */
    @RequestMapping("/updateVolume")
    @SocketSend(sendClass = ClientUpdateVolumeSocketSend.class, params = {"#uids", "#volume"})
    @ResponseBody
    public String updateVolume(@RequestParam("uids[]") String[] uids, int volume){
        clientService.updateVolume(volume, uids);
        return "succ";
    }
}
