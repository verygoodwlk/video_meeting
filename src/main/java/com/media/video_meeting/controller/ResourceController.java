package com.media.video_meeting.controller;

import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author ken
 * @Date 2019/2/24
 * @Version 1.0
 */
@RestController
@RequestMapping("/res")
@Slf4j
public class ResourceController {

    @Value("${upload.path}")
    private String uploadPath;

    /**
     * 上传音频文件
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @SysLog(value = LogType.INSERT, info = "上传了一个音频文件")
    public String uploadFile(MultipartFile file){

        log.info("上传了一个文件：" + file.getOriginalFilename() + " 文件大小为：" + file.getSize());

        File files = new File(uploadPath);
        if(files.isDirectory() && !files.exists()){
            files.mkdirs();
        }
        files = new File(files, file.getOriginalFilename());

        try (
                InputStream in = file.getInputStream();
                OutputStream out = new FileOutputStream(files);
            ){

            IOUtils.copy(in, out);

        } catch (IOException e) {
            log.error("上传文件失败！" + file.getOriginalFilename(), e);
            return "error";
        }

        //返回上传的绝对路径
        return files.getAbsolutePath();
    }
}
