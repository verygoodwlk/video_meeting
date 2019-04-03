package com.media.video_meeting.service;

import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Webcon;

import java.util.List;

/**
 * @Author ken
 * @Time 2019/3/11 9:35
 * @Version 1.0
 */
public interface IWebconService {

    List<Webcon> getWebconList();

    int insertWebCon(Webcon webcon);

    Webcon queryByAccount(String account);

    int deleteAccounts(String[] accounts);

    int updateWebcon(Webcon webcon);

    List<ClientMsg> queryClientsByWebcon(String account);
}
