package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.AdjacentClosureDBService;
import com.wzb.pojo.AdjacentClosure;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/25 20:27
 * @description:
 */
@RestController
@RequestMapping("/adjacent_closure")
public class AdjacentClosureController implements AdjacentClosureDBService {
    @Resource
    private AdjacentClosureDBService adjacentClosureDBService;


    @RequestMapping(value = "/insInitial", method = RequestMethod.POST)
    public void insInitial(@RequestBody String projectName){
        adjacentClosureDBService.insInitial(projectName);
    }

    @RequestMapping(value = "/selZeroNode",method = RequestMethod.GET)
    @Override
    public List<Integer> selZeroNode() {
        return adjacentClosureDBService.selZeroNode();
    }

    @RequestMapping(value = "/selOneDepthNode",method = RequestMethod.GET)
    @Override
    public List<AdjacentClosure> selOneDepthNode() {
        return adjacentClosureDBService.selOneDepthNode();
    }

    @RequestMapping(value = "/selByAncestor",method = RequestMethod.GET)
    @Override
    public List<AdjacentClosure> selByAncestor(Integer ancestor) {
        return adjacentClosureDBService.selByAncestor(ancestor);
    }

    @RequestMapping(value = "/selByDescendant",method = RequestMethod.GET)
    @Override
    public AdjacentClosure selByDescendant(Integer descendant) {
        return adjacentClosureDBService.selByDescendant(descendant);
    }

    @RequestMapping(value = "/insByAC",method = RequestMethod.POST)
    @Override
    public int insByAC(@RequestBody AdjacentClosure adjacentClosure) {
        return adjacentClosureDBService.insByAC(adjacentClosure);
    }

    @RequestMapping(value = "/delByAD",method = RequestMethod.POST)
    @Override
    public int delByAD(@RequestBody Integer ancestor,@RequestBody Integer descendant) {
        return adjacentClosureDBService.delByAD(ancestor,descendant);
    }
}