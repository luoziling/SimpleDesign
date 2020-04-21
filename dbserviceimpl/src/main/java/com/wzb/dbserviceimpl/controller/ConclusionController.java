package com.wzb.dbserviceimpl.controller;

import com.wzb.common.CommonResult;
import com.wzb.common.ConWrapper;
import com.wzb.common.ConcalWrapper;
import com.wzb.common.ConclusionResult;
import com.wzb.dbservice.ConclusionDBService;
import com.wzb.pojo.Conclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/selByMU",method = RequestMethod.POST)
    @Override
    public List<Conclusion> selByMU(@RequestBody ConWrapper conWrapper) {
        return conclusionDBService.selByMU(conWrapper);
    }

    @RequestMapping(value = "/selByPI",method = RequestMethod.POST)
    @Override
    public List<Conclusion> selByPI(@RequestBody ConcalWrapper concalWrapper) {
        return conclusionDBService.selByPI(concalWrapper);
    }

    @RequestMapping(value = "/selOrInsByPI",method = RequestMethod.POST)
    @Override
    public List<Conclusion> selOrInsByPI(@RequestBody ConcalWrapper concalWrapper) {
        return conclusionDBService.selOrInsByPI(concalWrapper);
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
        System.out.println("updByConclusion+conclusion:"+conclusion.toString());
        return conclusionDBService.updByConclusion(conclusion);
    }

    @RequestMapping(value = "/selConByPI/{id}",method = RequestMethod.GET)
    @Override
    public ConclusionResult selConByPI(@PathVariable("id") Integer projectID) {
        return conclusionDBService.selConByPI(projectID);
    }

    @RequestMapping(value = "/insConByEx",method = RequestMethod.POST)
    @Override
    public CommonResult insConByEx(@RequestBody Conclusion conclusion) {
        return conclusionDBService.insConByEx(conclusion);
    }

    @RequestMapping(value = "/delByExCon",method = RequestMethod.POST)
    @Override
    public CommonResult delByExCon(@RequestBody Conclusion conclusion) {
        return conclusionDBService.delByExCon(conclusion);
    }
}
