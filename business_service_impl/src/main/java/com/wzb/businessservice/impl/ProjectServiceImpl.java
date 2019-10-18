package com.wzb.businessservice.impl;

import com.wzb.businessservice.ProjectService;
import com.wzb.businessservice.feignservice.AdjacentClosureDBService;
import com.wzb.businessservice.feignservice.MatrixStorageDBService;
import com.wzb.businessservice.feignservice.ProjectInformationDBService;
//import com.wzb.feignapi.ProjectInformationDBService;
import com.wzb.businessservice.feignservice.TreeNodeDBService;
import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/25 20:25
 * @description:
 */
@Primary
//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Service
public class ProjectServiceImpl implements ProjectService {

    //使用RESTful API调用
    private static final String REST_URL_PREFIX = "eureka_db_provider";

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    //使用feign
//    @Autowired(required = false)
    @Autowired
//    @Resource
    private ProjectInformationDBService piDBService;

    @Autowired
    private TreeNodeDBService tnDBService;

    @Autowired
    private AdjacentClosureDBService acDBService;

    @Autowired
    private MatrixStorageDBService msDBService;


    /**
     * 测试用
     * @return
     */
    @Override
    public List<ProjectInformation> getAll(){
        String req ="/project_information/get_all";

        //invokeUtil
//        ResponseEntity<List<ProjectInformation>> responseEntity = InvokeUtil.getAll(loadBalancerClient,req);
//        List<ProjectInformation> pList = responseEntity.getBody();

        //RESTful API
//        List<ProjectInformation> pList = restTemplate.getForObject(REST_URL_PREFIX+"",List.class);

        //feign
        List<ProjectInformation> pList = piDBService.selAll();
        System.out.println(pList);
        return pList;
    }


    /**
     * 创建项目（模型）
     *
     *
     * @param projectName 项目名
     */
    @Override
    public void projectCreation(String projectName) {
        piDBService.projectCreation(projectName);

        //测试事务是否开启
//        int i = 5/0;
//        // 创建时候就添加一个根节点 并保存
//        // 存入comment table（TreeNodeTable）与comment_path table(AdjacentClosureTable)
//        //首先存入ProjectInformation
//        System.out.println("projectCreation");
//        System.out.println("ProjectServiceImpl projectName:" + projectName);
//        piDBService.insInitial(projectName);
//
//        //其次存入TreeNodeContent
//        tnDBService.insInitial(projectName);
//
//        //保存闭包表
//        acDBService.insInitial(projectName);


    }

    @Override
    public List<TreeNodeContent> getNodes(String projectName) {
//        TreeNodeContent treeNodeContent = tnDBService.selById(9);
//        System.out.println("treeNodeContent:" + treeNodeContent);
        return tnDBService.selByPI(projectName);
    }

    @Override
    public int saveMS(MatrixStorage matrixStorage) {
        System.out.println("saveMS:" + matrixStorage.toString());
        return msDBService.insOrUpdByMS(matrixStorage);
    }
}
