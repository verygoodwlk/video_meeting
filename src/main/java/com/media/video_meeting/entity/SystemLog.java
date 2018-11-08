package com.media.video_meeting.entity;

import java.io.Serializable;

public class SystemLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.user
     *
     * @mbggenerated
     */
    private String user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.time
     *
     * @mbggenerated
     */
    private Long time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.flag
     *
     * @mbggenerated
     */
    private Integer flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.info
     *
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemlog.exception
     *
     * @mbggenerated
     */
    private String exception;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table systemlog
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.id
     *
     * @return the value of systemlog.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.id
     *
     * @param id the value for systemlog.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.user
     *
     * @return the value of systemlog.user
     *
     * @mbggenerated
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.user
     *
     * @param user the value for systemlog.user
     *
     * @mbggenerated
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.type
     *
     * @return the value of systemlog.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.type
     *
     * @param type the value for systemlog.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.time
     *
     * @return the value of systemlog.time
     *
     * @mbggenerated
     */
    public Long getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.time
     *
     * @param time the value for systemlog.time
     *
     * @mbggenerated
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.flag
     *
     * @return the value of systemlog.flag
     *
     * @mbggenerated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.flag
     *
     * @param flag the value for systemlog.flag
     *
     * @mbggenerated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.info
     *
     * @return the value of systemlog.info
     *
     * @mbggenerated
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.info
     *
     * @param info the value for systemlog.info
     *
     * @mbggenerated
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemlog.exception
     *
     * @return the value of systemlog.exception
     *
     * @mbggenerated
     */
    public String getException() {
        return exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemlog.exception
     *
     * @param exception the value for systemlog.exception
     *
     * @mbggenerated
     */
    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemlog
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
        SystemLog other = (SystemLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
            && (this.getException() == null ? other.getException() == null : this.getException().equals(other.getException()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemlog
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getException() == null) ? 0 : getException().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemlog
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
        sb.append(", user=").append(user);
        sb.append(", type=").append(type);
        sb.append(", time=").append(time);
        sb.append(", flag=").append(flag);
        sb.append(", info=").append(info);
        sb.append(", exception=").append(exception);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}