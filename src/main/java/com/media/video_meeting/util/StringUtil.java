package com.media.video_meeting.util;

/**
 * @Author ken
 * @Time 2018/11/8 14:20
 * @Version 1.0
 */
public class StringUtil {

    public static boolean isNotEmpty(String str){
        return str != null && !str.trim().equals("");
    }
}
