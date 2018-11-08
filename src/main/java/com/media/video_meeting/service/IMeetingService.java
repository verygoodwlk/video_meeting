package com.media.video_meeting.service;

import com.media.video_meeting.entity.Meeting;
import com.media.video_meeting.entity.MeetingMoreInfo;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/7 15:43
 * @Version 1.0
 */
public interface IMeetingService {

    int addMeeting(Meeting meeting, MeetingMoreInfo meetingMoreInfo);

    int setMeetingDefualt(MeetingMoreInfo meetingMoreInfo);

    MeetingMoreInfo getMeetingDefault();

    List<Meeting> getAllMeeting();

    List<Meeting> getMeetingSearch(String keyword);
}
