package com.media.video_meeting.service.impl;

import com.media.video_meeting.dao.ModelMapper;
import com.media.video_meeting.dao.ModelSMapper;
import com.media.video_meeting.entity.Model;
import com.media.video_meeting.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/19 16:53
 * @Version 1.0
 */
@Service
public class ModelServiceImpl implements IModelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelSMapper modelSMapper;

    @Override
    public List<Model> queryAll() {
        return modelMapper.queryAll();
    }

    @Override
    public int saveOrUpdate(Model model) {
        return 0;
    }
}
