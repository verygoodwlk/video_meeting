package com.media.video_meeting.websocket_aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketSend {

    String[] params() default {};

    Class<? extends ISocketSend> sendClass();
}
