package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.DocInfo2DBService;
import com.wzb.pojo.DocInfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/10 19:15
 * @description:
 */
@RestController
@RequestMapping("/doc_info2")
public class DocInfo2Controller implements DocInfo2DBService {

    @Autowired
    private DocInfo2DBService docInfo2DBService;


    @RequestMapping("/Test1")
    public String Test1(){
        return "DocInfo2Controller";
    }

    @RequestMapping(value = "/findCountDocInfo/{count}",method = RequestMethod.GET)
    @Override
    public List<DocInfo2> findCountDocInfo(@PathVariable("count") int count) {
        return docInfo2DBService.findCountDocInfo(count);
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    @Override
    public DocInfo2 findById(@PathVariable(value = "id") int id) {
        return docInfo2DBService.findById(id);
    }
}
