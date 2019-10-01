package com.wzb.dbserviceimpl.controller;

import com.wzb.dbservice.MatrixStorageDBService;
import com.wzb.pojo.MatrixStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 21:10
 * @description:
 */
public class MatrixStorageController implements MatrixStorageDBService {

    @Autowired
    private MatrixStorageDBService matrixStorageDBService;

    @RequestMapping(value = "/selByNode",method = RequestMethod.GET)
    @Override
    public List<MatrixStorage> selByNode(String nodeValue) {
        return matrixStorageDBService.selByNode(nodeValue);
    }

    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    @Override
    public MatrixStorage selByNodeValue(int i, int j, String nodeValue, String username) {
        return matrixStorageDBService.selByNodeValue(i,j,nodeValue,username);
    }

    @RequestMapping(value = "/insOrUpdByMS",method = RequestMethod.POST)
    @Override
    public int insOrUpdByMS(@RequestBody MatrixStorage matrixStorage) {
        return matrixStorageDBService.insOrUpdByMS(matrixStorage);
    }
}
