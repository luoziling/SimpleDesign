package com.wzb.dbservice;

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



}
