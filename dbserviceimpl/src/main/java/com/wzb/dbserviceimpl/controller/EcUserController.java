package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.EcUserDBService;
import com.wzb.pojo.EcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Satsuki
 * @time 2019/10/1 21:10
 * @description:
 */
@RestController
@RequestMapping("/ec_user")
public class EcUserController implements EcUserDBService {
    @Autowired
    private EcUserDBService ecUserDBService;

    @RequestMapping(value = "/selByUsername",method = RequestMethod.POST)
    @Override
    public EcUser selByUsername(@RequestBody String username) {
        System.out.println("username = [" + username + "]");
        return ecUserDBService.selByUsername(username);
    }

    @RequestMapping(value = "/insByUser",method = RequestMethod.POST)
    @Override
    public int insByUser(@RequestBody EcUser user) {
        return ecUserDBService.insByUser(user);
    }

    @RequestMapping(value = "/updByUser", method = RequestMethod.POST)
    @Override
    public int updByUser(@RequestBody EcUser user) {
        return ecUserDBService.updByUser(user);
    }
}
