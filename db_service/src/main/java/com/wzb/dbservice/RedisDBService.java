package com.wzb.dbservice;

import com.wzb.common.UserAndModel;

/**
 * @author Satsuki
 * @time 2019/10/30 20:49
 * @description:
 */
public interface RedisDBService {
    void setUserAndModel(UserAndModel userAndModel);
    UserAndModel getUserAndModel();
}
