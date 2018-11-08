package com.media.video_meeting.util;

import org.slf4j.Logger;

/**
 * @Author ken
 * @Time 2018/11/8 16:30
 * @Version 1.0
 */
public class LogUtil {

    public static void info(Logger logger, String info){
        logger.info(info);
    }

    public static void debug(Logger logger, String debug){
        logger.debug(debug);
    }

    public static void error(Logger logger, String error, Throwable e){
        logger.error(error);
        logger.error(getException(e));
    }

    public static String getException(Throwable e) {
        StackTraceElement[] ste = e.getStackTrace();
        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage() + " ");
        for (int i = 0; i < ste.length; i++) {
            sb.append(ste[i].toString() + "\n");
        }
        return sb.toString();
    }

}
