package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.MatrixStorageDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.MatrixStorageMapper;
import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.MatrixStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 19:38
 * @description:
 */
@Primary
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 36000,rollbackFor = Exception.class)
@Repository
public class MatrixStorageDBServiceImpl implements MatrixStorageDBService {

    @Autowired
    private MatrixStorageMapper matrixStorageMapper;

    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Override
    public List<MatrixStorage> selByNode(String nodeValue) {
        MatrixStorageExample example = new MatrixStorageExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andValueEqualTo(nodeValue);
        return matrixStorageMapper.selectByExample(example);
    }

    @Override
    public MatrixStorage selByNodeValue(int i, int j, String nodeValue, String username) {
        MatrixStorageExample example = new MatrixStorageExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andUsernameEqualTo(username).andIEqualTo(i).andJEqualTo(j).andValueEqualTo(nodeValue);
        List<MatrixStorage> list = matrixStorageMapper.selectByExample(example);
        System.out.println("existList" + list.toString());
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public MatrixStorage selByNodeValue1(int i, int j, String nodeValue, String projectName) {
        MatrixStorageExample example = new MatrixStorageExample();
        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
                .andProjectNameEqualTo(projectName).andIEqualTo(i).andJEqualTo(j).andValueEqualTo(nodeValue);
        List<MatrixStorage> list = matrixStorageMapper.selectByExample(example);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int insOrUpdByMS(MatrixStorage matrixStorage) {
        // 线程同步
        synchronized (this){
            System.out.println("insOrUpdByMS:" + matrixStorage.toString());
            //测试，没有username
//        MatrixStorage exist = this.selByNodeValue(matrixStorage.getI(), matrixStorage.getJ(), matrixStorage.getValue(), matrixStorage.getUsername());
//            MatrixStorage exist = this.selByNodeValue1(matrixStorage.getI(), matrixStorage.getJ(), matrixStorage.getValue(),matrixStorage.getProjectName());
            // 上面的方法找projectName是从数据库中找的所以出错!!
            MatrixStorage exist;
            MatrixStorageExample example1 = new MatrixStorageExample();
//            System.out.println("values: " + matrixStorage.get);
            example1.createCriteria().andProjectNameEqualTo(matrixStorage.getProjectName()).andIEqualTo(matrixStorage.getI()).andJEqualTo(matrixStorage.getJ()).andValueEqualTo(matrixStorage.getValue());
            List<MatrixStorage> list = matrixStorageMapper.selectByExample(example1);
            if (list.size()>0){
                System.out.println("找到了111111111111111111111111111111111" + list.toString());
                exist = list.get(0);
            }else {
                System.out.println("未找到111111111111111111111111111111111" + list.toString());
                exist = null;
            }

            if(null!=exist){
                System.out.println("exist:" + exist.toString());
            }

            MatrixStorageExample example = new MatrixStorageExample();
//        example.createCriteria().andIdEqualTo(exist.getId());
            if (exist!=null){
                //如果存在则更新
                matrixStorage.setId(exist.getId());
                return matrixStorageMapper.updateByPrimaryKey(matrixStorage);
//            return matrixStorageMapper.updateByExampleSelective(matrixStorage,example);
//            return matrixStorageMapper.updateByExample(matrixStorage,example);
            }else {
                //如果不存在则插入
                return matrixStorageMapper.insertSelective(matrixStorage);
            }
        }

    }

    @Override
    public String test11(){
        System.out.println("test11");
        return "MatrixStorageDBServiceImpl";
    }
}
