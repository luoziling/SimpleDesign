package com.wzb.dbserviceimpl.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzb.dbservice.DocInfo2DBService;
import com.wzb.dbserviceimpl.mapper.DocInfo2Mapper;
import com.wzb.pojo.DocInfo2;
import com.wzb.pojo.DocInfo2Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/10 19:09
 * @description:
 */
@Primary
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class DocInfo2DBServiceImpl implements DocInfo2DBService {

    @Autowired
    private DocInfo2Mapper docInfo2Mapper;

    @Override
    public List<DocInfo2> findCountDocInfo(int count) {
        PageHelper.startPage(1,count);
        List<DocInfo2> docList = docInfo2Mapper.selectByExample(new DocInfo2Example());
        PageInfo<DocInfo2> info =new PageInfo<>(docList);

        return info.getList();
    }

    @Override
    public DocInfo2 findById(int id) {
        return docInfo2Mapper.selectByPrimaryKey(id);
    }
}
