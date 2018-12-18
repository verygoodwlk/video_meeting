package com.media.video_meeting.entity;

import java.io.Serializable;
import java.util.List;

public class ClientGroup implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client_group.id
     *
     * @mbggenerated
     */
    private Integer id;
    private Integer userid;

    private String isParent = "true";

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client_group.pid
     *
     * @mbggenerated
     */
    private Integer pid = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client_group.gname
     *
     * @mbggenerated
     */
    private String gname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client_group.cnumber
     *
     * @mbggenerated
     */
    private Integer cnumber = 0;

    private List<ClientMsg> clientMsgs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_group
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client_group.id
     *
     * @return the value of client_group.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client_group.id
     *
     * @param id the value for client_group.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client_group.pid
     *
     * @return the value of client_group.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client_group.pid
     *
     * @param pid the value for client_group.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client_group.gname
     *
     * @return the value of client_group.gname
     *
     * @mbggenerated
     */
    public String getGname() {
        return gname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client_group.gname
     *
     * @param gname the value for client_group.gname
     *
     * @mbggenerated
     */
    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client_group.cnumber
     *
     * @return the value of client_group.cnumber
     *
     * @mbggenerated
     */
    public Integer getCnumber() {
        return cnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client_group.cnumber
     *
     * @param cnumber the value for client_group.cnumber
     *
     * @mbggenerated
     */
    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_group
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClientGroup other = (ClientGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getGname() == null ? other.getGname() == null : this.getGname().equals(other.getGname()))
            && (this.getCnumber() == null ? other.getCnumber() == null : this.getCnumber().equals(other.getCnumber()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_group
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getGname() == null) ? 0 : getGname().hashCode());
        result = prime * result + ((getCnumber() == null) ? 0 : getCnumber().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_group
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", gname=").append(gname);
        sb.append(", cnumber=").append(cnumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public List<ClientMsg> getClientMsgs() {
        return clientMsgs;
    }

    public void setClientMsgs(List<ClientMsg> clientMsgs) {
        this.clientMsgs = clientMsgs;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
}