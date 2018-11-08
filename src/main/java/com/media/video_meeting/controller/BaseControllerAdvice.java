package com.media.video_meeting.controller;

import com.media.video_meeting.util.LogUtil;
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
        LogUtil.error(logger, "controller运行出现异常", e);
        return "error";
    }


}