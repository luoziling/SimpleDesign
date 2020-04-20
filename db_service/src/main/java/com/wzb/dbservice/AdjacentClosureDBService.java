package com.wzb.dbservice;

import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.ProjectInformation;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:06
 * @description:
 */
public interface AdjacentClosureDBService {

    void insInitial(String projectName);


    void insInitialExpert(ProjectInformation pi);

    /**
     * 获取深度为0的节点ID
     * 也就是找出了所有的节点
     * @return
     */
    List<Integer> selZeroNode();

    /**
     * 找寻祖先后裔的深度差距为1的节点
     * 这里的祖先和后裔的差距只有1也就代表了这两个是父子节点
     * 祖先节点等于父节点，后裔节点等于子节点，进行组合即可
     * 找祖先节点（j）
     * 找出深度差距为0的节点也就找出了全部节点
     *  深度差距为1的节点，就代表祖先和后裔的深度差距为1就找到了节点之间的关系父子关系，进行组合即可
     * @return
     */
    List<AdjacentClosure> selOneDepthNode();

    /**
     * 根据该节点查询以该节点为祖先节点的所有记录
     * 查询该节点有多少后裔节点
     * @param ancestor
     * @return
     */
    List<AdjacentClosure> selByAncestor(Integer ancestor);

    /**
     * 根据模型ID以及根节点为祖先节点查询所i有记录
     * @param projectID
     * @param ancestor
     * @return
     */
    List<AdjacentClosure> selByAP(Integer projectID,Integer ancestor);

    /**
     * 从数据库中查询当前节点有没有父节点
     * 父节点就是在邻接矩阵中后裔ID为当前节点ID且深度为1的节点
     * @param descendant
     * @return
     */
    AdjacentClosure selByDescendant(Integer descendant);

    /**
     * 根据模型ID以及当前节点节点为后裔节点查询上一层记录
     * @param projectID
     * @param descendant
     * @return
     */
    AdjacentClosure selByDP(Integer projectID,Integer descendant);

//    List<AdjacentClosure> selAllChild(Integer )

    /**
     * 插入一条信息
     * @param adjacentClosure
     */
    int insByAC(AdjacentClosure adjacentClosure);

    /**
     * 根据祖先后裔删除
     * @param ancestor
     * @param descendant
     * @return
     */
    int delByAD(Integer ancestor,Integer descendant);


}
