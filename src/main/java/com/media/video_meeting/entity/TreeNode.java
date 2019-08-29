package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 树形节点
 * @Author ken
 * @Time 2018/12/19 23:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode implements Serializable {

    private Integer id;
    private Integer userid;
    private Integer pid;
    private String gname;
    private String isParent = "true";
    private String icon;
    private Integer isClient; // 0 - 不是终端   1 - 是终端
    private Integer isOnLine; // 0 - 未上线 1 - 上线
    private Integer hosts;// 0 - 非主机  1 - 是主机
    private boolean checked;

}
