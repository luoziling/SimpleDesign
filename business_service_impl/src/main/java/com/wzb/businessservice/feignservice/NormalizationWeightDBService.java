package com.wzb.businessservice.feignservice;

import com.wzb.common.NorWrapper;
import com.wzb.pojo.NormalizationWeight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/normalization_weight")
public interface NormalizationWeightDBService {
    @RequestMapping(value = "/selByNodeValue/{nowValue}",method = RequestMethod.GET)
    public NormalizationWeight selByNodeValue(@PathVariable(value = "nowValue") String nowValue);

    @RequestMapping(value = "/selByPI",method = RequestMethod.POST)
    public NormalizationWeight selByPI(@RequestBody NorWrapper norWrapper);

    @RequestMapping(value = "/selByValueAndPlan",method = RequestMethod.GET)
    public NormalizationWeight selByValueAndPlan(@RequestParam("nowValue") String nowValue,@RequestParam("plan") String plan);

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.GET)
    public NormalizationWeight selByTwoValue(@RequestParam("value") String value,@RequestParam("nextValue") String nextValue);

    @RequestMapping(value = "/selByTwoValue",method = RequestMethod.POST)
    public NormalizationWeight selByTwoValues(@RequestBody NorWrapper norWrapper);

    @RequestMapping(value = "/insOrUpdByNW",method = RequestMethod.POST)
    public int insOrUpdByNW(@RequestBody NormalizationWeight normalizationWeight);
}
