package com.wzb.feignapi;

/**
 * @author Satsuki
 * @time 2019/9/24 22:06
 * @description:
 */
public interface TreeNodeDBService {

    /**
     * 初始化头节点
     * @param projectName
     */
    void insInitial(String projectName);
}
