package com.wzb.userserviceimpl.feighservice;

import com.wzb.pojo.EcUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Satsuki
 * @time 2019/10/1 21:45
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/ec_user")
public interface EcUserDBService {

    @RequestMapping(value = "/selByUsername",method = RequestMethod.POST)
    EcUser selByUsername(String username);

}
