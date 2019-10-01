package com.wzb.businessservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Satsuki
 * @time 2019/9/25 21:44
 * @description:
 */
@RestController
public class TestController {

    @RequestMapping("/testC")
    public String testC(){
        return "Hello Eureka";
    }
}
