package com.media.video_meeting.controller;

import com.alibaba.fastjson.JSON;
import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import com.media.video_meeting.websocket.MyWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/apk")
@Slf4j
public class ApkController {

    @Value("${upload.apkpath}")
    private String uploadPath;

    @Autowired
    private MyWebSocket myWebSocket;

    @RequestMapping("/upload")
    @SysLog(value = LogType.INSERT, info = "apk新版本上传")
    public String apkUpload(@RequestParam("file") MultipartFile file) throws Exception {

        log.info("upload file: 上传了一个文件->" + file.getOriginalFilename() + " 文件大小为：" + file.getSize());


        File files = new File(uploadPath);
        if(!files.exists()){
            files.mkdirs();
        }
        files = new File(files, file.getOriginalFilename());

        try (
                InputStream in = file.getInputStream();
                OutputStream out = new FileOutputStream(files);
        ){

            IOUtils.copy(in, out);

        } catch (IOException e) {
            log.error("upload file error: 上传文件失败！" + file.getOriginalFilename(), e);
            throw new Exception("apk上传失败");
        }

        return "redirect:/topage/apkmanager?info=succ";
    }

    /**
     * 更新设备apk
     * @return
     */
    @RequestMapping("/updateClient")
    @ResponseBody
    public String updateClient(@RequestParam("clients[]") String[] clients){

        String str = JSON.toJSONString(clients);

        String json = "{\"id\":\"updateApkVersion\", \"account\":\"admin\", \"users\":" + str + "}";
        log.info("updateapk: 更新终端的apk版本" + json);
        if(myWebSocket.isOpen()){
            myWebSocket.send(json);
        } else {
            return "error";
        }

        return "succ";
    }
}
