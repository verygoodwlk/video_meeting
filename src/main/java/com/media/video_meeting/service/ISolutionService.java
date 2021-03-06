package com.media.video_meeting.service;

import com.media.video_meeting.entity.Solution;

import java.util.List;

/**
 * @Author ken
 * @Time 2019/3/11 23:55
 * @Version 1.0
 */
public interface ISolutionService {

    List<Solution> queryByAccount(String account);

    Solution queryById(Integer id);

    Solution queryByName(String name);
}
