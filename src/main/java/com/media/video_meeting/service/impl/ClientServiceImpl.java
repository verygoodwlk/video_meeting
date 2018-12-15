package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.ClientMsgMapper;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/15 14:38
 * @Version 1.0
 */
@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientMsgMapper clientMsgMapper;

    @Override
    public int insertOrUpdate(ClientMsg clientMsg) {
        if(clientMsg.getUserid() != null){
            ClientMsg msg = clientMsgMapper.selectByPrimaryKey(clientMsg.getUserid());
            if (msg == null){
                if(clientMsg.getId().equals("online")){
                    return  clientMsgMapper.insert(clientMsg);
                }
            } else {
                return  clientMsgMapper.updateByPrimaryKeySelective(clientMsg);
            }
        }
        return 0;
    }

    @Override
    public List<ClientMsg> queryPage() {
        return clientMsgMapper.queryPage();
    }

    @Override
    public List<Integer> queryUserIds() {
        return clientMsgMapper.queryUserIds();
    }
}
