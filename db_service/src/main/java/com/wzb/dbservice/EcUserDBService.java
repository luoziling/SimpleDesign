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

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insByUser(EcUser user);

    /**
     *  更新用户信息
     * @param user
     * @return
     */
    int updByUser(EcUser user);


}
