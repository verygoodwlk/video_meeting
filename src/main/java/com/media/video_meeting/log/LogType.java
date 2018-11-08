package com.media.video_meeting.log;

/**
 * @Author ken
 * @Time 2018/11/8 15:42
 * @Version 1.0
 */
public enum LogType {
    LOGIN(1),
    LOGOUT(2),
    INSERT(3),
    UPDATE(4),
    DELETE(5);

    private Integer type;

    LogType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return type;
    }

    public LogType queryLogType(Integer type){
        LogType[] values = values();
        for (LogType value : values) {
            if(value.getType() == type){
                return value;
            }
        }
        return null;
    }
}
