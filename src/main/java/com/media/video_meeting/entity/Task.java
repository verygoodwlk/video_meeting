package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;
    private String taskid;
    private String solution;
    private String account;
    private int taskt;//任务类型 1 - 定时音乐 2 - 定时采集 3 - 消防报警 4 - 语音合成 5 - 实时音乐
    private int taskpriority;
    private String taskname;
    private int taskclassify;
    private int playorder;
    private String startdate;
    private String starttime;
    private String stopdate;
    private int isenddate;
    private int duration;
    private int isduration;
    private int startinadvance;
    private String mp3;
    private int looptype;
    private int muiscduration;
    private int samll;
    private int volume;
    private int keybind;
    private int weekmask;
    private String users;
    private String collectionclient;
    private String area;
    private int fireterminal;
    private int port;
    private int islevel;
    private int isexternalmusic;
    private String externalfireterminal;
    private String info;
    private int reporter;
    private int speed;
    private int loopnum;
    private int tasktype;
    private int status;

//    public List<String> getMp3(){
//        return JSON.parseArray(mp3, String.class);
//    }
//
//    public List<String> getUsers(){
//        return JSON.parseArray(users, String.class);
//    }
}