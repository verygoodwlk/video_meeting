package com.media.video_meeting.page;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ken
 * @Time 2018/11/8 10:05
 * @Version 1.0
 */
@Aspect
public class PageAop {

    @Value("${page.size}")
    private Integer pageSize;


    @Around("@annotation(PageHelper)")
    public Object pageAop(ProceedingJoinPoint joinPoint) throws Throwable {

        //获得所有参数
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            //放行
            return joinPoint.proceed();
        }

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();

        for (int i = 0; i < args.length; i++) {
            if(args[i] != null && args[i].getClass() == Page.class){
                Page page = (Page) args[i];

                page.setPageSize(pageSize);

                //动态设置page的url
                String url = request.getRequestURL().toString();
                //获得传递过来的参数
                String params = request.getQueryString();
                if(params == null){
                    url += "?1=1";
                } else {
                    url += "?" + params;
                }

                if(url.indexOf("&page=") != -1){
                    url = url.substring(0, url.indexOf("&page="));
                }
                page.setUrl(url);
                args[i] = page;

                //开始分页
                PagePlugin.startPage(page);

                break;
            }
        }

        return joinPoint.proceed(args);
    }
}
