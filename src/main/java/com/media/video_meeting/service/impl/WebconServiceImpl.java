package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.WebconMapper;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IWebconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author ken
 * @Date 2019/3/11
 * @Version 1.0
 */
@Service
public class WebconServiceImpl implements IWebconService {

    @Autowired
    private WebconMapper webconMapper;

    @Override
    public List<Webcon> getWebconList() {
        return webconMapper.selectList(null);
    }

    @Override
    public int insertWebCon(Webcon webcon) {
        if(queryByAccount(webcon.getAccount()) == null){
            //不存在账号添加
            return webconMapper.insert(webcon);
        } else {
            //存在账号修改
            return webconMapper.updateById(webcon);
        }
    }

    @Override
    public Webcon queryByAccount(String account) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        return webconMapper.selectOne(queryWrapper);
    }

    @Override
    public int deleteAccounts(String[] accounts) {
        return webconMapper.deleteBatchIds(Arrays.asList(accounts));
    }

    @Override
    public int updateWebcon(Webcon webcon) {
        return webconMapper.updateById(webcon);
    }
}
