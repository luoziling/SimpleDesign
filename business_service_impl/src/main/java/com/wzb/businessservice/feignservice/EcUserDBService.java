package com.wzb.businessservice.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Satsuki
 * @time 2019/10/1 21:45
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/NormalizationWeight")
public interface EcUserDBService {
}
