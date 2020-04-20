package com.wzb.dbservice;

import com.wzb.common.NorWrapper;
import com.wzb.pojo.Conclusion;
import com.wzb.pojo.NormalizationWeight;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
public interface NormalizationWeightDBService {

    /**
     * 以当前节点为nextValue的归一权重表中的数据
     * @param nowValue
     * @return
     */
    NormalizationWeight selByNodeValue(String nowValue);

    /**
     * 以当前节点为nextValue以及模型信息查询归一权重表中的数据
     * @param norWrapper
     * @return
     */
    NormalizationWeight selByPI(NorWrapper norWrapper);

    /**
     * 取最后一层与结论层的值
     * @param nowValue
     * @param plan
     * @return
     */
    NormalizationWeight selByValueAndPlan(String nowValue,String plan);

    /**
     * 在数据库中查询有没有相同内容（是否已保存过
     * @param value
     * @param nextValue
     * @return
     */
    NormalizationWeight selByTwoValue(String value,String nextValue);
    /**
     * 获取准则最后一层-结论层的权重数据
     * @param norWrapper
     * @return
     */
    NormalizationWeight selByTwoValues(NorWrapper norWrapper);

    /**
     * 如果已经保存过则更改，未保存则插入
     * @param normalizationWeight
     * @return
     */
    int insOrUpdByNW(NormalizationWeight normalizationWeight);
}
