package com.media.video_meeting.controller;

import com.media.video_meeting.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author ken
 * @Time 2018/11/19 16:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private IModelService modelService;

    /**
     * 模块管理
     * @return
     */
    @RequestMapping("/list")
    public String modelManager(Model model){
        List<com.media.video_meeting.entity.Model> models = modelService.queryAll();
        model.addAttribute(models);
        return "modellist";
    }
}
