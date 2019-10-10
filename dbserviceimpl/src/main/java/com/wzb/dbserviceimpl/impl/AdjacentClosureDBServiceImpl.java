package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.AdjacentClosureDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.AdjacentClosureMapper;
import com.wzb.dbserviceimpl.mapper.TreeNodeContentMapper;
import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.AdjacentClosureExample;
import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.TreeNodeContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:09
 * @description:
 */
@Primary
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class AdjacentClosureDBServiceImpl implements AdjacentClosureDBService {


    @Autowired
    private AdjacentClosureMapper adjacentClosureMapper;

    @Autowired
    private TreeNodeContentMapper treeNodeContentMapper;

    @Lazy
    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Override
    public void insInitial(String projectName) {
        //保存闭包表
        //根据值查询初始节点ID 需要限定在一个模型（项目）中每个节点值都是独立的
        TreeNodeContentExample tExample = new TreeNodeContentExample();
        tExample.createCriteria().andValueEqualTo(projectName);

        Integer ancestor = treeNodeContentMapper.selectByExample(tExample).get(0).getId();
        AdjacentClosure adjacentClosure = new AdjacentClosure();
        adjacentClosure.setProjectName(projectName);
        //当节点第一次插入时祖先后裔都是同一个
        adjacentClosure.setAncestor(ancestor);
        adjacentClosure.setDescendant(ancestor);
        adjacentClosure.setDepth(0);
        adjacentClosureMapper.insert(adjacentClosure);



    }

    @Override
    public List<Integer> selZeroNode() {
        List<Integer> nodeIdList = new ArrayList<>();
        List<AdjacentClosure> acList = new ArrayList<>();


        AdjacentClosureExample example = new AdjacentClosureExample();
        //获取当前模型信息
        ProjectInformation nowModel = projectInformationDBService.selNowModel();
        //以ancestor为关键总排序
        example.setOrderByClause("ancestor");
        //查询深度为0模型为当前选定模型的数据项
        example.createCriteria().andDepthEqualTo(0).andProjectNameEqualTo(nowModel.getProjectName());
        acList = adjacentClosureMapper.selectByExample(example);
        //遍历list查找需要的第零层节点
        for (AdjacentClosure adjacentClosure: acList){
            if (adjacentClosure.getAncestor().equals(adjacentClosure.getDescendant())){
                //添加节点ID
                nodeIdList.add(adjacentClosure.getAncestor());
            }
        }

        //返回
        return nodeIdList;
    }

    @Override
    public List<AdjacentClosure> selOneDepthNode() {
        List<AdjacentClosure> depthOneList = new ArrayList<>();
        ProjectInformation nowModel = projectInformationDBService.selNowModel();
        AdjacentClosureExample example = new AdjacentClosureExample();
        example.setOrderByClause("ancestor");
        example.createCriteria().andDepthEqualTo(1).andProjectNameEqualTo(nowModel.getProjectName());
        depthOneList = adjacentClosureMapper.selectByExample(example);


        return depthOneList;
    }

    @Override
    public List<AdjacentClosure> selByAncestor(Integer ancestor) {
        List<AdjacentClosure> acList = new ArrayList<>();
        AdjacentClosureExample example=  new AdjacentClosureExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andAncestorEqualTo(ancestor);
        acList = adjacentClosureMapper.selectByExample(example);

        return acList;
    }

    @Override
    public AdjacentClosure selByDescendant(Integer descendant) {
        AdjacentClosure fatherAdj = new AdjacentClosure();
        AdjacentClosureExample example = new AdjacentClosureExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andDescendantEqualTo(descendant).andDepthEqualTo(1);
        fatherAdj = adjacentClosureMapper.selectByExample(example).get(0);
        return fatherAdj;
    }

    @Override
    public int insByAC(AdjacentClosure adjacentClosure) {
        return adjacentClosureMapper.insertSelective(adjacentClosure);
    }

    @Override
    public int delByAD(Integer ancestor, Integer descendant) {
        AdjacentClosureExample example = new AdjacentClosureExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andAncestorEqualTo(ancestor).andDescendantEqualTo(descendant);

        return adjacentClosureMapper.deleteByExample(example);
    }
}
