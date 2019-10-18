package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.TreeNodeDBService;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/25 20:28
 * @description:
 */
@RestController
@RequestMapping("/treenode_content")
public class TreeNodeContentController implements TreeNodeDBService {
    @Resource
    private TreeNodeDBService treeNodeDBService;

    @RequestMapping(value = "/insInitial",method = RequestMethod.POST)
    public void insInitial(@RequestBody String projectName){
        treeNodeDBService.insInitial(projectName);
    }

    @RequestMapping(value = "/selById/{id}", method = RequestMethod.GET)
    @Override
    public TreeNodeContent selById(@PathVariable(value = "id") Integer id) {
        return treeNodeDBService.selById(id);
    }

    @RequestMapping(value = "/selIdByContent/{nodeValue}",method = RequestMethod.GET)
    @Override
    public Integer selIdByContent(@PathVariable(value = "nodeValue") String nodeValue) {
        return treeNodeDBService.selIdByContent(nodeValue);
    }

    @RequestMapping(value = "/selByContent/{model_name}",method = RequestMethod.GET)
    @Override
    public TreeNodeContent selByContent(@PathVariable(value = "model_name") String value) {
        return treeNodeDBService.selByContent(value);
    }

    @RequestMapping(value = "/insByTN",method = RequestMethod.POST)
    @Override
    public int insByTN(@RequestBody TreeNodeContent treeNodeContent) {
        return treeNodeDBService.insByTN(treeNodeContent);
    }

    @RequestMapping(value = "/delById",method = RequestMethod.POST)
    @Override
    public int delById(@RequestBody Integer id) {
        return treeNodeDBService.delById(id);
    }

    @RequestMapping(value = "/selByPI/{projectName}",method = RequestMethod.GET)
    @Override
    public List<TreeNodeContent> selByPI(@PathVariable("projectName") String projectName) {
        System.out.println("projectName:" + projectName);
        return treeNodeDBService.selByPI(projectName);
    }
}
