package com.media.video_meeting.controller;

import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.IWebconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author ken
 * @Date 2019/3/11
 * @Version 1.0
 */
@Controller
@RequestMapping("/webcon")
public class WebconController {

    @Autowired
    private IWebconService webconService;

    /**
     * 获取所有分控
     * @return
     */
    @RequestMapping("/list")
    @PageHelper
    public String list(Model model, Page page){
        List<Webcon> webconList = webconService.getWebconList();
        model.addAttribute("list", webconList);
        return "webconlist";
    }

    /**
     * 添加分控
     * @param webcon
     * @return
     */
    @RequestMapping("/insert")
    public String insert(Webcon webcon){
        System.out.println("接收到消息：" + webcon);
        webconService.insertWebCon(webcon);
        return "redirect:/webcon/list";
    }

    /**
     * 校验用户名是否唯一
     * @return
     */
    @RequestMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(String account){
        Webcon webcon = webconService.queryByAccount(account);
        return webcon != null ? true : false;
    }

    /**
     * 根据用户名查询分控信息
     * @param account
     * @return
     */
    @RequestMapping("/queryByAccount")
    @ResponseBody
    public Webcon queryByAccount(String account){
        return webconService.queryByAccount(account);
    }

    /**
     * 批量删除分控信息
     * @return
     */
    @RequestMapping("/deleteWebcons")
    public String deleteWebcons(String[] mid){
        webconService.deleteAccounts(mid);
        return "redirect:/webcon/list";
    }
}
