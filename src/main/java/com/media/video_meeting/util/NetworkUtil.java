package com.media.video_meeting.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author ken
 * @Time 2018/11/14 17:27
 * @Version 1.0
 */
public class NetworkUtil {



    /**
     * @param ip
     * @param time
     * @return
     */
    public static String ping(String ip, Integer time, String os){

        if (StringUtil.isNotEmpty(ip)) {

            String pingStr = "ping ";

            if (os.equalsIgnoreCase("linux")){
                pingStr += " -c " + time + " " + ip;
            } else if(os.equalsIgnoreCase("window")){
                pingStr += " -n " + time + " " + ip;
            }

            Runtime runtime = Runtime.getRuntime();
            BufferedReader in = null;
            try {
                Process exec = runtime.exec(pingStr);
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
            }
        }

        return null;
    }
}
