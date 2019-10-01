package com.wzb.businessservice.feignservice;

import com.wzb.pojo.NormalizationWeight;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
public interface NormalizationWeightDBService {
    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    public NormalizationWeight selByNodeValue(String nowValue);

    @RequestMapping(value = "/selByValueAndPlan",method = RequestMethod.GET)
    public NormalizationWeight selByValueAndPlan(String nowValue, String plan);

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.GET)
    public NormalizationWeight selByTwoValue(String value, String nextValue);

    @RequestMapping(value = "/insOrUpdByNW",method = RequestMethod.POST)
    public int insOrUpdByNW(NormalizationWeight normalizationWeight);
}
