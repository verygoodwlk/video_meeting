package com.media.video_meeting.entity;

import java.io.Serializable;

/**
 *
 * 树形节点
 * @Author ken
 * @Time 2018/12/19 23:11
 * @Version 1.0
 */
public class TreeNode implements Serializable {

    private Integer id;
    private Integer userid;
    private Integer pid;
    private String gname;
    private String isParent = "true";
    private String icon;
    private Integer isClient; // 0 - 不是终端   1 - 是终端

    public TreeNode() {
    }

    public TreeNode(Integer id, Integer userid, Integer pid, String gname, String isParent, String icon, Integer isClient) {
        this.id = id;
        this.userid = userid;
        this.pid = pid;
        this.gname = gname;
        this.isParent = isParent;
        this.icon = icon;
        this.isClient = isClient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsClient() {
        return isClient;
    }

    public void setIsClient(Integer isClient) {
        this.isClient = isClient;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
