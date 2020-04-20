package com.wzb.businessservice.feignservice;

import com.wzb.pojo.AdjacentClosure;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:06
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/adjacent_closure")
public interface AdjacentClosureDBService {


    @RequestMapping(value = "/insInitial", method = RequestMethod.POST)
    void insInitial(String projectName);

    @RequestMapping(value = "/selZeroNode",method = RequestMethod.GET)
    public List<Integer> selZeroNode();

    @RequestMapping(value = "/selOneDepthNode",method = RequestMethod.GET)
    public List<AdjacentClosure> selOneDepthNode();

    @RequestMapping(value = "/selByAncestor/{ancestor}",method = RequestMethod.GET)
    public List<AdjacentClosure> selByAncestor(@PathVariable(value = "ancestor") Integer ancestor) ;

    @RequestMapping(value = "/selByAP",method = RequestMethod.GET)
    public List<AdjacentClosure> selByAP(@RequestParam("projectID") Integer projectID,@RequestParam("ancestor") Integer ancestor);

    @RequestMapping(value = "/selByDescendant/{descendant}",method = RequestMethod.GET)
    public AdjacentClosure selByDescendant(@PathVariable(value = "descendant") Integer descendant);

    @RequestMapping(value = "/selByDescendant",method = RequestMethod.GET)
    public AdjacentClosure selByDP(@RequestParam("projectID") Integer projectID,@RequestParam("descendant") Integer descendant);

    @RequestMapping(value = "/insByAC",method = RequestMethod.POST)
    public int insByAC(@RequestBody AdjacentClosure adjacentClosure);

    @RequestMapping(value = "/delByAD",method = RequestMethod.POST)
    public int delByAD(@RequestParam("value") Integer ancestor,@RequestParam("descendant") Integer descendant);
}
