package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("meeting_moreinfo")
public class MeetingMoreInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer mid;
    private Integer mpic;
    private Integer webpic;
    private Integer mpicmode;
    private Integer grouptype;
    private String gourpip;
    private Integer groupportyp;
    private Integer groupportzsp;
    private Integer groupportfsp;
    private Integer spnl;
    private Integer ypnl;
    private Integer hycc;
    private Integer zdhjzd;
    private Integer zdsdcjhy;
    private Integer fspml;
    private Integer fsplx;
    private Integer fspnl;
    private Integer gbyzk;
    private Integer isopenipzdjs;
    private Integer ypjmsf;
    private Integer zspjmsf;
    private Integer fspjmsf;
    private static final long serialVersionUID = 1L;
}