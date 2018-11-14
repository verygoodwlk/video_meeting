package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.MeetingMapper;
import com.media.video_meeting.dao.MeetingMoreInfoMapper;
import com.media.video_meeting.entity.Meeting;
import com.media.video_meeting.entity.MeetingMoreInfo;
import com.media.video_meeting.service.IMeetingService;
import com.media.video_meeting.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/7 15:44
 * @Version 1.0
 */
@Service
public class MeetingServiceImpl implements IMeetingService {

    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private MeetingMoreInfoMapper meetingMoreInfoMapper;

    //添加会议
    @Override
    @Transactional
    public int addMeeting(Meeting meeting, MeetingMoreInfo meetingMoreInfo) {
        //添加会议基本信息
        meetingMapper.insertSelective(meeting);
        //添加会议详细信息
        meetingMoreInfo.setMid(meeting.getId());
        meetingMoreInfoMapper.insertSelective(meetingMoreInfo);
        return 1;
    }

    @Override
    public int setMeetingDefualt(MeetingMoreInfo meetingMoreInfo) {
        if(meetingMoreInfo.getId() == null){
            meetingMoreInfoMapper.insertSelective(meetingMoreInfo);
        } else {
            meetingMoreInfoMapper.updateByPrimaryKey(meetingMoreInfo);
        }
        return 1;
    }

    @Override
    public MeetingMoreInfo getMeetingDefault() {
        MeetingMoreInfo meetingMoreInfo = meetingMoreInfoMapper.selectDefaultMeetingMoreInfo();
        return meetingMoreInfo != null ? meetingMoreInfo : new MeetingMoreInfo();
    }

    @Override
    public List<Meeting> getAllMeeting() {
        return meetingMapper.queryAll();
    }

    @Override
    public List<Meeting> getMeetingSearch(String keyword) {
        if(StringUtil.isNotEmpty(keyword)){
            return meetingMapper.queryByKeyWord(keyword);
        }
        return meetingMapper.queryAll();
    }

    @Override
    @Transactional
    public int deleteMeetings(Integer[] mid){
        if(mid != null){
            for (Integer integer : mid) {
                meetingMapper.deleteByPrimaryKey(integer);
                meetingMoreInfoMapper.deleteByMid(integer);
            }
        }
        return 1;
    }
}
