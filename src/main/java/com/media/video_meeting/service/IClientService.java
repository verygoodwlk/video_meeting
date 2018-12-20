package com.media.video_meeting.service;

import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.TreeNode;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/12/15 14:37
 * @Version 1.0
 */
public interface IClientService {

    int insertOrUpdate(ClientMsg clientMsg);

    List<ClientMsg> queryPage();

    List<Integer> queryUserIds();

    List<TreeNode> queryAllGroup();

    int addGroup(ClientGroup group);

    int deleteGroupOrClient(Integer id, Integer gid, boolean isClient);

    int move(Integer[] ids, Integer pid, Integer oldpid, boolean isClient, boolean isCopy);

    int updateName(Integer userid, String uname);

}
