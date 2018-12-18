package com.media.video_meeting.controller;

import com.media.video_meeting.log.LogType;
import com.media.video_meeting.log.SysLog;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author ken
 * @Time 2018/11/20 17:41
 * @Version 1.0
 */
@Controller
@RequestMapping("/db")
public class DBController {

    @Value("${db.path}")
    private String path;

    /**
     * 数据库导出
     * @return
     */
    @RequestMapping("/export")
    @SysLog(value = LogType.UPDATE, info = "导出数据库记录！")
    public void export(HttpServletResponse response) throws Exception {
        InputStream in = null;
        ServletOutputStream out = null;
        try {
            File file = new File(path);
            in = new FileInputStream(path);

            String name = file.getName();
            response.setHeader("Content-disposition", "attachment;fileName="+name);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
        } finally {
            if(in != null){
                in.close();
            }

            if(out != null){
                out.close();
            }
        }
    }

    /**
     * 导入数据库
     * @return
     */
    @RequestMapping("/import")
    public String importFile(MultipartFile dbfile) throws Exception {

        if(dbfile.getSize() == 0){
            return "error";
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = dbfile.getInputStream();
            out = new FileOutputStream(path);
            IOUtils.copy(in, out);
        } finally {
            if(in != null){
                in.close();
            }

            if (out != null) {
                out.close();
            }
        }
        return "succ";
    }
}
