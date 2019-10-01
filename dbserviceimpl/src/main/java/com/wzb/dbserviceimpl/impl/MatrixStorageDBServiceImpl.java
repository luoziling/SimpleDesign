package com.wzb.dbserviceimpl.impl;

import com.wzb.dbservice.MatrixStorageDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.MatrixStorageMapper;
import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.MatrixStorageExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 19:38
 * @description:
 */
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
        return matrixStorageMapper.selectByExample(example).get(0);
    }

    @Override
    public int insOrUpdByMS(MatrixStorage matrixStorage) {
        MatrixStorage exist = this.selByNodeValue(matrixStorage.getI(), matrixStorage.getJ(), matrixStorage.getValue(), matrixStorage.getUsername());
        MatrixStorageExample example = new MatrixStorageExample();
        if (exist!=null){
            //如果存在则更新
            return matrixStorageMapper.updateByExample(matrixStorage,example);
        }else {
            //如果不存在则插入
            return matrixStorageMapper.insertSelective(matrixStorage);
        }
    }
}
