package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.DocInfo1;
import com.wzb.pojo.DocInfo1Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocInfo1Mapper {
    int countByExample(DocInfo1Example example);

    int deleteByExample(DocInfo1Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocInfo1 record);

    int insertSelective(DocInfo1 record);

    List<DocInfo1> selectByExample(DocInfo1Example example);

    DocInfo1 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocInfo1 record, @Param("example") DocInfo1Example example);

    int updateByExample(@Param("record") DocInfo1 record, @Param("example") DocInfo1Example example);

    int updateByPrimaryKeySelective(DocInfo1 record);

    int updateByPrimaryKey(DocInfo1 record);
}