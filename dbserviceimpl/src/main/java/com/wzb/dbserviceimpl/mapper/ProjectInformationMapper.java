package com.wzb.dbserviceimpl.mapper;

import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.ProjectInformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectInformationMapper {
    int countByExample(ProjectInformationExample example);

    int deleteByExample(ProjectInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInformation record);

    int insertSelective(ProjectInformation record);

    List<ProjectInformation> selectByExample(ProjectInformationExample example);

    ProjectInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectInformation record, @Param("example") ProjectInformationExample example);

    int updateByExample(@Param("record") ProjectInformation record, @Param("example") ProjectInformationExample example);

    int updateByPrimaryKeySelective(ProjectInformation record);

    int updateByPrimaryKey(ProjectInformation record);
}