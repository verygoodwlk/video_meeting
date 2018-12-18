package com.media.video_meeting.service;


import com.media.video_meeting.entity.User;

/**
 * @Author ken
 * @Time 2018/12/18 21:55
 * @Version 1.0
 */
public interface IUserService {

    User login(String username, String password);

    int updatePassword(String newPassword, String oldPassword, Integer id);
}
