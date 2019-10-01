package com.wzb.businessservice.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Satsuki
 * @time 2019/9/25 21:11
 * @description:
 * 调用服务的工具类
 */
public class InvokeUtil {

    /**
     * 测试
     * @param loadBalancerClient
     * @param req
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> getAll(LoadBalancerClient loadBalancerClient,String req){
        //选择调用的服务名称
        ServiceInstance si = loadBalancerClient.choose("eureka_db_provider");
        //拼接访问服务的url
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://").append(si.getHost()).append(":").append(si.getPort()).append(req);
        System.out.println("myPort:" + si.getPort());
        //SpringMVC restTemplate
        RestTemplate rt = new RestTemplate();

        ParameterizedTypeReference<T> typeReference = new ParameterizedTypeReference<T>() {};

        //封装返回信息
        ResponseEntity<T> responseEntity = rt.exchange(stringBuffer.toString(), HttpMethod.GET,null,typeReference);


        return responseEntity;
    }

//    public static void
}
