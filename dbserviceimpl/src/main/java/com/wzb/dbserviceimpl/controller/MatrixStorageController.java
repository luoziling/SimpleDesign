package com.wzb.dbserviceimpl.controller;

import com.wzb.common.CommonResult;
import com.wzb.common.MatrixResult;
import com.wzb.common.NWResult;
import com.wzb.dbservice.MatrixStorageDBService;
import com.wzb.pojo.MatrixStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 21:10
 * @description:
 */
@RestController
@RequestMapping("/matrix_storage")
public class MatrixStorageController implements MatrixStorageDBService {

    @Autowired
//    @Resource
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

    @RequestMapping(value = "/selByNodeValue1",method = RequestMethod.GET)
    @Override
    public MatrixStorage selByNodeValue1(int i, int j, String nodeValue, String projectName) {
        return matrixStorageDBService.selByNodeValue1(i,j,nodeValue,projectName);
    }

    @RequestMapping(value = "/insOrUpdByMS",method = RequestMethod.POST)
    @Override
    public int insOrUpdByMS(@RequestBody MatrixStorage matrixStorage) {
        System.out.println(matrixStorage.toString());
        return matrixStorageDBService.insOrUpdByMS(matrixStorage);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String test11(@RequestBody String json){
        System.out.println(json);
        return "MatrixStorageController";
    }

    @RequestMapping(value = "/test11", method = RequestMethod.GET)
    @Override
    public String test11() {
        System.out.println("test11");
        return matrixStorageDBService.test11();
    }

    @RequestMapping(value = "/selByIV", method = RequestMethod.POST)
    @Override
    public MatrixResult selByIV(@RequestBody MatrixStorage matrixStorage) {
        return matrixStorageDBService.selByIV(matrixStorage);
    }

    @RequestMapping(value = "/insOrUpdByNW", method = RequestMethod.POST)
    @Override
    public CommonResult insOrUpdByNW(@RequestBody NWResult nwResult) {
        return matrixStorageDBService.insOrUpdByNW(nwResult);
    }
}
