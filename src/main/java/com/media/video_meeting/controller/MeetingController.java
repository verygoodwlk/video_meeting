package com.media.video_meeting.controller;

import com.media.video_meeting.entity.Meeting;
import com.media.video_meeting.entity.MeetingMoreInfo;
import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.page.Page;
import com.media.video_meeting.page.PageHelper;
import com.media.video_meeting.service.IMeetingService;
import com.media.video_meeting.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/4 11:46
 * @Version 1.0
 */
@Controller
@RequestMapping("/meeting")
public class MeetingController {

    private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private IMeetingService meetingService;

    /**
     * 会议列表
     * @return
     */
    @PageHelper
    @RequestMapping("/list")
    public String list(Model model, Page page){
        List<Meeting> allMeeting = meetingService.getAllMeeting();
        model.addAttribute("meetings", allMeeting);
        return "meetinglist";
    }

    @RequestMapping("/toadd")
    public String toAdd(Model model){
        MeetingMoreInfo meetingDefault = meetingService.getMeetingDefault();
        model.addAttribute("default" , meetingDefault);
        model.addAttribute("number", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        return "meetingadd";
    }

    /**
     * 添加会议
     * @return
     */
    @RequestMapping("/add")
    @SysLog(value = LogType.INSERT, info = "添加会议信息")
    public String insertMeeting(Meeting meeting, MeetingMoreInfo meetingMoreInfo, Integer time1, Integer time2){
        Integer time = time1 * 60 * 60 * 1000 + time2 * 60 * 1000;
        meeting.setTime(time);
        LogUtil.info(logger, "会议对象：" + meeting);
        LogUtil.info(logger, "会议详细信息对象：" + meetingMoreInfo);

        meetingService.addMeeting(meeting, meetingMoreInfo);

        return "redirect:/topage/succ";
    }

    /**T
     * 删除会议
     * @return
     */
    @SysLog(value = LogType.DELETE, info = "批量删除会议信息")
    @RequestMapping("/deleteMeeting")
    public String deleteMeeting(Integer[] mid){
        meetingService.deleteMeetings(mid);
        return "redirect:/meeting/list";
    }


    /**
     * 去到默认配置页面
     * @return
     */
    @RequestMapping("/tomeetingconfig")
    public String toMeetingConfig(Model model){
        MeetingMoreInfo meetingDefault = meetingService.getMeetingDefault();
        model.addAttribute("default" , meetingDefault);
        return "meetingconfig";
    }

    /**
     * 设置默认参数配置
     * @return
     */
    @RequestMapping("/setdefault")
    @SysLog(value = LogType.UPDATE, info = "修改默认会议参数")
    public String updateDefaultInfo(MeetingMoreInfo meetingMoreInfo){
        LogUtil.info(logger, "修改的默认参数值：" + meetingMoreInfo);
        //设置默认值
        meetingService.setMeetingDefualt(meetingMoreInfo);
        return "redirect:/topage/succ";
    }

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @PageHelper
    @RequestMapping("/search")
    public String searchMeeting(String keyword, Model model, Page page){
        LogUtil.info(logger, "关键字：" + keyword);
        List<Meeting> allMeeting = meetingService.getMeetingSearch(keyword);
        model.addAttribute("meetings", allMeeting);
        model.addAttribute("keyword", keyword);
        return "meetinglist";
    }

}
