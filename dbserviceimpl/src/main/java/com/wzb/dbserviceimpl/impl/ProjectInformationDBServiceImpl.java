package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.AdjacentClosureDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbservice.TreeNodeDBService;
import com.wzb.dbserviceimpl.mapper.ProjectInformationMapper;
import com.wzb.pojo.ProjectInformation;
import com.wzb.pojo.ProjectInformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/9/22 16:09
 * @description:
 *
 * propagation 控制事务传播行为.
 * REQUIRED (默认值): 如果当前有事务,就在事务中执行,如果当 前没有事务,新建一个事务
 * isolation=”” 事务隔离级别
 * 在多线程或并发访问下如何保证访问到的数据具有完整性
 * 脏读：
 * 一个事务A读取到另一个事务(B)中未提交的数据，另一个事务中的数据可能进行了改变，此时A事务
 * 读取的数据可能和数据库中的数据不一致，此时认为是读脏数据，读脏数据的过程叫做脏读
 * 不可重复读：
 * 主要针对的是某行数据，主要针对的操作是修改两次读取在同一个事务内
 * 当事务A第一次读取事务后，事务B对事务A读取的数据进行修改，事务A中再次读取的数据
 * 与之前读取的数据不一致这个过程称为不可重复读
 * 幻读：
 * 主要针对是新增或删除，两次事务的结果
 * 事务A按照特定条件查询出结果，事务B新增了一条符合条件的数据，事务A中
 * 查询的数据和数据库中的数据不一致事务A好像产生了幻觉称为幻读
 * DEFAULT:默认值，由底层数据库自动判断应该使用什么隔离级别
 *
 */
@Primary
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class ProjectInformationDBServiceImpl implements ProjectInformationDBService {


    @Autowired
    private ProjectInformationMapper projectInformationMapper;

    @Lazy
    @Autowired
    private TreeNodeDBService treeNodeDBService;

    @Autowired
    private AdjacentClosureDBService adjacentClosureDBService;

    @Override
    public List<ProjectInformation> selAll() {
        System.out.println("selAll");
        List<ProjectInformation> projectInformationList = null;
        ProjectInformationExample example = new ProjectInformationExample();
//        example.createCriteria().andProjectNameEqualTo("测试project3");
        example.createCriteria().andProjectNameEqualTo("测试project3");
        projectInformationList = projectInformationMapper.selectByExample(example);
        System.out.println(projectInformationList.toString());
        return projectInformationList;
    }

    /**
     * 1、selective的意思是：选择性
     * 2、insertSelective--选择性保存数据；
     * 比如User里面有三个字段:id，name，age，password
     * 但是我只设置了一个字段；
     * User u=new user();
     * u.setName（"张三"）；
     * insertSelective（u）；
     * 3、insertSelective执行对应的sql语句的时候，只插入对应的name字段；（主键是自动添加的，默认插入为空）
     * insert into tb_user （id，name） value （null，"张三"）；
     * 4、而insert则是不论你设置多少个字段，统一都要添加一遍，不论你设置几个字段，即使是一个。
     * User u=new user();
     * u.setName（"张三"）；
     * insertSelective（u）；
     * insert into tb_user （id，name，age，password） value （null，"张三"，null，null）；
     */
    @Override
    public void insTest() {
        ProjectInformation pi = new ProjectInformation();
        pi.setProjectName("insTest2");
        pi.setLayer(2);
        projectInformationMapper.insert(pi);
//        int a = 5/0;
//        try {
//            int a = 5/0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public ProjectInformation selById(Integer id) {
        return projectInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insInitial(String projectName) {
        System.out.println("ProjectInformationDBServiceImpl projectName:" + projectName);
        ProjectInformation initialPI = new ProjectInformation();
        initialPI.setProjectName(projectName);
        //初始化层数为1后期根据具体插入的数据进行修改（边插入新数据边修改
        initialPI.setLayer(1);
        projectInformationMapper.insert(initialPI);
    }

    @Override
    public void projectCreation(String projectName) {

        // 创建时候就添加一个根节点 并保存
        // 存入comment table（TreeNodeTable）与comment_path table(AdjacentClosureTable)
        //首先存入ProjectInformation
        this.insInitial(projectName);

        //其次存入TreeNodeContent
        treeNodeDBService.insInitial(projectName);

        //保存闭包表
        adjacentClosureDBService.insInitial(projectName);
    }

    @Override
    public ProjectInformation selNowModel() {
        //将ID为n的数据项定位保存当前项目信息的数据项
        return projectInformationMapper.selectByPrimaryKey(10004);
    }
}
