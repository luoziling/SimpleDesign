package com.wzb.businessservice;

import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.TreeNodeContent;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/25 18:23
 * @description:
 */
public interface ProjectService {
    /**
     * 项目建立
     * @param projectName 项目名
     */
    void projectCreation(String projectName);

    /**
     * 测试用的
     * @return
     */
    List<ProjectInformation> getAll();

    /**
     * 获取该项目下的所有节点
     * @return
     */
    List<TreeNodeContent> getNodes(String projectName);

    /**
     * 保存判断矩阵
     * @param matrixStorage
     * @return
     */
    int saveMS(MatrixStorage matrixStorage);


}
