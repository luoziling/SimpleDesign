package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.ConclusionDBService;
import com.wzb.pojo.Conclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 21:09
 * @description:
 */
@RestController
@RequestMapping("/conclusion")
public class ConclusionController implements ConclusionDBService {

    @Autowired
    private ConclusionDBService conclusionDBService;

    @RequestMapping(value = "/selByModel",method = RequestMethod.GET)
    @Override
    public List<Conclusion> selByModel() {
        return conclusionDBService.selByModel();
    }

    @RequestMapping(value = "/insOneRecord",method = RequestMethod.POST)
    @Override
    public int insOneRecord(@RequestBody Conclusion conclusion) {
        return conclusionDBService.insOneRecord(conclusion);
    }

    @RequestMapping(value = "/delByPlan",method = RequestMethod.POST)
    @Override
    public int delByPlan(@RequestBody String plan) {
        return conclusionDBService.delByPlan(plan);
    }

    @RequestMapping(value = "/updByConclusion",method = RequestMethod.POST)
    @Override
    public int updByConclusion(@RequestBody Conclusion conclusion) {
        return 0;
    }
}
