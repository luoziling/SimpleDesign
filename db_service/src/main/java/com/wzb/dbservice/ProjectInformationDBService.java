package com.wzb.dbservice;

import com.wzb.pojo.ProjectInformation;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/22 16:06
 * @description:
 */
////value是指定微服务名
//@FeignClient(value = "EUREKA_DB_PROVIDER")
//@RequestMapping("/project_information")
public interface ProjectInformationDBService {


    //从数据库中获取所有保存的项目信息并且显示
//    @RequestMapping(value = "/get_all",method = RequestMethod.GET)
//    @GetMapping("/get_all")
    List<ProjectInformation> selAll();

    /**
     * 根据ID查找项目
     * @param id
     * @return
     */
    ProjectInformation selById(Integer id);


    /**
     * 查找当前的模型（项目）
     * @return
     */
    ProjectInformation selNowModel();


//    @RequestMapping("/add_pi")
    void insTest();

    // 创建时候就添加一个根节点 并保存
    void insInitial(String projectName);

    /**
     * 项目（模型）创建
     * @param projectName
     */
    void projectCreation(String projectName);


}