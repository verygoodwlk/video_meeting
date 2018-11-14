package com.media.video_meeting.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author ken
 * @Time 2018/11/13 16:34
 * @Version 1.0
 */
public class SearchLog {

    private Integer typeVal;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date mintime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date maxtime;

    private Long mtime;
    private Long matime;

    private String minStr;
    private String maxStr;

    public Integer getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(Integer typeVal) {
        this.typeVal = typeVal;
    }

    public Date getMintime() {
        return mintime;
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public void setMintime(Date mintime) {
        this.mintime = mintime;
        if(mintime != null) {
            this.setMtime(mintime.getTime());
            this.setMinStr(simpleDateFormat.format(mintime));
        }
    }

    public Date getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(Date maxtime) {
        this.maxtime = maxtime;
        if(maxtime != null) {
            this.setMatime(maxtime.getTime());
            this.setMaxStr(simpleDateFormat.format(maxtime));
        }
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public Long getMatime() {
        return matime;
    }

    public void setMatime(Long matime) {
        this.matime = matime;
    }

    public String getMinStr() {
        return minStr;
    }

    public void setMinStr(String minStr) {
        this.minStr = minStr;
    }

    public String getMaxStr() {
        return maxStr;
    }

    public void setMaxStr(String maxStr) {
        this.maxStr = maxStr;
    }
}
