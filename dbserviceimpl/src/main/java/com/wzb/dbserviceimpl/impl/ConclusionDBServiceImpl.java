package com.wzb.dbserviceimpl.impl;

import com.wzb.common.CommonResult;
import com.wzb.common.ConWrapper;
import com.wzb.common.ConcalWrapper;
import com.wzb.common.ConclusionResult;
import com.wzb.dbservice.ConclusionDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.ConclusionMapper;
import com.wzb.pojo.Conclusion;
import com.wzb.pojo.ConclusionExample;
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
public class ConclusionDBServiceImpl implements ConclusionDBService {
    @Autowired
    private ConclusionMapper conclusionMapper;

    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Override
    public List<Conclusion> selByModel() {
        List<Conclusion> conclusions = new ArrayList<>();
        ConclusionExample example = new ConclusionExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName());
        conclusions = conclusionMapper.selectByExample(example);
        return conclusions;
    }

    @Override
    public List<Conclusion> selByMU(ConWrapper conWrapper) {
        ConclusionExample example = new ConclusionExample();
        example.createCriteria().andProjectIdEqualTo(conWrapper.getProjectID())
                .andUserIdEqualTo(conWrapper.getUserID());
        return conclusionMapper.selectByExample(example);
    }

    @Override
    public List<Conclusion> selByPI(ConcalWrapper concalWrapper) {
        ConclusionExample example = new ConclusionExample();
        example.createCriteria().andProjectIdEqualTo(concalWrapper.getProjectID())
                .andUserIdEqualTo(concalWrapper.getUserID());
        return conclusionMapper.selectByExample(example);
    }

    @Override
    public List<Conclusion> selOrInsByPI(ConcalWrapper concalWrapper) {
        ConclusionExample example = new ConclusionExample();
        example.createCriteria().andProjectIdEqualTo(concalWrapper.getProjectID())
                .andUserIdEqualTo(concalWrapper.getUserID());
        // 去看看能不能查到已有的记录，查不到则插入
        List<Conclusion> conclusions = conclusionMapper.selectByExample(example);
        System.out.println("conclusions1 = " + conclusions);
//        if (conclusions==null){
        // 没有数据插入数据
        if (conclusions.size()<=0){
            // 插入记录
            Conclusion conclusion = new Conclusion();
            conclusion.setProjectId(concalWrapper.getProjectID());
            conclusion.setUserId(concalWrapper.getUserID());
            conclusion.setProjectName(concalWrapper.getProjectName());
            // 录入前十个医生
            for (int i = 1; i < 11; i++) {
                conclusion.setPlan(i+"");
                conclusionMapper.insert(conclusion);
            }
            // 再次查询
            conclusions = conclusionMapper.selectByExample(example);
            System.out.println("conclusions = " + conclusions);
        }
        // 返回
        return conclusions;
    }

    @Override
    public int insOneRecord(Conclusion conclusion) {
        return conclusionMapper.insertSelective(conclusion);
    }

    @Override
    public int delByPlan(String plan) {
        ConclusionExample example = new ConclusionExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andPlanEqualTo(plan);
        return conclusionMapper.deleteByExample(example);
    }

    @Override
    public int updByConclusion(Conclusion conclusion) {
//        return conclusionMapper.updateByExample(conclusion,new ConclusionExample());
        return conclusionMapper.updateByPrimaryKey(conclusion);
    }

    @Override
    public ConclusionResult selConByPI(Integer projectID) {
        ConclusionResult result = new ConclusionResult();
        try {
            ConclusionExample example = new ConclusionExample();
            example.createCriteria().andProjectIdEqualTo(projectID);
            result.setConclusions(conclusionMapper.selectByExample(example));
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(true);
            result.setReviews("网络出错请重试");
            return result;
        }

        result.setFlag(true);
        result.setReviews("获取结论信息成功");
        return result;
    }

    @Override
    public CommonResult insConByEx(Conclusion conclusion) {
        CommonResult result = new CommonResult();
        try {
            conclusionMapper.insert(conclusion);
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(true);
            result.setReviews("网络出错请重试");
            return result;
        }

        result.setFlag(true);
        result.setReviews("保存成功");
        return result;
    }

    @Override
    public CommonResult delByExCon(Conclusion conclusion) {
        CommonResult result = new CommonResult();
        try {
            ConclusionExample example = new ConclusionExample();
            example.createCriteria().andProjectIdEqualTo(conclusion.getProjectId())
                    .andUserIdEqualTo(conclusion.getUserId())
                    .andPlanEqualTo(conclusion.getPlan());
            conclusionMapper.deleteByExample(example);
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(true);
            result.setReviews("网络出错请重试");
            return result;
        }

        result.setFlag(true);
        result.setReviews("删除成功");
        return result;
    }
}
