package com.wzb.businessservice;

import com.wzb.pojo.ProjectInformation;

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


}
