package com.wzb.userserviceimpl.impl;

import com.wzb.common.UserResult;
import com.wzb.pojo.EcUser;
import com.wzb.userservice.UserService;
import com.wzb.userserviceimpl.feighservice.EcUserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Satsuki
 * @time 2020/4/8 18:41
 * @description:
 */
@Primary
@Service
public class UserServiceImpl implements UserService {
    // feign接口
    @Autowired
    private EcUserDBService ecUserDBService;

    @Override
    public UserResult loginVerification(EcUser user) {
        // 根据name查询用户
        EcUser realUser = ecUserDBService.selByUsername(user.getUsername());
        System.out.println("user = [" + user + "]");
        System.out.println("realUser = [" + realUser + "]");
        // 用于封装返回信息
        UserResult userResult = new UserResult();
        // 判断用户名和密码是否正确
        if (realUser == null){
            userResult.setFlag(false)
                    .setUser(realUser)
                    .setReviews("未找到该用户，请检查用户名是否正确");
            return userResult;
        }
        // 用户名正确
        if (user.getUsername().equals(realUser.getUsername())){
            if (user.getPassword().equals(realUser.getPassword())){
                // 密码正确
                userResult.setFlag(true)
                        .setUser(realUser)
                        .setReviews("验证正确，登陆成功");
                return userResult;

            }else {
                // 用户名正确密码不正确
                userResult.setFlag(false)
                        .setUser(realUser)
                        .setReviews("验证失败，密码错误");
                return userResult;
            }
        }
        return null;
    }
}
