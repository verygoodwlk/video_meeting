package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.service.ISolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ken
 * @Date 2019/3/11
 * @Version 1.0
 */
@Service
public class SolutionServiceImpl implements ISolutionService {

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public List<Solution> queryByAccount(String account) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        return solutionMapper.selectList(queryWrapper);
    }

    @Override
    public Solution queryById(Integer id) {
        return solutionMapper.selectById(id);
    }

    @Override
    public Solution queryByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("solutionname", name);
        return solutionMapper.selectOne(queryWrapper);
    }
}
