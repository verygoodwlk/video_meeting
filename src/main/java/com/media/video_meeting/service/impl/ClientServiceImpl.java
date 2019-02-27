package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.ClientGroupMapper;
import com.media.video_meeting.dao.ClientMsgMapper;
import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.TreeNode;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.util.ClientUtil;
import com.media.video_meeting.util.GroupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private ClientUtil clientUtil;

    @Override
    @Transactional
    public int insertOrUpdate(ClientMsg clientMsg) {
        if(clientMsg.getUserid() != null){
            ClientMsg msg = clientMsgMapper.selectByPrimaryKey(clientMsg.getUserid());
            if (msg == null){
                if(clientMsg.getId().equals("online")){
                    clientMsgMapper.insert(clientMsg);
                    //将群组和终端插入中间表
                    clientMsgMapper.insertClientGroupTable(clientMsg.getUserid(), 1);
                    return 1;
                }
            } else {
                return clientMsgMapper.updateByPrimaryKeySelective(clientMsg);
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
    public List<TreeNode> queryAllGroup() {
        List<TreeNode> treeNodes = new ArrayList<>();
        //查询所有分组以及分组下的终端
        List<ClientGroup> clientGroups = clientGroupMapper.queryAllGroupJoinClient();
        for (ClientGroup clientGroup : clientGroups) {
            TreeNode treeNode = new TreeNode(clientGroup.getId(),
                    null,
                    clientGroup.getPid(),
                    clientGroup.getGname(),
                    "true",
                    "",
                    0);
            treeNodes.add(treeNode);
            //处理分组下的终端
            if(clientGroup.getClientMsgs() != null){
                for (ClientMsg clientMsg : clientGroup.getClientMsgs()) {
                    TreeNode treeNode1 = new TreeNode(null, clientMsg.getUserid(),
                            clientGroup.getId(),
                            clientMsg.getTerminalname(),
                            "false",
                            clientMsg.getStatus() == 1 ? "resources/images/online2.png" : "resources/images/offline2.png",
                            1);
                    treeNodes.add(treeNode1);
                }
            }
        }

        return treeNodes;
    }

    @Override
    public ClientMsg queryById(Integer userid) {
        return clientMsgMapper.selectByPrimaryKey(userid);
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
    public int deleteGroupOrClient(Integer id, Integer gid, boolean isClient) {
        if(!isClient){
            //操作的是群组
            //删除群组
            clientGroupMapper.deleteGroup(id);
            //该群组下的子群组设置为顶级群组
            clientGroupMapper.updateGourp2DefaultByPid(id);
            //根据群组id查询该组下的所有终端id
            List<Integer> cids = clientMsgMapper.selectClientIdsByGourpId(id);
            //修改组成员成为默认组
            for (Integer cid : cids) {
                //查询该设备是否存在于默认组
                int result = clientMsgMapper.update2DefaultGroupId(cid, id);
                if(result == 0){
                    //说明该终端已经存在于默认组了
                    clientMsgMapper.deleteClientGroupTable(cid, id);
                }
            }
        } else {
            //操作的就是终端
            clientMsgMapper.deleteClientGroupTable(id, gid);
        }

        groupUtil.sendGroupInfo();
        return 1;
    }

    /**
     * 移动设备
     * @param pid
     * @param isClient
     * @return
     */
    @Override
    @Transactional
    public int move(Integer[] ids, Integer pid, Integer oldpid, boolean isClient, boolean isCopy) {
        if (isClient){
            for (Integer id : ids) {
                //移动的是终端
//                ClientMsg clientMsg = clientMsgMapper.selectByPrimaryKey(id);
//                clientMsg.setGid(pid);
//                clientMsgMapper.updateByPrimaryKeySelective(clientMsg);

                //如果不是复制操作，则必须将终端与原来群组的关系删除掉
                if(!isCopy){
                    clientMsgMapper.deleteClientGroupTable(id, oldpid);
                }

                //添加终端和新群组的关系
                clientMsgMapper.insertClientGroupTable(id, pid);
            }
        } else {
            //移动的是群组
            ClientGroup group = clientGroupMapper.selectByPrimaryKey(ids[0]);
            group.setPid(pid);
            clientGroupMapper.updateByPrimaryKeySelective(group);
        }

        //发送分组消息
        groupUtil.sendGroupInfo();

        return 1;
    }

    @Override
    public int updateName(Integer userid, String uname) {
        ClientMsg clientMsg = clientMsgMapper.selectByPrimaryKey(userid);
        clientMsg.setTerminalname(uname);
        int result = clientMsgMapper.updateByPrimaryKeySelective(clientMsg);

        //发送socket
        if(result > 0){
            clientUtil.sendUpdateName(clientMsg);
        }
        return result;
    }

    /**
     * 根据终端id删除终端
     * @param cid
     * @return
     */
    @Override
    public int deleteClientByCid(String cid) {
        int result = clientMsgMapper.deleteByCid(cid);
        return result;
    }

    @Override
    public int insertClientGroupTable(Integer gid, Integer cid) {
        return clientMsgMapper.insertClientGroupTable(cid, gid);
    }


}
