package com.wzb.businessservice.feignservice;

import com.wzb.common.CommonResult;
import com.wzb.common.NWResult;
import com.wzb.pojo.MatrixStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 21:46
 * @description:
 */
@FeignClient(value = "EUREKA-DB-PROVIDER")
@RequestMapping("/matrix_storage")
public interface MatrixStorageDBService {
    @RequestMapping(value = "/selByNode",method = RequestMethod.GET)
    public List<MatrixStorage> selByNode(String nodeValue);

    @RequestMapping(value = "/selByNodeValue",method = RequestMethod.GET)
    public MatrixStorage selByNodeValue(@RequestParam("i") int i,@RequestParam("j") int j,@RequestParam("nodeValue") String nodeValue,@RequestParam("username") String username);

    @RequestMapping(value = "/insOrUpdByMS",method = RequestMethod.POST)
    public int insOrUpdByMS(@RequestBody MatrixStorage matrixStorage);

    @RequestMapping(value = "/insOrUpdByNW", method = RequestMethod.POST)
    public CommonResult insOrUpdByNW(@RequestBody NWResult nwResult);
}
