package com.media.video_meeting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author ken
 * @Time 2018/11/7 17:00
 * @Version 1.0
 */
@ControllerAdvice
public class BaseControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        logger.error("运行出现异常：" + getException(e));
        return "error";
    }

    public static String getException(Exception e) {
        StackTraceElement[] ste = e.getStackTrace();
        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage() + " ");
        for (int i = 0; i < ste.length; i++) {
            sb.append(ste[i].toString() + "\n");
        }
        return sb.toString();
    }

}