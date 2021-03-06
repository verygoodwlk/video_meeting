package com.media.video_meeting.entity;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column model.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column model.mname
     *
     * @mbggenerated
     */
    private String mname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column model.mstate
     *
     * @mbggenerated
     */
    private Integer mstate;

    private List<ModelS> modelS;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table model
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column model.id
     *
     * @return the value of model.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column model.id
     *
     * @param id the value for model.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column model.mname
     *
     * @return the value of model.mname
     *
     * @mbggenerated
     */
    public String getMname() {
        return mname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column model.mname
     *
     * @param mname the value for model.mname
     *
     * @mbggenerated
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column model.mstate
     *
     * @return the value of model.mstate
     *
     * @mbggenerated
     */
    public Integer getMstate() {
        return mstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column model.mstate
     *
     * @param mstate the value for model.mstate
     *
     * @mbggenerated
     */
    public void setMstate(Integer mstate) {
        this.mstate = mstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table model
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
        Model other = (Model) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMname() == null ? other.getMname() == null : this.getMname().equals(other.getMname()))
            && (this.getMstate() == null ? other.getMstate() == null : this.getMstate().equals(other.getMstate()));
    }

    public List<ModelS> getModelS() {
        return modelS;
    }

    public void setModelS(List<ModelS> modelS) {
        this.modelS = modelS;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table model
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMname() == null) ? 0 : getMname().hashCode());
        result = prime * result + ((getMstate() == null) ? 0 : getMstate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table model
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
        sb.append(", mname=").append(mname);
        sb.append(", mstate=").append(mstate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}