package com.wzb.businessservice.controller;

import com.wzb.businessservice.ProjectService;
import com.wzb.businessservice.utils.FastjsonUtil;
import com.wzb.pojo.ProjectInformation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/25 21:41
 * @description:
 */
@RestController
@RequestMapping("/project_service")
//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
public class ProjectServiceController {

    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/get_All",method = RequestMethod.GET)
    public List<ProjectInformation> getPI(){
        return projectService.getAll();
    }

    @RequestMapping(value = "/insInitial", method = RequestMethod.POST)
    public void projectCreation(@RequestBody String json){
        System.out.println(json);
        System.out.println("json:" + json);
        String projectName = FastjsonUtil.getString(json,"projectName");
        System.out.println("projectName:" + projectName);
        projectService.projectCreation(projectName);
    }
}
