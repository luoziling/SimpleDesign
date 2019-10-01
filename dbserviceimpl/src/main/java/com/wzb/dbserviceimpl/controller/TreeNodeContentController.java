package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.TreeNodeDBService;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Satsuki
 * @time 2019/9/25 20:28
 * @description:
 */
@RestController
@RequestMapping("/TreeNodeContent")
public class TreeNodeContentController implements TreeNodeDBService {
    @Resource
    private TreeNodeDBService treeNodeDBService;

    @RequestMapping(value = "/insInitial",method = RequestMethod.POST)
    public void insInitial(@RequestBody String projectName){
        treeNodeDBService.insInitial(projectName);
    }

    @RequestMapping(value = "/selById", method = RequestMethod.GET)
    @Override
    public TreeNodeContent selById(Integer id) {
        return treeNodeDBService.selById(id);
    }

    @RequestMapping(value = "/selIdByContent",method = RequestMethod.GET)
    @Override
    public Integer selIdByContent(String nodeValue) {
        return treeNodeDBService.selIdByContent(nodeValue);
    }

    @RequestMapping(value = "/selByContent",method = RequestMethod.GET)
    @Override
    public TreeNodeContent selByContent(String value) {
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
}
