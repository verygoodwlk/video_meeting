package com.media.video_meeting.websocket.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.media.video_meeting.dao.ClientGroupMapper;
import com.media.video_meeting.entity.ClientGroup;
import com.media.video_meeting.service.IClientService;
import com.media.video_meeting.websocket.SocketMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分组管理（新增、删除、修改）
 *
 * {"id": "webconterminalGroup","account":"admin",
 * "groups": [
 *  {"gname": "默认分组","level": 1,"clients": ["1", "2", "3"],
 *      "groups": [
 *          {"gname": "分组1","level": 2,"clients": [],
 *              "groups": [
 *                  {"gname": "分组2","level": 3,"clients": ["4"],"groups": []}]}]}]}
 *
 * @Author ken
 * @Date 2019/2/27
 * @Version 1.0
 */
@Component("webconterminalGroup")
public class SocketMsgGroupManagerHandler extends SocketMsgHandler {


    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientGroupMapper clientGroupMapper;

    @Override
    public void handlerMsg(String msg, JSONObject jsonObject) throws Exception {
        //清空所有的分组以及分组与终端的关系
        clientGroupMapper.deleteAllGroup();
        clientGroupMapper.deleteAllGroupAndClients();

        //根据json获得分组集合json
        List<ClientGroup> groupsList = new ArrayList<>();
        JSONArray groups = jsonObject.getJSONArray("groups");
        //解析并且插入所有的终端组与终端
        parseJSONArray(groups, null);
    }

    @Override
    public void exception(Throwable t, String msg, JSONObject jsonObject) {

    }

    /**
     * 将json字符串转换成实体类对象
     */
    private void parseJSONArray(JSONArray groups, ClientGroup clientGroup){
        if(groups == null || groups.size() <= 0){
            return;
        }

        for(int i = 0; i < groups.size(); i++){
            //循环出一个分组
            JSONObject groupJsonObject = groups.getJSONObject(i);
            //将分组的json转换成Group对象
            ClientGroup cGroup = new ClientGroup();
            //设置当前群组的父分组、组名称、终端数量
            cGroup.setPid(clientGroup != null ? clientGroup.getId() : -1);
            cGroup.setGname(groupJsonObject.getString("gname"));
            cGroup.setCnumber(0);
            //将该对象保存到数据中
            clientService.addGroup(cGroup);
            //处理该Group下的终端
            JSONArray clients = groupJsonObject.getJSONArray("clients");
            if(clients != null && clients.size() > 0){
                for (int j = 0; j < clients.size(); i++){
                    //循环获得该分组下的所有终端id
                    int cid = clients.getInteger(j);
                    //
                    clientService.insertClientGroupTable(cGroup.getId(), cid);
                }
            }

            //处理该分组下的所有子分组
            JSONArray sonGroups = groupJsonObject.getJSONArray("groups");
            parseJSONArray(sonGroups, cGroup);
        }
    }
}
