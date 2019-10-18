package com.wzb.businessservice.feignservice;

import com.wzb.pojo.DocInfo2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/10 19:25
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/doc_info2")
public interface DocInfo2DBservice {

    @RequestMapping(value = "/findCountDocInfo/{count}",method = RequestMethod.GET)
    List<DocInfo2> findCountDocInfo(@PathVariable("count") int count);

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public DocInfo2 findById(@PathVariable(value = "id") int id);
}
