package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.TreeNodeContent;
import com.wzb.pojo.TreeNodeContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeNodeContentMapper {
    int countByExample(TreeNodeContentExample example);

    int deleteByExample(TreeNodeContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TreeNodeContent record);

    int insertSelective(TreeNodeContent record);

    List<TreeNodeContent> selectByExample(TreeNodeContentExample example);

    TreeNodeContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TreeNodeContent record, @Param("example") TreeNodeContentExample example);

    int updateByExample(@Param("record") TreeNodeContent record, @Param("example") TreeNodeContentExample example);

    int updateByPrimaryKeySelective(TreeNodeContent record);

    int updateByPrimaryKey(TreeNodeContent record);
}