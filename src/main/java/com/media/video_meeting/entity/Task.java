package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    private Integer id;
    private String taskid;
    private String solution;
    private String account;
    private Integer taskpriority;
    private String taskname;
    private Integer taskclassify;
    private Integer playorder;
    private String startdate;
    private String starttime;
    private String stopdate;
    private Integer isenddate;
    private Integer duration;
    private Integer isduration;
    private Integer startinadvance;
    private String mp3;
    private Integer looptype;
    private Integer muiscduration;
    private Integer samll;
    private Integer volume;
    private Integer keybind;
    private Integer weekmask;
    private String users;
    private String collectionclient;
    private String area;
    private Integer fireterminal;
    private Integer port;
    private Integer islevel;
    private Integer isexternalmusic;
    private String externalfireterminal;
    private String info;
    private Integer reporter;
    private Integer speed;
    private Integer loopnum;
    private Integer tasktype;
    private Integer status;
}