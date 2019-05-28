package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.dao.TaskMapper;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.service.ISolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Solution> queryByAccount(String account) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.or();
        queryWrapper.isNull("account");
        return solutionMapper.selectList(queryWrapper);
    }

    @Override
    public List<Solution> querySolutionsList() {
        return solutionMapper.selectList(null);
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

    @Override
    public int insertSolution(Solution solution) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("solutionname", solution.getSolutionname());
        queryWrapper.eq("account", solution.getAccount());
        Solution s = solutionMapper.selectOne(queryWrapper);
        if(s != null){
            return -1;
        }
        return solutionMapper.insert(solution);
    }

    @Override
    @Transactional
    public int updateSolution(Solution solution, String oldName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("solutionname", solution.getSolutionname());
        queryWrapper.eq("account", solution.getAccount());
        Solution s = solutionMapper.selectOne(queryWrapper);
        if(s != null){
            return -1;
        }
        int result = solutionMapper.updateByName(oldName, solution.getSolutionname(), solution.getAccount());
        if(result > 0){
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("solution", oldName);
            queryWrapper1.eq("account", solution.getAccount());

            List<Task> list = taskMapper.selectList(queryWrapper1);
            for (Task task : list) {
                task.setSolution(solution.getSolutionname());
                taskMapper.updateById(task);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteSolution(Solution solution) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("solutionname", solution.getSolutionname());
        queryWrapper.eq("account", solution.getAccount());
        int result = solutionMapper.delete(queryWrapper);

        if(result > 0){
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("solution", solution.getSolutionname());
            queryWrapper2.eq("account", solution.getAccount());

            taskMapper.delete(queryWrapper2);
        }

        return result;
    }
}
