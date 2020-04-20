package com.wzb.businessservice.controller;

import com.wzb.businessservice.CalculationService;
import com.wzb.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Satsuki
 * @time 2019/10/11 14:52
 * @description:
 */

@RestController
@RequestMapping("/calculation_service")
public class CalculationServiceController implements CalculationService {

    @Autowired
    private CalculationService calculationService;

    @Override
    public NWResult NWCalculation(Double[][] data) {
        return null;
    }

    @RequestMapping(value = "/conclusion_calculation",method = RequestMethod.GET)
    @Override
    public void conclusionCalculation() {
        calculationService.conclusionCalculation();
    }

    @RequestMapping("/criconcal")
    @Override
    public void criConCalculation() {
        calculationService.criConCalculation();

    }

    @RequestMapping("/cal_test")
    @Override
    public void testBook() {
        calculationService.testBook();

    }

    @RequestMapping(value = "/norcal_andsave",method = RequestMethod.POST)
    @Override
    public void norCalAndSave(@RequestBody RootCriData rootCriData) {
        calculationService.norCalAndSave(rootCriData);
    }

    @RequestMapping(value = "/expertMatrixSaVE",method = RequestMethod.POST)
    @Override
    public CommonResult expertMatrixSaVE(@RequestBody MatrixWrapper matrixWrapper) {
        return calculationService.expertMatrixSaVE(matrixWrapper);
    }

    @RequestMapping(value = "/concalExpert",method = RequestMethod.POST)
    @Override
    public CommonResult concalExpert(@RequestBody ConcalWrapper concalWrapper) {
        return calculationService.concalExpert(concalWrapper);
    }
}
