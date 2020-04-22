package com.wzb.userservice;


import com.wzb.common.CommonResult;
import com.wzb.common.UserResult;
import com.wzb.pojo.EcUser;

/**
 * @author Satsuki
 * @time 2020/4/8 17:42
 * @description:
 */
public interface UserService {
    /**
     * 登陆验证
     * @param user
     * @return
     */
    UserResult loginVerification(EcUser user);

    /**
     *  用户注册
     * @param user
     * @return
     */
    CommonResult userRegistration(EcUser user);

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    CommonResult editUserInfo(EcUser user);
}
