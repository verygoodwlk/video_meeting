package com.media.video_meeting.websocket_aop.send;

import com.media.video_meeting.entity.Task;
import com.media.video_meeting.websocket_aop.ISocketSend;

import java.util.Map;

/**
 * 实时音乐,新建任务 //列表循环1， 随机播放2，默认随机播放
 * {"id":"playMusicTask","account":"admin", "taskname":"Region",
 * "loopType":1, "volume":"8", "mp3":["1.mp3","2.mp3","G.E.M.邓紫棋 - 喜欢你.mp3"] ,
 * "terminal":["1", "2", "3"]}
 *
 * 定时音乐
 * 16.新建任务  WEEK_MONDAY = 2,WEEK_TUESDAY = 4,WEEK_WEDNESDAY = 8,WEEK_THURSDAY = 16,WEEK_FRIDAY = 32,WEEK_SATURDAY = 64,WEEK_SUNDAY = 128,
 * solution归属方案，taskPriority优先级，loopType循环类型，stopDate结束日期，音量stopDate， 开始时间startTime，持续时间duration， playOrder任务类型，status任务状态，taskid任务id，samll音量微调，开始日期startDate，星期选择weekMask
 * {"id":"addTask","account":"admin", "solution":"Default Solution", "taskname":"Region",
 * "taskPriority":0,"loopType":1,"stopDate":"2019-06-10","volume":0,"mp3":["1.mp3","2.mp3"],
 * "startTime":"16:22:53","duration":1800,"playOrder":1,"users":["1"],"status":0,"samll":0,"startDate":"2018-12-14","weekMask":254}
 * 返回
 * {"id":"addTaskResponse","response":"success","account":"admin","taskid":"145","solution":"Default Solution", "taskname":"Region","taskPriority":0,"loopType":1,"stopDate":"2019-06-10","volume":0,"mp3":["1.mp3","2.mp3"],"startTime":"16:22:53","duration":1800,"playOrder":1,"users":["1"],"status":0,"samll":0,"startDate":"2018-12-14","weekMask":254}
 * {"id":"addTaskResponse","response":"fail","account":"admin"}
 *
 * 定时采集
 * {"id":"addCollectTask", "account":"admin","taskid":"145", "taskPriority":0,"taskname":"Region",
 * "stopDate":"2019-06-10","startTime":"16:22:53","duration":1800,"playOrder":1,"users":["1"],
 * "status":0,"startDate":"2018-12-14","weekMask":254}
 *
 * 消防报警
 * {"id":"addFireTask", "account":"admin","taskid":"145","taskname":"火警", "area":"A区",
 * "fireTerminal":"1","port":"1","isLevel":"0","mp3":["1.mp3","2.mp3"],
 * "isExternalMusic":"1","externalFireTerminal":"10","users":["1"]}
 *
 * 语音合成
 * {"id":"addVoiceTask","account":"admin","taskid":"145","taskname":"Region","info":"欢迎光临", "reporter":"1",
 * "speed":"50", "loopNum":"1", "taskPriority":0,"taskType":1,"stopDate":"2019-06-10",
 * "startTime":"16:22:53","users":["1"],"startDate":"2018-12-14","weekMask":254, "status":"0"}
 *
 *
 * 任务类型 1 - 定时音乐 2 - 定时采集 3 - 消防报警 4 - 语音合成 5 - 实时音乐
 */
public class MusicUpdateSocketSend extends ISocketSend {

    @Override
    public Map<String, Object> sendMsg(Object... objs) {
        Task task = (Task) objs[0];

        Map<String, Object> map = task2Map(task);
        if(task.getTaskt() == 5){
            map.put("id", "modifyMusicTask");
        } else if(task.getTaskt() == 1){
            map.put("id", "modifyTask");
        } else if(task.getTaskt() == 2){
            //定时采集
            map.put("id", "modifyCollectTask");
        } else if(task.getTaskt() == 3){
            //消防报警
            map.put("id", "modifyFireTask");
        } else if(task.getTaskt() == 4){
            //语音合成
            map.put("id", "modifyVoiceTask");
        }

        return map;
    }
}
