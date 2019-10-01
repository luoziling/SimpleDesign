package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.NormalizationWeightDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.NormalizationWeightMapper;
import com.wzb.pojo.NormalizationWeight;
import com.wzb.pojo.NormalizationWeightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Satsuki
 * @time 2019/9/24 22:08
 * @description:
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class NormalizationWeightDBServiceImpl implements NormalizationWeightDBService {

    @Autowired
    private NormalizationWeightMapper normalizationWeightMapper;

    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Override
    public NormalizationWeight selByNodeValue(String nowValue) {
        NormalizationWeight normalizationWeight = new NormalizationWeight();
        NormalizationWeightExample example = new NormalizationWeightExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andNextValueEqualTo(nowValue);
        normalizationWeight = normalizationWeightMapper.selectByExample(example).get(0);
        return normalizationWeight;
    }

    @Override
    public NormalizationWeight selByValueAndPlan(String nowValue, String plan) {
        NormalizationWeight normalizationWeight = new NormalizationWeight();
        NormalizationWeightExample example = new NormalizationWeightExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andValueEqualTo(nowValue).andNextValueEqualTo(plan);
        normalizationWeight = normalizationWeightMapper.selectByExample(example).get(0);
        return normalizationWeight;
    }

    @Override
    public NormalizationWeight selByTwoValue(String value, String nextValue) {
        NormalizationWeightExample example = new NormalizationWeightExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andValueEqualTo(value).andNextValueEqualTo(nextValue);
        return normalizationWeightMapper.selectByExample(example).get(0);
    }

    @Override
    public int insOrUpdByNW(NormalizationWeight normalizationWeight) {
        NormalizationWeight saved = this.selByTwoValue(normalizationWeight.getValue(), normalizationWeight.getNextValue());
        if (saved!=null){
            //如果保存过则更新
            return normalizationWeightMapper.updateByExample(normalizationWeight,new NormalizationWeightExample());
        }else {
            //如果未保存则插入
            return normalizationWeightMapper.insert(normalizationWeight);
        }
    }
}
