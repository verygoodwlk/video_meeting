package com.media.video_meeting.util;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.BroadCast;
import com.media.video_meeting.entity.DeviceStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/6 22:41
 */
@Component
public class PushUtil {

    @Value("${push.appId}")
    public String appId;

    @Value("${push.secretKey}")
    public String secretKey;

    @Value("${push.url.broadcast}")
    public String broadcast;

    @Value("${push.url.deviceStatus}")
    public String deviceStatus;


    /**
     * 播放状态推送
     * @return
     */
    public String pushBroadcast(BroadCast broadCast){
        String result = sendUrl(broadcast, broadCast);
        return result;
    }

    /**
     * 广播系统设备状态
     */
    public String pushDeviceStatus(DeviceStatus deviceStatusObj){
        String result = sendUrl(deviceStatus, deviceStatusObj);
        return result;
    }

    /**
     * 发送请求
     * @param sendUrl
     * @param obj
     * @return
     */
    public String sendUrl(String sendUrl, Object obj){
        String content = JSON.toJSONString(obj);
        int nonc = (int)(Math.random() * 900000 + 100000);
        String url = sendUrl + "?appId=" + appId + "&nonc=" + nonc + "&sign=" + MD5Util.md5(content + nonc + secretKey);
        System.out.println(url);
        String result = HttpUtil.sendJson(url, content);
        return result;
    }
}
