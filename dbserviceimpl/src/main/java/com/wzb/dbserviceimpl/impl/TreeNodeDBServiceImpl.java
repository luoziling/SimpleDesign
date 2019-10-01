package com.wzb.dbserviceimpl.impl;

import com.netflix.discovery.converters.Auto;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbservice.TreeNodeDBService;
import com.wzb.dbserviceimpl.mapper.TreeNodeContentMapper;
import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.TreeNodeContent;
import com.wzb.pojo.TreeNodeContentExample;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class TreeNodeDBServiceImpl implements TreeNodeDBService {

    @Autowired
    private TreeNodeContentMapper treeNodeContentMapper;

    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Override
    public void insInitial(String projectName) {
        System.out.println("TreeNodeDBServiceImpl projectName:" + projectName);
        TreeNodeContent treeNodeContent = new TreeNodeContent();
        treeNodeContent.setProjectName(projectName);
        treeNodeContent.setValue(projectName);
        treeNodeContentMapper.insert(treeNodeContent);
    }

    @Override
    public TreeNodeContent selById(Integer id) {
        return treeNodeContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer selIdByContent(String nodeValue) {
        TreeNodeContent treeNodeContent= this.selByContent(nodeValue);
        return treeNodeContent.getId();
    }


    @Override
    public TreeNodeContent selByContent(String value) {
        TreeNodeContent treeNodeContent= new TreeNodeContent();
        TreeNodeContentExample example=  new TreeNodeContentExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andValueEqualTo(value);
        treeNodeContent=treeNodeContentMapper.selectByExample(example).get(0);
        return treeNodeContent;
    }

    @Override
    public int insByTN(TreeNodeContent treeNodeContent) {
        return treeNodeContentMapper.insertSelective(treeNodeContent);
    }

    @Override
    public int delById(Integer id) {
        return treeNodeContentMapper.deleteByPrimaryKey(id);
    }
}
