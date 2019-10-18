package com.wzb.businessservice.controller;

import com.wzb.businessservice.ProjectService;
import com.wzb.businessservice.utils.FastjsonUtil;
import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.ProjectInformation;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import com.wzb.pojo.TreeNodeContent;
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
public class ProjectServiceController implements ProjectService {

    @Resource
    private ProjectService projectService;



    @RequestMapping(value = "/insInitial", method = RequestMethod.POST)
    public void projectCreation(@RequestBody String json){
        System.out.println(json);
        System.out.println("json:" + json);
        String projectName = FastjsonUtil.getString(json,"projectName");
        System.out.println("projectName:" + projectName);
        projectService.projectCreation(projectName);
    }

    @RequestMapping(value = "/get_All",method = RequestMethod.GET)
    @Override
    public List<ProjectInformation> getAll() {
        return projectService.getAll();
    }

    @RequestMapping(value = "/getNodes/{projectName}",method = RequestMethod.GET)
    @Override
    public List<TreeNodeContent> getNodes(@PathVariable(value = "projectName") String projectName) {
        System.out.println("projectName:" + projectName);
        return projectService.getNodes(projectName);
    }

    @RequestMapping(value = "/save_ms",method = RequestMethod.POST)
    @Override
    public int saveMS(@RequestBody MatrixStorage matrixStorage) {
        System.out.println("PC:" + matrixStorage.toString());
        return projectService.saveMS(matrixStorage);
    }
}
