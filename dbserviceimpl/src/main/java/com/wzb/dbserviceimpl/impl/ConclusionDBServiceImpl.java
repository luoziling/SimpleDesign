package com.wzb.dbserviceimpl.impl;

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
}
