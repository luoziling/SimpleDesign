package com.wzb.userservice;

import com.wzb.common.UserResult;
import com.wzb.pojo.EcUser;

/**
 * @author Satsuki
 * @time 2020/4/8 17:42
 * @description:
 */
public interface UserService {
    UserResult loginVerification(EcUser user);
}
