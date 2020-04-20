package com.wzb.dbserviceimpl.impl;

import com.netflix.discovery.converters.Auto;
import com.wzb.common.*;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbservice.TreeNodeDBService;
import com.wzb.dbserviceimpl.mapper.AdjacentClosureMapper;
import com.wzb.dbserviceimpl.mapper.ConclusionMapper;
import com.wzb.dbserviceimpl.mapper.TreeNodeContentMapper;
import com.wzb.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TreeNodeDBServiceImpl implements TreeNodeDBService {

    @Autowired
    private TreeNodeContentMapper treeNodeContentMapper;

    @Autowired
    private AdjacentClosureMapper adjacentClosureMapper;

    @Autowired
    private ConclusionMapper conclusionMapper;

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
    public void insInitialExpert(ProjectInformation pi) {
        TreeNodeContent treeNodeContent = new TreeNodeContent();
        treeNodeContent.setProjectId(pi.getId());
        treeNodeContent.setProjectName(pi.getProjectName());
        treeNodeContent.setValue(pi.getProjectName());
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
    public TreeNodeContent selRootByPI(ConcalWrapper concalWrapper) {
        TreeNodeContentExample example=  new TreeNodeContentExample();
        example.createCriteria().andProjectIdEqualTo(concalWrapper.getProjectID())
                .andValueEqualTo(concalWrapper.getProjectName());
        return treeNodeContentMapper.selectByExample(example).get(0);
    }

    @Override
    public int insByTN(TreeNodeContent treeNodeContent) {
        return treeNodeContentMapper.insertSelective(treeNodeContent);
    }

    @Override
    public int delById(Integer id) {
        return treeNodeContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TreeNodeContent> selByPI(String projectName) {
        TreeNodeContentExample example = new TreeNodeContentExample();
        example.createCriteria().andProjectNameEqualTo(projectName);

        return treeNodeContentMapper.selectByExample(example);
    }

    @Override
    public CommonResult saveTreeNode(SaveNodeWrapper saveNodeWrapper) {
        CommonResult result = new CommonResult();
        try {
            // 保存新节点
            treeNodeContentMapper.insert(saveNodeWrapper.getTreeNodeContent());

            // 获取新节点id
            TreeNodeContentExample example = new TreeNodeContentExample();
            example.createCriteria().andProjectIdEqualTo(saveNodeWrapper.getTreeNodeContent().getProjectId())
                    .andValueEqualTo(saveNodeWrapper.getTreeNodeContent().getValue());
            int myID = treeNodeContentMapper.selectByExample(example).get(0).getId();

            // 保存闭包表
            // 保存自己
            AdjacentClosure adjacentClosure = new AdjacentClosure();
            adjacentClosure.setProjectId(saveNodeWrapper.getTreeNodeContent().getProjectId());
            adjacentClosure.setProjectName(saveNodeWrapper.getTreeNodeContent().getProjectName());
            adjacentClosure.setAncestor(myID);
            adjacentClosure.setDescendant(myID);
            adjacentClosure.setDepth(0);

            adjacentClosureMapper.insert(adjacentClosure);

            // 保存新节点与祖先节点的关系
            //深度置零
            int mydepth = 0;
            for (int i = 0; i < saveNodeWrapper.getNodeList().size(); i++) {
                mydepth++;
                // 根据内容与项目id获取祖节点id
                TreeNodeContentExample myExample = new TreeNodeContentExample();
                myExample.createCriteria().andValueEqualTo(saveNodeWrapper.getNodeList().get(i))
                        .andProjectIdEqualTo(saveNodeWrapper.getTreeNodeContent().getProjectId());
                int aID = treeNodeContentMapper.selectByExample(myExample).get(0).getId();
                // 其余不变，修改祖先ID与深度
                adjacentClosure.setAncestor(aID);
                adjacentClosure.setDepth(mydepth);
                // 插入闭包表保存树形节点之间的关系
                adjacentClosureMapper.insert(adjacentClosure);
            }
        }catch (Exception e){
            // 出错
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }

        result.setFlag(true);
        result.setReviews("节点创建成功");


        return result;
    }

    @Override
    public CommonResult deleteTreeNode(DeleteNodeWrapper deleteNodeWrapper) {
        CommonResult result = new CommonResult();
        try{
            // 查询待删除节点的ID
            TreeNodeContentExample tExample = new TreeNodeContentExample();
            tExample.createCriteria().andProjectIdEqualTo(deleteNodeWrapper.getProjectID())
                    .andValueEqualTo(deleteNodeWrapper.getNodeValue());
            int myID = treeNodeContentMapper.selectByExample(tExample).get(0).getId();
            // 查询以该节点为祖先节点的所有节点（找出后裔节点
            List<AdjacentClosure> list = new ArrayList<>();
            AdjacentClosureExample aExample = new AdjacentClosureExample();
            aExample.createCriteria().andProjectIdEqualTo(deleteNodeWrapper.getProjectID())
                    .andAncestorEqualTo(myID);
            list = adjacentClosureMapper.selectByExample(aExample);
            // 删除所有以上述列表中的后裔节点为后裔节点的记录
            for(AdjacentClosure adjacentClosure : list){
                // 删除闭包表记录
                // 不需要重复删除祖先节点为待删除节点的记录
                // 后裔节点包含了所有要删除的内容。
                AdjacentClosureExample mExample = new AdjacentClosureExample();
                mExample.createCriteria().andProjectIdEqualTo(deleteNodeWrapper.getProjectID())
                        .andDescendantEqualTo(adjacentClosure.getDescendant());

                adjacentClosureMapper.deleteByExample(mExample);

                // 删除节点表记录
                TreeNodeContentExample tnExample = new TreeNodeContentExample();
                tnExample.createCriteria().andProjectIdEqualTo(deleteNodeWrapper.getProjectID())
                        .andIdEqualTo(adjacentClosure.getDescendant());
                treeNodeContentMapper.deleteByExample(tnExample);

            }
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }
        result.setFlag(true);
        result.setReviews("节点删除成功");
        return result;
    }

    @Override
    public ModelInfoResult selInfoByPI(Integer projectID) {
        ModelInfoResult result = new ModelInfoResult();
        try{
            // 查询所有节点
            TreeNodeContentExample tExample = new TreeNodeContentExample();
            tExample.createCriteria().andProjectIdEqualTo(projectID);
            result.setNodeContents(treeNodeContentMapper.selectByExample(tExample));
            // 查询节点层次关系
            AdjacentClosureExample aExample = new AdjacentClosureExample();
            // 查询深度1的记录
            aExample.createCriteria().andProjectIdEqualTo(projectID)
                    .andDepthEqualTo(1);
            result.setAdjacentClosures(adjacentClosureMapper.selectByExample(aExample));

        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }
        result.setFlag(true);
        result.setReviews("获取模型信息成功");
        return result;
    }

    @Override
    public NextOrConResult selNCByTN(TreeNodeContent treeNodeContent) {
        NextOrConResult result = new NextOrConResult();
        try{
            // 根据当前节点信息查询下一层节点
            // 根据内容与模型ID查询当前节点ID
            TreeNodeContentExample tExample = new TreeNodeContentExample();
            tExample.createCriteria().andProjectIdEqualTo(treeNodeContent.getProjectId())
                    .andValueEqualTo(treeNodeContent.getValue());
            Integer ancestor = treeNodeContentMapper.selectByExample(tExample).get(0).getId();
            AdjacentClosureExample aExample = new AdjacentClosureExample();
            // 以当前节点为祖先节点查询深度1的记录就是查询下一层节点记录
            aExample.createCriteria().andProjectIdEqualTo(treeNodeContent.getProjectId())
                    .andAncestorEqualTo(ancestor)
                    .andDepthEqualTo(1);
            List<AdjacentClosure> adjacentClosures = adjacentClosureMapper.selectByExample(aExample);
            if (adjacentClosures.size()>0){
                // 有下一层节点直接返回
                result.setFlag(true);
                result.setAdjacentClosures(adjacentClosures);
                result.setReviews("找到下一层节点信息");
                // 查找节点详细信息
                List<TreeNodeContent> treeNodeContents = new ArrayList<>();
                for(AdjacentClosure adjacentClosure : adjacentClosures){
                    TreeNodeContent mNode = new TreeNodeContent();
                    TreeNodeContentExample mExample = new TreeNodeContentExample();
                    // 根据后裔节点ID作为节点ID查询子节点
                    mExample.createCriteria().andIdEqualTo(adjacentClosure.getDescendant());
                    mNode = treeNodeContentMapper.selectByExample(mExample).get(0);
                    treeNodeContents.add(mNode);
                }
                // 存值返回
                result.setTreeNodeContents(treeNodeContents);
            }else {
                // 未找到节点信息
                // 尝试查找结论信息
                ConclusionExample cExample = new ConclusionExample();
                cExample.createCriteria().andProjectIdEqualTo(treeNodeContent.getProjectId());
                List<Conclusion> conclusions = conclusionMapper.selectByExample(cExample);
                if (conclusions.size()>0){
                    // 有结论
                    result.setFlag(true);
                    result.setConclusions(conclusions);
                    result.setReviews("找到方案信息");
                }else {
                    // 没下一层节点也没有方案
                    result.setFlag(false);
                    result.setReviews("请先添加下一层节点/方案");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }

        return result;
    }
}
