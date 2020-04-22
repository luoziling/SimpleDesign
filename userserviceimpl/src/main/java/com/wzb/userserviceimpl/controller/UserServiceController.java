package com.wzb.userserviceimpl.controller;

import com.wzb.common.CommonResult;
import com.wzb.common.UserResult;
import com.wzb.pojo.EcUser;
import com.wzb.userservice.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Satsuki
 * @time 2020/4/8 18:41
 * @description:
 */
@RestController
@RequestMapping("user_service")
public class UserServiceController implements UserService {
    @Resource
    private UserService userService;
    @RequestMapping("/loginVerification")
    @Override
    public UserResult loginVerification(@RequestBody EcUser user) {
        return userService.loginVerification(user);
    }

    @RequestMapping(value = "/userRegistration",method = RequestMethod.POST)
    @Override
    public CommonResult userRegistration(@RequestBody EcUser user) {
        return userService.userRegistration(user);
    }

    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    @Override
    public CommonResult editUserInfo(@RequestBody EcUser user) {
        return userService.editUserInfo(user);
    }
}
