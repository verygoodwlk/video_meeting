package com.media.video_meeting.service;

import com.media.video_meeting.entity.Model;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/19 16:51
 * @Version 1.0
 */
public interface IModelService {

    List<Model> queryAll();

    int saveOrUpdate(Model model);
}
