package com.wzb.businessservice.feignservice;

import com.wzb.pojo.Conclusion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/conclusion")
public interface ConclusionDBService {

    @RequestMapping(value = "/selByModel",method = RequestMethod.GET)
    public List<Conclusion> selByModel();

    @RequestMapping(value = "/insOneRecord",method = RequestMethod.POST)
    public int insOneRecord(@RequestBody Conclusion conclusion);

    @RequestMapping(value = "/delByPlan",method = RequestMethod.POST)
    public int delByPlan(@RequestBody String plan);

    @RequestMapping(value = "/updByConclusion",method = RequestMethod.POST)
    public int updByConclusion(Conclusion conclusion);
}
