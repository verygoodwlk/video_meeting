package com.media.video_meeting.log;

import com.media.video_meeting.entity.SystemLog;
import com.media.video_meeting.service.ISystemLogService;
import com.media.video_meeting.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author ken
 * @Time 2018/11/8 15:35
 * @Version 1.0
 */
@Aspect
public class LogAop {

    private static Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    private ISystemLogService logService;

    @Around("@annotation(SysLog)")
    public Object log(ProceedingJoinPoint joinPoint){
        //获取目标方法上的自定义注解IsLogin
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.getAttribute("loginuser");

        //创建系统日志对象
        SystemLog systemLog = new SystemLog();
        systemLog.setUser("admin");
        systemLog.setTime(new Date());
        systemLog.setType(sysLog.value().getType());
        systemLog.setInfo(sysLog.info());

        Object result = "error";
        try {
            result = joinPoint.proceed();
            systemLog.setFlag(1);//成功
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            systemLog.setFlag(0);//失败
            systemLog.setException(throwable.toString());
        }

        try {
            logService.insertLog(systemLog);
        } catch (Exception e) {
            LogUtil.error(logger, "记录系统日志出现异常", e);
        }
        return result;
    }
}
