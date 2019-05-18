package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.RecordClientMapper;
import com.media.video_meeting.dao.RecordMapper;
import com.media.video_meeting.entity.Record;
import com.media.video_meeting.entity.RecordClient;
import com.media.video_meeting.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RecordClientMapper recordClientMapper;

    @Override
    @Transactional
    public int insertRecord(Record record) {
        int result = recordMapper.insert(record);

        List<Integer> users = record.getUsers();
        for (int i = 0; i < users.size(); i++){
            RecordClient recordClient = new RecordClient(record.getId(), users.get(i));
            recordClientMapper.insert(recordClient);
        }

        return result;
    }

    @Override
    public List<Record> queryByCid(int cid) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid", cid);
        List<RecordClient> list = recordClientMapper.selectList(queryWrapper);

        List<Record> records = new ArrayList<>();
        for (RecordClient recordClient : list) {
            Record record = recordMapper.selectById(recordClient.getRid());

            QueryWrapper qw = new QueryWrapper();
            qw.eq("rid", record.getId());
            List<RecordClient> recordClientList = recordClientMapper.selectList(qw);
            for (RecordClient client : recordClientList) {
                record.addUserId(client.getUserid());
            }

            records.add(record);
        }

        return records;
    }

    @Override
    public int deleteRecordByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("record", name);
        Record record = recordMapper.selectOne(queryWrapper);

        if(record != null){
            int id = record.getId();

            Map map = new HashMap<>();
            map.put("rid", id);
            recordClientMapper.deleteByMap(map);

            return recordMapper.deleteById(id);
        }
        return 0;
    }
}
