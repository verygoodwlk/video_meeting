package com.media.video_meeting.entity;

import java.io.Serializable;

public class Scheme implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scheme.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column scheme.schemename
     *
     * @mbggenerated
     */
    private String schemename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table scheme
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scheme.id
     *
     * @return the value of scheme.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scheme.id
     *
     * @param id the value for scheme.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column scheme.schemename
     *
     * @return the value of scheme.schemename
     *
     * @mbggenerated
     */
    public String getSchemename() {
        return schemename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column scheme.schemename
     *
     * @param schemename the value for scheme.schemename
     *
     * @mbggenerated
     */
    public void setSchemename(String schemename) {
        this.schemename = schemename == null ? null : schemename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
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
        Scheme other = (Scheme) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchemename() == null ? other.getSchemename() == null : this.getSchemename().equals(other.getSchemename()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchemename() == null) ? 0 : getSchemename().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scheme
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
        sb.append(", schemename=").append(schemename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}