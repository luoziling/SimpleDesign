package com.wzb.businessservice;

import com.wzb.common.*;

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


    CommonResult concalGeneral(ConcalWrapper concalWrapper);

    /**
     * 准则--结论
     * 对应的归一化权重计算
     * 第一个上海市骨科挂号决策支持模型的计算
     */
    void criConCalculation();

    /**
     * 计算测试
     */
    void testBook();

    /**
     * 将输入的数值计算归一化权重并且保存
     */
    void norCalAndSave(RootCriData rootCriData);


    /**
     * 计算一致性保存矩阵数据
     * @param matrixWrapper 矩阵数据的封装
     * @return
     */
    CommonResult expertMatrixSaVE(MatrixWrapper matrixWrapper);

    /**
     * 结论计算
     * @return
     */
    CommonResult concalExpert(ConcalWrapper concalWrapper);

}
