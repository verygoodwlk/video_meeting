package com.media.video_meeting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author ken
 * @Time 2018/11/14 17:27
 * @Version 1.0
 */
public class NetworkUtil {

    private static final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * @param ip
     * @param time
     * @return
     */
    public static String ping(String ip, String time, String os){

        try {
            if (StringUtil.isNotEmpty(ip) || StringUtil.isNotEmpty(time)) {

                String pingStr = "ping ";

                if (os.equalsIgnoreCase("linux")){
                    pingStr += " -c " + time + " " + ip;
                } else if(os.equalsIgnoreCase("window")){
                    pingStr += " -n " + time + " " + ip;
                }

                Runtime runtime = Runtime.getRuntime();
                BufferedReader in = null;
                Process exec = null;
                try {
                    exec = runtime.exec(pingStr);
                    in = new BufferedReader(new InputStreamReader(exec.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String str = null;
                    while((str = in.readLine()) != null){
                        if(sb.length() > 0){
                            sb.append("<br/>");
                        }
                        sb.append(str);
                    }

                    System.out.println(sb.toString());

                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(in != null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(exec != null){
                        exec.destroy();
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.error(logger, "Ping检测异常", e);
        }

        return "参数异常！";
    }
}
