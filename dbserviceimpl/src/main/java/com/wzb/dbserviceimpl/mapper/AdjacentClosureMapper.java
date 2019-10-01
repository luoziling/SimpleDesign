package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.AdjacentClosureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdjacentClosureMapper {
    int countByExample(AdjacentClosureExample example);

    int deleteByExample(AdjacentClosureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdjacentClosure record);

    int insertSelective(AdjacentClosure record);

    List<AdjacentClosure> selectByExample(AdjacentClosureExample example);

    AdjacentClosure selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdjacentClosure record, @Param("example") AdjacentClosureExample example);

    int updateByExample(@Param("record") AdjacentClosure record, @Param("example") AdjacentClosureExample example);

    int updateByPrimaryKeySelective(AdjacentClosure record);

    int updateByPrimaryKey(AdjacentClosure record);
}