package com.wzb.businessservice;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;




//@ComponentScans({@ComponentScan("com.wzb.dbservice"),@ComponentScan("com.wzb.businessservice")})
//@ComponentScan("com.wzb.dbservice")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

//@EnableFeignClients(basePackages = {"com.wzb.dbservice"})
//@EnableFeignClients(basePackages = {"com.wzb.feignapi"})
//@ComponentScan({"com.wzb.feignapi","com.wzb.businessservice"})
//@ComponentScan({"com.wzb.dbservice","com.wzb.businessservice"})
//@ComponentScan("com.wzb.dbservice")
public class BusinessServiceImplApplication {

    @Bean
    @LoadBalanced //Spring Cloud Ribbon是基于Netflix Ribbon实现的一套==**客户端**==负载均衡工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * Ribbon的调用算法默认是round轮询，还可以有随机等多种算法
     * @return
     */
    @Bean
    public IRule myRule(){
//        return new RoundRobinRule(); //轮询算法
//        return new RandomRule(); //随机算法
        return new RetryRule(); //重试算法，若未出现问题就是轮询算法，若某个服务宕机多次尝试访问失败后则会跳过访问改服务
    }

    public static void main(String[] args) {
        SpringApplication.run(BusinessServiceImplApplication.class, args);
    }

}
