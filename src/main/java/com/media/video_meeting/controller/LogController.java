package com.media.video_meeting.controller;

import com.media.video_meeting.entity.SystemLog;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/8 16:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ISystemLogService systemLogService;

    /**
     * 查询系统日志
     * @return
     */
    @PageHelper
    @RequestMapping("/syslist")
    public String sysList(Model model, Page page){
        List<SystemLog> systemLogs = systemLogService.queryAll();
        model.addAttribute(systemLogs);
        return "syslist";
    }
}
