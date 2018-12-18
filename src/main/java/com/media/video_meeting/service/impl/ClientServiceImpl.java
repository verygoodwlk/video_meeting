package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.ClientGroupMapper;
import com.media.video_meeting.dao.ClientMsgMapper;
import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.util.GroupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ClientGroupMapper clientGroupMapper;

    @Autowired
    private GroupUtil groupUtil;

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

    @Override
    public List<ClientGroup> queryAllGroup() {
        //查询所有分组
        List<ClientGroup> clientGroups = clientGroupMapper.queryAllGroup();
        //查询所有设备
        List<ClientMsg> clientMsgs = clientMsgMapper.queryPage();
        for (ClientMsg clientMsg : clientMsgs) {
            ClientGroup group = new ClientGroup();
            group.setUserid(clientMsg.getUserid());
            group.setPid(clientMsg.getGid());
            group.setGname(clientMsg.getTerminalname());
            group.setIsParent("false");
            clientGroups.add(group);
        }
        return clientGroups;
    }

    @Override
    public int addGroup(ClientGroup group) {
        //发送分组消息
        int result = clientGroupMapper.insert(group);
        groupUtil.sendGroupInfo();
        return result;
    }

    @Override
    @Transactional
    public int deleteGroup(Integer id) {
        //删除群组
        clientGroupMapper.deleteGroup(id);
        //修改组成员成为默认组
        clientMsgMapper.updateByGroupId(id);
        groupUtil.sendGroupInfo();
        return 1;
    }

    /**
     * 移动设备
     * @param id
     * @param pid
     * @param isClient
     * @return
     */
    @Override
    @Transactional
    public int move(Integer id, Integer pid, boolean isClient) {
        if (isClient){
            //移动的是终端
            ClientMsg clientMsg = clientMsgMapper.selectByPrimaryKey(id);
            clientMsg.setGid(pid);
            clientMsgMapper.updateByPrimaryKeySelective(clientMsg);
        } else {
            //移动的是群组
            ClientGroup group = clientGroupMapper.selectByPrimaryKey(id);
            group.setPid(pid);
            clientGroupMapper.updateByPrimaryKeySelective(group);
        }

        //发送分组消息
        groupUtil.sendGroupInfo();

        return 1;
    }


}
