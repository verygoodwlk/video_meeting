package com.media.video_meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.media.video_meeting.dao.ClientMsgMapper;
import com.media.video_meeting.dao.SolutionMapper;
import com.media.video_meeting.dao.WebconMapper;
import com.media.video_meeting.entity.ClientMsg;
import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.IWebconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private ClientMsgMapper clientMsgMapper;

    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public List<Webcon> getWebconList() {
        return webconMapper.selectList(null);
    }

    @Override
    @Transactional
    public int insertWebCon(Webcon webcon) {
        if(queryByAccount(webcon.getAccount()) == null){

//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("solutionname", "Default Solution");
//            Solution s = solutionMapper.selectOne(queryWrapper);
//            if(s != null) {
//                //不存在账号添加
//                //创建该分控的默认方案
//                Solution solution = new Solution(0, "Default Solution", webcon.getAccount());
//                solutionMapper.insert(solution);
//            }

            //创建该分控的默认方案
            Solution solution = new Solution(0, "Default Solution", webcon.getAccount(), 1);
            solutionMapper.insert(solution);

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

    /**
     * 根据分控账号查询关联的终端
     * @return
     */
    @Override
    public List<ClientMsg> queryClientsByWebcon(String account) {
        //获得分控信息
        Webcon webcon = webconMapper.selectById(account);
        //获取分控终端信息
        String clients = webcon.getClients();


        List<ClientMsg> clientMsgs = new ArrayList<>();
        //获得分控的终端信息
        String[] split = clients.split("\\|");
        if(split != null){
            Arrays.stream(split).forEach((x) -> {
                //获得所有终端
                String[] sp = x.split("-");
                //根据终端id查询终端对象
                ClientMsg clientMsg = clientMsgMapper.selectByPrimaryKey(Integer.parseInt(sp[1]));
                if(clientMsg != null){
                    clientMsgs.add(clientMsg);
                }
            });
        }

        return clientMsgs;
    }
}
