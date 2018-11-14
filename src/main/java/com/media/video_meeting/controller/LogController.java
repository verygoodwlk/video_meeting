package com.media.video_meeting.controller;

import com.media.video_meeting.entity.SearchLog;
import com.media.video_meeting.entity.SystemLog;
import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.ISystemLogService;
import com.media.video_meeting.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/8 16:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/log")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

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

    /**
     * 搜索系统日志
     * @return
     */
    @PageHelper
    @RequestMapping("/syssearch")
    public String sysSearch(SearchLog searchLog, Page page, Model model){
        List<SystemLog> systemLogs = systemLogService.searchLog(searchLog);
        model.addAttribute(systemLogs);
        model.addAttribute(searchLog);
        return "syslist";
    }

    @SysLog(value = LogType.DELETE, info = "批量删除日志记录")
    @RequestMapping("/deleteLog")
    public String deleteLog(Integer[] logid, Integer clear){
       if (clear == 1){
           LogUtil.info(logger, "删除日志：" + Arrays.toString(logid));
           systemLogService.deleteLogs(logid);
       } else {
           LogUtil.info(logger, "清空日志");
           systemLogService.clearLog();
       }

       return "redirect:/log/syslist";
    }
}
