package com.wzb.dbserviceimpl;

//import com.wzb.dbserviceimpl.impl.RedisDBServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.wzb.dbserviceimpl.mapper")
@EnableEurekaClient
//@EnableConfigurationProperties(RedisDBServiceImpl.class)
//@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker //熔断机制，类似AOP的异常处理
public class   DbserviceimplApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbserviceimplApplication.class, args);
    }

}
