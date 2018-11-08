package com.media.video_meeting.log;

import java.lang.annotation.*;

/**
 * 系统日志
 * @Author ken
 * @Time 2018/11/8 15:37
 * @Version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    //日志类型
    LogType value();

    //日志备注
    String info() default "";

}
