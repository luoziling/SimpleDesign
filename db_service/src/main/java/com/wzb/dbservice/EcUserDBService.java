package com.wzb.dbservice;

import com.wzb.pojo.EcUser;

/**
 * @author Satsuki
 * @time 2019/10/1 19:38
 * @description:
 */
public interface EcUserDBService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    EcUser selByUsername(String username);
}
