package com.wzb.dbservice;

import com.wzb.common.AllModelsResult;
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
     * 根据用户id与项目名查询是否有重复项目
     * @param pi
     * @return
     */
    boolean selByPI(ProjectInformation pi);

    /**
     * 根据用户id与项目名查询是否有重复项目
     * @param pi
     * @return 返回项目信息
     */
    ProjectInformation selByPi(ProjectInformation pi);


    /**
     * 查找当前的模型（项目）
     * @return
     */
    ProjectInformation selNowModel();

    /**
     * 设置当前模型
     * 在创建或者选取模型的时候要重置数据库中的nowModel字段（后续可以放到redis中）
     * @return
     */
    Boolean setNowModel(String projectName);


//    @RequestMapping("/add_pi")
    void insTest();

    // 创建时候就添加一个根节点 并保存
    void insInitial(String projectName);


    // 重载方法，参数不同。
    void insInitialExpert(ProjectInformation pi);

    /**
     * 项目（模型）创建
     * @param projectName
     */
    void projectCreation(String projectName);

    /**
     * 模型创建检测是否有相同的项目名已存在
     * @param projectName
     * @return
     */
    Boolean createVerification(String projectName);

    /**
     * 根据用户ID查询所有项目
     * @param userID
     * @return
     */
    AllModelsResult selByUserID(Integer userID);


}
