package com.media.video_meeting.websocket_aop;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.websocket.MyWebSocket;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class SocketAop {

    @Autowired
    private MyWebSocket myWebSocket;

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(SocketSend)")
    public Object sendSocket(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Webcon webcon = (Webcon) session.getAttribute("account");
//        System.out.println("session中的值：" + account);

        //获得目标方法的注解
        Method method = getMethod(joinPoint);

        //获得方法上的注解
        SocketSend annotation = method.getAnnotation(SocketSend.class);

        //获得注解上的参数
        String[] params = annotation.params();
        List<Object> paramsList = new ArrayList<>();
        if(params.length > 0){
            for (String param : params){
                Object value = null;
                if(param.equals("#result")){
                    value = result;
                } else {
                    value = parseSpel(method, joinPoint.getArgs(), param);
                }

                if(value != null){
                    paramsList.add(value);
                }
            }
        }

//        System.out.println("获得实际参数：" + paramsList);

        //获得实际处理的类
        Class<? extends ISocketSend> socketClass = annotation.sendClass();
        ISocketSend iSocketSend = socketClass.newInstance();
        Map<String, Object> map = iSocketSend.sendMsg(paramsList.toArray());

        //获得处理的结果，并且封装信息
        if(webcon != null){
            map.put("account", webcon.getAccount());
        }
        String json = JSON.toJSONString(map);
        System.out.println("websocket发送json：" + json);

        //发送json
        if(map != null && myWebSocket.isOpen()){
            myWebSocket.send(json);
        }

        return result;
    }

    /**
     * 获取目标的方法
     * @param joinPoint
     * @return
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

    /**
     * 解析 spel 表达式
     *
     * @param method    方法
     * @param arguments 参数
     * @param spel      表达式
     * @return 执行spel表达式后的结果
     */
    private Object parseSpel(Method method, Object[] arguments, String spel) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], arguments[len]);
        }
        try {
            Expression expression = parser.parseExpression(spel);
            return expression.getValue(context);
        } catch (Exception e) {
            return null;
        }
    }
}
