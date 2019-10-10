package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.DocInfo2;
import com.wzb.pojo.DocInfo2Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocInfo2Mapper {
    int countByExample(DocInfo2Example example);

    int deleteByExample(DocInfo2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocInfo2 record);

    int insertSelective(DocInfo2 record);

    List<DocInfo2> selectByExample(DocInfo2Example example);

    DocInfo2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocInfo2 record, @Param("example") DocInfo2Example example);

    int updateByExample(@Param("record") DocInfo2 record, @Param("example") DocInfo2Example example);

    int updateByPrimaryKeySelective(DocInfo2 record);

    int updateByPrimaryKey(DocInfo2 record);
}