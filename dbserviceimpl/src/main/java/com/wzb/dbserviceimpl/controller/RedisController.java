//package com.wzb.dbserviceimpl.controller;
//
//import com.wzb.common.UserAndModel;
//import com.wzb.dbservice.RedisDBService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author Satsuki
// * @time 2019/10/30 21:25
// * @description:
// */
//@RestController
//@RequestMapping("redis")
//public class RedisController implements RedisDBService {
//
//    @Autowired
//    private RedisDBService redisDBService;
//
//    @RequestMapping(value = "/setUserAndModel", method = RequestMethod.POST)
//    @Override
//    public void setUserAndModel(UserAndModel userAndModel) {
//        redisDBService.setUserAndModel(userAndModel);
//    }
//
//    @RequestMapping(value = "/getUserAndModel",method = RequestMethod.GET)
//    @Override
//    public UserAndModel getUserAndModel() {
//        return redisDBService.getUserAndModel();
//    }
//}
