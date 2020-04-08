//package com.wzb.dbserviceimpl.impl;
//
//import com.wzb.common.UserAndModel;
//import com.wzb.dbservice.RedisDBService;
//import com.wzb.sdredis.dao.JedisDao;
//import com.wzb.utils.JsonUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
///**
// * @author Satsuki
// * @time 2019/10/30 20:56
// * @description:
// */
//@Primary
////@ConfigurationProperties(locations = "classpath:redis.properties")
//@ConfigurationProperties(prefix = "redis")
//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
//@Repository
//public class RedisDBServiceImpl implements RedisDBService {
//    @Resource
//    private JedisDao jedisDaoImpl;
//
//    @Value("${redis.user_and_model}")
//    private String key;
//    @Override
//    public void setUserAndModel(UserAndModel userAndModel) {
//        String json = JsonUtils.objectToJson(userAndModel);
//        jedisDaoImpl.set(key,json);
//    }
//
//    @Override
//    public UserAndModel getUserAndModel() {
//        String json = jedisDaoImpl.get(key);
//        UserAndModel userAndModel = JsonUtils.jsonToPojo(json, UserAndModel.class);
//        return userAndModel;
//    }
//}
