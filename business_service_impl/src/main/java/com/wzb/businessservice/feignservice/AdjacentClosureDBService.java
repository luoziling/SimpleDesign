package com.wzb.businessservice.feignservice;

import com.wzb.pojo.AdjacentClosure;
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
@RequestMapping("/adjacent_closure")
public interface AdjacentClosureDBService {


    @RequestMapping(value = "/insInitial", method = RequestMethod.POST)
    void insInitial(String projectName);

    @RequestMapping(value = "/selZeroNode",method = RequestMethod.GET)
    public List<Integer> selZeroNode();

    @RequestMapping(value = "/selOneDepthNode",method = RequestMethod.GET)
    public List<AdjacentClosure> selOneDepthNode();

    @RequestMapping(value = "/selByAncestor",method = RequestMethod.GET)
    public List<AdjacentClosure> selByAncestor(Integer ancestor) ;

    @RequestMapping(value = "/selByDescendant",method = RequestMethod.GET)
    public AdjacentClosure selByDescendant(Integer descendant);

    @RequestMapping(value = "/insByAC",method = RequestMethod.POST)
    public int insByAC(@RequestBody AdjacentClosure adjacentClosure);

    @RequestMapping(value = "/delByAD",method = RequestMethod.POST)
    public int delByAD(@RequestBody Integer ancestor,@RequestBody Integer descendant);
}
