package com.wzb.dbservice;

import com.wzb.common.CommonResult;
import com.wzb.common.ConcalWrapper;
import com.wzb.common.ConclusionResult;
import com.wzb.pojo.Conclusion;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:07
 * @description:
 */
public interface ConclusionDBService {
    /**
     * 根据模型搜索
     * @return
     */
    List<Conclusion> selByModel();

    /**
     * 根据模型信息搜索
     * @return
     */
    List<Conclusion> selByPI(ConcalWrapper concalWrapper);

    /**
     * insert a record
     * @param conclusion
     * @return
     */
    int insOneRecord(Conclusion conclusion);


    /**
     * 根据结论内容删除一条结论
     * @param plan
     * @return
     */
    int delByPlan(String plan);

    /**
     * 更新数据库数据
     * @param conclusion
     * @return
     */
    int updByConclusion(Conclusion conclusion);


    /**
     * 根据模型ID获取结论
     * @param projectID
     * @return
     */
    ConclusionResult selConByPI(Integer projectID);

    /**
     * 专家模块中的保存方案功能
     * @return
     */
    CommonResult insConByEx(Conclusion conclusion);

    /**
     * 专家模块中的删除方案功能
     * @param conclusion
     * @return
     */
    CommonResult delByExCon(Conclusion conclusion);



}
