package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 广播实体类
 *
 * @version 1.0
 * @user ken
 * @date 2019/8/28 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BroadCast implements Serializable {

    private String songName;
    private String totalLength;
    private String pos;
}
