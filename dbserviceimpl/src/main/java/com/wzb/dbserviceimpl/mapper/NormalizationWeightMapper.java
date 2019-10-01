package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.NormalizationWeight;
import com.wzb.pojo.NormalizationWeightExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NormalizationWeightMapper {
    int countByExample(NormalizationWeightExample example);

    int deleteByExample(NormalizationWeightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NormalizationWeight record);

    int insertSelective(NormalizationWeight record);

    List<NormalizationWeight> selectByExample(NormalizationWeightExample example);

    NormalizationWeight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NormalizationWeight record, @Param("example") NormalizationWeightExample example);

    int updateByExample(@Param("record") NormalizationWeight record, @Param("example") NormalizationWeightExample example);

    int updateByPrimaryKeySelective(NormalizationWeight record);

    int updateByPrimaryKey(NormalizationWeight record);
}