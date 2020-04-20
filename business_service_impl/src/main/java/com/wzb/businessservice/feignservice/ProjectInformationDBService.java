package com.wzb.businessservice.feignservice;

import com.wzb.pojo.ProjectInformation;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/22 16:06
 * @description:
 */
//value是指定微服务名
//@EnableFeignClients
@FeignClient(value = "EUREKA-DB-PROVIDER")
//@FeignClient(value = "EUREKA_DB_PROVIDER") //非法服务名不能带下划线

@RequestMapping("/project_information")
public interface ProjectInformationDBService {


    //从数据库中获取所有保存的项目信息并且显示
//    @RequestMapping(value = "/get_all",method = RequestMethod.GET)
//    @GetMapping("/get_all")
//    List<ProjectInformation> selAll();


//    @RequestMapping(value = "/add_pi", method = RequestMethod.POST)
//    void insTest();

    @RequestMapping(value = "/add_pi", method = RequestMethod.POST)
    // 创建时候就添加一个根节点 并保存
    void insInitial(String projectName);

    @RequestMapping(value = "/project_creation", method = RequestMethod.POST)
    public void projectCreation(String projectName);

    @RequestMapping(value = "/insInitialExpert",method = RequestMethod.POST)
    public void insInitialExpert(@RequestBody ProjectInformation pi);



    @RequestMapping(value = "/selAll",method = RequestMethod.GET)
    public List<ProjectInformation> selAll();

    @RequestMapping(value = "/selById",method = RequestMethod.GET)
    public ProjectInformation selById(Integer id);

    @RequestMapping(value = "/selByPI",method = RequestMethod.POST)
    public boolean selByPI(@RequestBody ProjectInformation pi);

    @RequestMapping(value = "/selByPi",method = RequestMethod.POST)
    public ProjectInformation selByPi(@RequestBody ProjectInformation pi);


    @RequestMapping(value = "/selNowModel",method = RequestMethod.GET)
    public ProjectInformation selNowModel();

    @RequestMapping(value = "/insTest",method = RequestMethod.POST)
    public void insTest();
}
