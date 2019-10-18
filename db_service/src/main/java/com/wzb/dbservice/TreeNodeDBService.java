package com.wzb.dbservice;

import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.TreeNodeContent;

import java.util.List;

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

    /**
     * 根据ID查找节点信息
     * @param id
     * @return
     */
    TreeNodeContent selById(Integer id);


    /**
     * 根据内容获取ID
     * @param nodeValue
     * @return
     */
    Integer selIdByContent(String nodeValue);

    /**
     * 获取项目名代表的节点
     * @param value
     * @return
     */
    TreeNodeContent selByContent(String value);

    /**
     * 插入一条信息
     * @param treeNodeContent
     */
    int insByTN(TreeNodeContent treeNodeContent);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int delById(Integer id);

    /**
     * 根据项目名查找所有的节点
     * @param projectName
     * @return
     */
    List<TreeNodeContent> selByPI(String projectName);

}
