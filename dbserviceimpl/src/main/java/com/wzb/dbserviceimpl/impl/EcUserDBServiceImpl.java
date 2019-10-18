package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.EcUserDBService;
import com.wzb.dbserviceimpl.mapper.EcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Satsuki
 * @time 2019/10/1 19:39
 * @description:
 */
@Primary
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class EcUserDBServiceImpl implements EcUserDBService {
    @Autowired
    private EcUserMapper ecUserMapper;

}
