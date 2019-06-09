package com.media.video_meeting.websocket_aop.send;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.Task;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 修改任务音量
 *
 * @version 1.0
 * @user ken
 * @date 2019/6/1 14:13
 */
public class UpdateVolumeSocketSend extends ISocketSend {
    @Override
    public Map<String, Object> sendMsg(Object... objs) {

        Task task = (Task) objs[0];
        int volume = (int) objs[1];

        String users = task.getUsers();
        List<String> strings = JSON.parseArray(users, String.class);


        Map<String, Object> map = new HashMap<>();
        map.put("id", "volume");
        map.put("num", volume + "");
        map.put("terminal", strings);

        return map;
    }
}
