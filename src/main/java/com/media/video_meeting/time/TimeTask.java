package com.media.video_meeting.time;

import com.media.video_meeting.util.TaskStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * 定时任务
 *
 * @version 1.0
 * @user ken
 * @date 2019/6/2 1:14
 */
@Component
public class TimeTask {

    @Autowired
    private TaskStatusUtil taskStatusUtil;

    /**
     * 每秒 修改任务的当前进度
     */
    @Scheduled(fixedDelay = 1000)
    public void timeTask(){
        taskStatusUtil.addNowDurationTask();
    }
}
