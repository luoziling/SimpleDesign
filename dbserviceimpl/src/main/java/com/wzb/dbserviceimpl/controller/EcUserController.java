package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.EcUserDBService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Satsuki
 * @time 2019/10/1 21:10
 * @description:
 */
public class EcUserController implements EcUserDBService {
    @Autowired
    private EcUserDBService ecUserDBService;
}
