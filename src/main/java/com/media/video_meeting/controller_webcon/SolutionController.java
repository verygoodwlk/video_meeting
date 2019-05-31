package com.media.video_meeting.controller_webcon;


import com.media.video_meeting.entity.Solution;
import com.media.video_meeting.entity.Webcon;
import com.media.video_meeting.service.ISolutionService;
import com.media.video_meeting.websocket_aop.SocketSend;
import com.media.video_meeting.websocket_aop.send.StartSolutionTaskSocketSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/web/solution")
public class SolutionController {


    @Autowired
    private ISolutionService solutionService;

    /**
     * 获得方案列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Solution> queryList(@SessionAttribute("account") Webcon webcon){
        List<Solution> solutions = solutionService.queryByAccount(webcon.getAccount());
        return solutions;
    }


    @RequestMapping("/insert")
    @ResponseBody
    public int insertSolut(Solution solution, @SessionAttribute("account") Webcon webcon){
        solution.setAccount(webcon.getAccount());
        return solutionService.insertSolution(solution);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateSolut(Solution solution, @SessionAttribute("account") Webcon webcon, String oldName){
        solution.setAccount(webcon.getAccount());
        return solutionService.updateSolution(solution, oldName);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int deleteSolut(Solution solution, @SessionAttribute("account") Webcon webcon){
        solution.setAccount(webcon.getAccount());
        return solutionService.deleteSolution(solution);
    }

    @RequestMapping("/exec")
    @ResponseBody
    @SocketSend(params = {"#solution"}, sendClass = StartSolutionTaskSocketSend.class)
    public int execSolut(Solution solution, @SessionAttribute("account") Webcon webcon){
        solution.setAccount(webcon.getAccount());
        solutionService.execSolution(solution);
        return 1;
    }
}
