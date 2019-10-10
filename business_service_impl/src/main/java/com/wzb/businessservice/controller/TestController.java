package com.wzb.businessservice.controller;

import com.wzb.businessservice.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Satsuki
 * @time 2019/9/25 21:44
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private CalculationService calculationService;

    @RequestMapping("/testC")
    public String testC(){
        return "Hello Eureka";
    }

    @RequestMapping("/test_cri")
    public void criConCalculation(){
        calculationService.criConCalculation();
    }
}
