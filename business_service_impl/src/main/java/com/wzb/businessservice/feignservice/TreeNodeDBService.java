package com.wzb.businessservice.feignservice;

import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/treenode_content")
public interface TreeNodeDBService {

    /**
     * 初始化头节点
     * @param projectName
     */
    @RequestMapping("/insInitial")
    void insInitial(String projectName);

    @RequestMapping(value = "/selById/{id}", method = RequestMethod.GET)
    public TreeNodeContent selById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/selIdByContent/{nodeValue}",method = RequestMethod.GET)
    public Integer selIdByContent(@PathVariable(value = "nodeValue") String nodeValue);

    @RequestMapping(value = "/selByContent/{model_name}",method = RequestMethod.GET)
    public TreeNodeContent selByContent(@PathVariable(value = "model_name") String value);

    @RequestMapping(value = "/insByTN",method = RequestMethod.POST)
    public int insByTN(@RequestBody TreeNodeContent treeNodeContent);

    @RequestMapping(value = "/delById",method = RequestMethod.POST)
    public int delById(@RequestBody Integer id);

    @RequestMapping(value = "/selByPI/{projectName}",method = RequestMethod.GET)
    public List<TreeNodeContent> selByPI(@PathVariable(value = "projectName") String projectName);

}
