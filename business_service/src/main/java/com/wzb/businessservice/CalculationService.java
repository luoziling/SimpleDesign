package com.wzb.businessservice;

import com.wzb.common.NWResult;

/**
 * @author Satsuki
 * @time 2019/10/1 18:57
 * @description:
 * 计算服务的接口
 */
public interface CalculationService {

    NWResult NWCalculation(Double[][] data);


    /**
     * 结论优先值计算
     */
    void conclusionCalculation();

    /**
     * 准则--结论
     * 对应的归一化权重计算
     * 第一个上海市骨科挂号决策支持模型的计算
     */
    void criConCalculation();
}
