package com.wzb.businessservice.feignservice;

import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:06
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/TreeNodeContent")
public interface TreeNodeDBService {

    /**
     * 初始化头节点
     * @param projectName
     */
    @RequestMapping("/insInitial")
    void insInitial(String projectName);

    @RequestMapping(value = "/selById", method = RequestMethod.GET)
    public TreeNodeContent selById(Integer id);

    @RequestMapping(value = "/selIdByContent",method = RequestMethod.GET)
    public Integer selIdByContent(String nodeValue);

    @RequestMapping(value = "/selByContent",method = RequestMethod.GET)
    public TreeNodeContent selByContent(String value);

    @RequestMapping(value = "/insByTN",method = RequestMethod.POST)
    public int insByTN(@RequestBody TreeNodeContent treeNodeContent);

    @RequestMapping(value = "/delById",method = RequestMethod.POST)
    public int delById(@RequestBody Integer id);

}
