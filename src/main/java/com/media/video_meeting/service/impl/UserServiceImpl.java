package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.UserMapper;
import com.media.video_meeting.entity.User;
import com.media.video_meeting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ken
 * @Time 2018/12/18 21:56
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {

        User user = userMapper.queryByUserName(username);
        System.out.println(user);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword, Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(!user.getPassword().equals(oldPassword)){
            return 0;//旧密码错误
        }

        user.setPassword(newPassword);
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
