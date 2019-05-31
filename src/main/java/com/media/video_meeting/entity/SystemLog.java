package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLog implements Serializable {
    private Integer id;
    private String user;
    private Integer type;
    private Date time;
    private Integer flag;
    private String info;
    private String exception;

}