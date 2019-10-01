package com.wzb.businessservice.feignservice;

import com.wzb.pojo.MatrixStorage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 21:46
 * @description:
 */
public interface MatrixStorageDBService {
    @RequestMapping(value = "/selByNode",method = RequestMethod.GET)
    public List<MatrixStorage> selByNode(String nodeValue);

    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    public MatrixStorage selByNodeValue(int i, int j, String nodeValue, String username);

    @RequestMapping(value = "/insOrUpdByMS",method = RequestMethod.POST)
    public int insOrUpdByMS(@RequestBody MatrixStorage matrixStorage);
}
