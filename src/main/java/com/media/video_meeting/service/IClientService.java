package com.media.video_meeting.service;

import com.media.video_meeting.entity.ClientMsg;

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

}
