package com.wzb.businessservice.feignservice;

import com.wzb.pojo.NormalizationWeight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/normalization_weight")
public interface NormalizationWeightDBService {
    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    public NormalizationWeight selByNodeValue(String nowValue);

    @RequestMapping(value = "/selByValueAndPlan",method = RequestMethod.GET)
    public NormalizationWeight selByValueAndPlan(@RequestParam("nowValue") String nowValue,@RequestParam("plan") String plan);

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.GET)
    public NormalizationWeight selByTwoValue(@RequestParam("value") String value,@RequestParam("nextValue") String nextValue);

    @RequestMapping(value = "/insOrUpdByNW",method = RequestMethod.POST)
    public int insOrUpdByNW(NormalizationWeight normalizationWeight);
}
