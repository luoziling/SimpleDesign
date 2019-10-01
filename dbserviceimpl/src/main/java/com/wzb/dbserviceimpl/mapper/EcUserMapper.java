package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.EcUser;
import com.wzb.pojo.EcUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcUserMapper {
    int countByExample(EcUserExample example);

    int deleteByExample(EcUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EcUser record);

    int insertSelective(EcUser record);

    List<EcUser> selectByExample(EcUserExample example);

    EcUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EcUser record, @Param("example") EcUserExample example);

    int updateByExample(@Param("record") EcUser record, @Param("example") EcUserExample example);

    int updateByPrimaryKeySelective(EcUser record);

    int updateByPrimaryKey(EcUser record);
}