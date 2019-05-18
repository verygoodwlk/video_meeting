package com.media.video_meeting.service;

import com.media.video_meeting.entity.Record;

import java.util.List;

public interface IRecordService {

    int insertRecord(Record record);

    List<Record> queryByCid(int cid);

    int deleteRecordByName(String name);
}
