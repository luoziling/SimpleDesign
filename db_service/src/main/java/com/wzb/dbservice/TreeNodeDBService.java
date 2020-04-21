package com.wzb.dbservice;

import com.wzb.common.*;
import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.ProjectInformation;
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
     * 初始化头节点
     * @param pi
     */
    void insInitialExpert(ProjectInformation pi);

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
     * 根据模型信息查询根节点信息
     * @param concalWrapper
     * @return
     */
    TreeNodeContent selRootByPI(ConcalWrapper concalWrapper);

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

    /**
     * 根据项目ID查找所有的节点
     * @param projectID
     * @return
     */
    List<TreeNodeContent> selByPi(Integer projectID);

    /**
     *  保存新建节点
     * @param saveNodeWrapper
     * @return
     */
    CommonResult saveTreeNode(SaveNodeWrapper saveNodeWrapper);

    /**
     *  删除节点
     * @param deleteNodeWrapper
     * @return
     */
    CommonResult deleteTreeNode(DeleteNodeWrapper deleteNodeWrapper);


    /**
     * 获取模型详细信息
     * @param projectID
     * @return
     */
    ModelInfoResult selInfoByPI(Integer projectID);


    /**
     * 根据节点信息查询下一层节点或结论
     * @param treeNodeContent
     * @return
     */
    NextOrConResult selNCByTN(TreeNodeContent treeNodeContent);






}
