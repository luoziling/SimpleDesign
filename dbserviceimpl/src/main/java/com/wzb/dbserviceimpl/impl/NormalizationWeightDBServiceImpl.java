package com.wzb.dbserviceimpl.impl;

import com.wzb.common.NorWrapper;
import com.wzb.dbservice.NormalizationWeightDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.NormalizationWeightMapper;
import com.wzb.pojo.NormalizationWeight;
import com.wzb.pojo.NormalizationWeightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/24 22:08
 * @description:
 */
@Primary
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
        String projectName = projectInformationDBService.selNowModel().getProjectName();
        System.out.println("projectName:" + projectName);
        System.out.println("nowValue:" + nowValue);
        //todo:错误发现这边要求nextValue传递过来的却找不到
        example.createCriteria().andProjectNameEqualTo(projectName)
                .andNextValueEqualTo(nowValue);

//        example.createCriteria().andProjectNameEqualTo(projectName).andNextValueEqualTo()

        List<NormalizationWeight> normalizationWeights = normalizationWeightMapper.selectByExample(example);

        System.out.println("normalizationWeights:"+normalizationWeights.toString());
        if (normalizationWeights.size()>0){
            normalizationWeight = normalizationWeights.get(0);
        }else {
            normalizationWeight = null;
        }
        return normalizationWeight;
    }

    @Override
    public NormalizationWeight selByPI(NorWrapper norWrapper) {
        NormalizationWeightExample example = new NormalizationWeightExample();
        example.createCriteria().andProjectIdEqualTo(norWrapper.getConcalWrapper().getProjectID())
                .andUserIdEqualTo(norWrapper.getConcalWrapper().getUserID())
                .andNextValueEqualTo(norWrapper.getNextValue());
        return normalizationWeightMapper.selectByExample(example).get(0);
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
        List<NormalizationWeight> list = normalizationWeightMapper.selectByExample(example);
        if(list.size()>0){
            System.out.println("找到了selByTwoValue！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
            return list.get(0);
        }else {
            System.out.println("未找到selByTwoValue！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
            return null;
        }
//        return normalizationWeightMapper.selectByExample(example).get(0);
    }

    @Override
    public NormalizationWeight selByTwoValues(NorWrapper norWrapper) {
        System.out.println("norWrapper = " + norWrapper);
        NormalizationWeightExample example = new NormalizationWeightExample();
        example.createCriteria().andProjectIdEqualTo(norWrapper.getConcalWrapper().getProjectID())
                .andUserIdEqualTo(norWrapper.getConcalWrapper().getUserID())
                .andValueEqualTo(norWrapper.getNowValue())
                .andNextValueEqualTo(norWrapper.getNextValue());
        return normalizationWeightMapper.selectByExample(example).get(0);
    }

    @Override
    public int insOrUpdByNW(NormalizationWeight normalizationWeight) {

        synchronized (this){
            NormalizationWeight saved = this.selByTwoValue(normalizationWeight.getValue(), normalizationWeight.getNextValue());

            if (saved!=null){
                System.out.println("update!!!!!!!!!!!!!!!!!!!!!!!!" + saved.toString());
                //弄反了
//                saved.setId(normalizationWeight.getId());
//                //如果保存过则更新
//                return normalizationWeightMapper.updateByPrimaryKey(saved);
                // 获取已保存的数据ID
                normalizationWeight.setId(saved.getId());
                //如果保存过则更新
                // 保存新数据进行更新
                return normalizationWeightMapper.updateByPrimaryKey(normalizationWeight);
//            return normalizationWeightMapper.updateByExample(normalizationWeight,new NormalizationWeightExample());

            }else {
                System.out.println("null"+ "insert!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //如果未保存则插入
                return normalizationWeightMapper.insert(normalizationWeight);
            }
        }

    }
}
