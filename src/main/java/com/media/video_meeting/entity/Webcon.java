package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Webcon implements Serializable {
    private String account;
    private String password;
    private String nickname;
    private Integer priority;
    private String proxy;
    private String clients;
    private Integer solutionid;
}