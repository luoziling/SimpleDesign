package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.EcUserDBService;
import com.wzb.dbserviceimpl.mapper.EcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Satsuki
 * @time 2019/10/1 19:39
 * @description:
 */
public class EcUserDBServiceImpl implements EcUserDBService {
    @Autowired
    private EcUserMapper ecUserMapper;

}
