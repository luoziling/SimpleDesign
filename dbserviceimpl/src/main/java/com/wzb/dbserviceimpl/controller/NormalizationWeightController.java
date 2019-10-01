package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.NormalizationWeightDBService;
import com.wzb.pojo.NormalizationWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Satsuki
 * @time 2019/10/1 21:11
 * @description:
 */
public class NormalizationWeightController implements NormalizationWeightDBService {

    @Autowired
    private NormalizationWeightDBService normalizationWeightDBService;

    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    @Override
    public NormalizationWeight selByNodeValue(String nowValue) {
        return normalizationWeightDBService.selByNodeValue(nowValue);
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

    @RequestMapping(value = "/insOrUpdByNW",method = RequestMethod.POST)
    @Override
    public int insOrUpdByNW(@RequestBody NormalizationWeight normalizationWeight) {
        return normalizationWeightDBService.insOrUpdByNW(normalizationWeight);
    }
}
