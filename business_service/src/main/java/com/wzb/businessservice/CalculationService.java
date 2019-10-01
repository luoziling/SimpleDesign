package com.wzb.businessservice;

import com.wzb.common.NWResult;

/**
 * @author Satsuki
 * @time 2019/10/1 18:57
 * @description:
 */
public interface CalculationService {

    NWResult NWCalculation(Double[][] data);


    void conclusionCalculation();
}
