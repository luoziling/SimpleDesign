package com.wzb.dbserviceimpl.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.pojo.ProjectInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.netflix.discovery.DiscoveryClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/22 16:49
 * @description:
 */
@RestController
@RequestMapping("/project_information")
public class ProjectInformationController implements ProjectInformationDBService {

    @Resource
    private ProjectInformationDBService projectInformationDBService;


    @RequestMapping(value = "/project_information",method = RequestMethod.GET)
    public List<ProjectInformation>getAllProject(){
        System.out.println("projectInformationDBService = " + projectInformationDBService);
        List<ProjectInformation> projectInformationList= new ArrayList<>();
        projectInformationList = projectInformationDBService.selAll();
        return projectInformationList;

    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误消息后，会自动调用@HystrixCommand标注好的fallback调用类中指定方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public ProjectInformation getById(@PathVariable("id") Integer id){
        ProjectInformation pi = projectInformationDBService.selById(id);

        if(null == pi ){
            throw new RuntimeException("该ID：" + id + "没有查找对应的信息");
        }
        return pi;
    }

    public ProjectInformation processHystrix_Get(@PathVariable("id") Integer id){
        ProjectInformation pi = new ProjectInformation();
        pi.setId(id);
        pi.setProjectName("没有对应的信息,null--@HystrixCommand");
        pi.setLayer(0);
        return pi;
    }

    @RequestMapping(value = "/add_pi", method = RequestMethod.POST)
    public void addProjectInformation(@RequestBody String projectName){
        System.out.println("RequestBody projectName----------------:" + projectName);
        projectInformationDBService.insInitial(projectName);
    }


    @RequestMapping(value = "/project_creation", method = RequestMethod.POST)
    public void projectCreation(@RequestBody String projectName){
        System.out.println("RequestBody projectName----------------:" + projectName);
        projectInformationDBService.projectCreation(projectName);
    }

    @RequestMapping(value = "/selAll",method = RequestMethod.GET)
    @Override
    public List<ProjectInformation> selAll() {
        return projectInformationDBService.selAll();
    }

    @RequestMapping(value = "/selById",method = RequestMethod.GET)
    @Override
    public ProjectInformation selById(Integer id) {
        return projectInformationDBService.selById(id);
    }

    @RequestMapping(value = "/selNowModel",method = RequestMethod.GET)
    @Override
    public ProjectInformation selNowModel() {
        return projectInformationDBService.selNowModel();
    }

    @RequestMapping(value = "/insTest",method = RequestMethod.POST)
    @Override
    public void insTest() {
        projectInformationDBService.insTest();
    }

    @RequestMapping(value = "/insInitial",method = RequestMethod.POST)
    @Override
    public void insInitial(@RequestBody String projectName) {
        projectInformationDBService.insInitial(projectName);
    }

    @RequestMapping(value = "/setNowModel",method = RequestMethod.POST)
    @Override
    public Boolean setNowModel(@RequestBody String projectName) {
        System.out.println("setNowModel；" + projectName);
        return projectInformationDBService.setNowModel(projectName);
    }

    @RequestMapping(value = "/createVerification",method = RequestMethod.POST)
    @Override
    public Boolean createVerification(@RequestBody String projectName) {
        System.out.println("createVerification；" + projectName);
        return projectInformationDBService.createVerification(projectName);
    }
}
