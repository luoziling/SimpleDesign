package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.MatrixStorageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatrixStorageMapper {
    int countByExample(MatrixStorageExample example);

    int deleteByExample(MatrixStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MatrixStorage record);

    int insertSelective(MatrixStorage record);

    List<MatrixStorage> selectByExample(MatrixStorageExample example);

    MatrixStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MatrixStorage record, @Param("example") MatrixStorageExample example);

    int updateByExample(@Param("record") MatrixStorage record, @Param("example") MatrixStorageExample example);

    int updateByPrimaryKeySelective(MatrixStorage record);

    int updateByPrimaryKey(MatrixStorage record);
}