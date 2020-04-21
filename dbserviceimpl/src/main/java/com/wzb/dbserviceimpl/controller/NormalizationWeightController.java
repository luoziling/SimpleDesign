package com.wzb.dbserviceimpl.controller;

import com.wzb.common.NorWrapper;
import com.wzb.dbservice.NormalizationWeightDBService;
import com.wzb.pojo.NormalizationWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Satsuki
 * @time 2019/10/1 21:11
 * @description:
 */
@RestController
@RequestMapping("/normalization_weight")
public class NormalizationWeightController implements NormalizationWeightDBService {

    @Autowired
    private NormalizationWeightDBService normalizationWeightDBService;

    @RequestMapping(value = "/selByNodeValue/{nowValue}",method = RequestMethod.GET)
    @Override
    public NormalizationWeight selByNodeValue(@PathVariable(value = "nowValue") String nowValue) {
        return normalizationWeightDBService.selByNodeValue(nowValue);
    }

    @RequestMapping(value = "/selByPI",method = RequestMethod.POST)
    @Override
    public NormalizationWeight selByPI(@RequestBody NorWrapper norWrapper) {
        return normalizationWeightDBService.selByPI(norWrapper);
    }

    @RequestMapping(value = "/selByValueAndPlan",method = RequestMethod.GET)
    @Override
    public NormalizationWeight selByValueAndPlan(String nowValue, String plan) {
        return normalizationWeightDBService.selByValueAndPlan(nowValue,plan);
    }

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.GET)
    @Override
    public NormalizationWeight selByTwoValue(String value, String nextValue) {
        return normalizationWeightDBService.selByTwoValue(value,nextValue);
    }

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.POST)
    @Override
    public NormalizationWeight selByTwoValues(@RequestBody NorWrapper norWrapper) {
        return normalizationWeightDBService.selByTwoValues(norWrapper);
    }

    @RequestMapping(value = "/selByTwoValuesGeneral",method = RequestMethod.POST)
    @Override
    public NormalizationWeight selByTwoValuesGeneral(@RequestBody NorWrapper norWrapper) {
        return normalizationWeightDBService.selByTwoValuesGeneral(norWrapper);
    }

    @RequestMapping(value = "/insOrUpdByNW",method = RequestMethod.POST)
    @Override
    public int insOrUpdByNW(@RequestBody NormalizationWeight normalizationWeight) {
        System.out.println(normalizationWeight);
        return normalizationWeightDBService.insOrUpdByNW(normalizationWeight);
    }
}
