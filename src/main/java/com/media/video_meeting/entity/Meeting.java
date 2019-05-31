package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("meeting")
public class Meeting implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String number;
    private Integer time;
    @TableField(exist = false)
    private String timeStr;
    private Integer isfoerver = 0;
    private Integer ml;
    private String password;
    private Integer type;
    private Integer fs;
    private Integer lk;
    private String info;

    @TableField(exist = false)
    private Integer[] client_ids;//终端列表
    @TableField(exist = false)
    private Integer client_start;//发起者终端编号
    private Integer hystate = 1;
    private Date createtime = new Date();


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public void setTime(Integer time) {
        this.time = time;
        StringBuilder sb = new StringBuilder();
        int h = time/1000/60/60;
        int m = time/1000/60%60;
        this.setTimeStr(sb.append(h).append("时").append(m).append("分").toString());
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }


}