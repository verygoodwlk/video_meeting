package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private String host;
    private int type;
    private Date starttime;
    private Date endtime;
    private String record;

    @TableField(exist = false)
    private List<Integer> users = new ArrayList<>();

    public void addUserId(int cid){
        users.add(cid);
    }
}
