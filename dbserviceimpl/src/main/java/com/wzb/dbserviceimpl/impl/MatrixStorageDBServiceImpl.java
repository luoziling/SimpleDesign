package com.wzb.dbserviceimpl.impl;

import com.wzb.common.CommonResult;
import com.wzb.common.MatrixResult;
import com.wzb.common.NWResult;
import com.wzb.dbservice.MatrixStorageDBService;
import com.wzb.dbservice.ProjectInformationDBService;
import com.wzb.dbserviceimpl.mapper.MatrixStorageMapper;
import com.wzb.dbserviceimpl.mapper.NormalizationWeightMapper;
import com.wzb.pojo.MatrixStorage;
import com.wzb.pojo.MatrixStorageExample;
import com.wzb.pojo.NormalizationWeight;
import com.wzb.pojo.NormalizationWeightExample;
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
    private NormalizationWeightMapper normalizationWeightMapper;

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
//        example.createCriteria().andProjectNameEqualTo(projectInformationDBService.selNowModel().getProjectName())
//                .andUsernameEqualTo(username).andIEqualTo(i).andJEqualTo(j).andValueEqualTo(nodeValue);
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

    @Override
    public MatrixResult selByIV(MatrixStorage matrixStorage) {
        MatrixResult result = new MatrixResult();
        try{
            // 根据模型ID与点击的节点内容查询判断矩阵
            MatrixStorageExample mExample = new MatrixStorageExample();
            mExample.createCriteria().andProjectIdEqualTo(matrixStorage.getProjectId())
                    .andValueEqualTo(matrixStorage.getValue());
            result.setMatrixStorages(matrixStorageMapper.selectByExample(mExample));

        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }
        result.setFlag(true);
        result.setReviews("获取矩阵信息成功");
        return result;
    }

    @Override
    public CommonResult insOrUpdByNW(NWResult nwResult) {
        System.out.println("nwResult = [" + nwResult + "]");
        CommonResult result = new CommonResult();
        try{
            // 保存矩阵数据与权重数据
            Double[][] data = nwResult.getData();
            MatrixStorage matrixStorage = nwResult.getMatrixStorage();
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    matrixStorage.setI(i);
                    matrixStorage.setJ(j);
                    matrixStorage.setMatrixValue(data[i][j]);
                    // 查找是否已有记录，有则更新，没有则插入
                    // 矩阵记录未找到，尝试查看查询错误的地方
                    MatrixStorageExample mExample = new MatrixStorageExample();
                    mExample.createCriteria().andProjectIdEqualTo(matrixStorage.getProjectId())
                            .andIEqualTo(i)
                            .andJEqualTo(j)
                            .andValueEqualTo(matrixStorage.getValue());
                    List<MatrixStorage> matrixStorages = matrixStorageMapper.selectByExample(mExample);
                    System.out.println("size:" + matrixStorages.size());
                    if (matrixStorages.size()>0){
                        // 有数据
                        // 更新
                        matrixStorage.setId(matrixStorages.get(0).getId());
                        matrixStorageMapper.updateByPrimaryKey(matrixStorage);
                        // 清空ID
                        matrixStorage.setId(null);
                    }else {
                        // 未找到
                        // 插入
                        matrixStorageMapper.insert(matrixStorage);
                    }
//                    if (matrixStorage == null){
//                        // 未找到
//                        // 插入
//                        matrixStorageMapper.insert(matrixStorage);
//                    }else {
//                        // 更新
//                        matrixStorage.setId(matrixStorage1.getId());
//                        matrixStorageMapper.updateByPrimaryKey(matrixStorage);
//                        // 清空ID
//                        matrixStorage.setId(null);
//                    }


                }
            }

            // 归一化权重的保存
            Double[] w = nwResult.getW();
            List<String> nextList = nwResult.getNextList();
            NormalizationWeight normalizationWeight = new NormalizationWeight();
            normalizationWeight.setProjectId(matrixStorage.getProjectId());
            normalizationWeight.setProjectName(matrixStorage.getProjectName());
            normalizationWeight.setValue(matrixStorage.getValue());
            normalizationWeight.setUserId(matrixStorage.getUserId());
            for (int i = 0; i < nextList.size(); i++) {
                normalizationWeight.setWeight(w[i]);
                normalizationWeight.setNextValue(nextList.get(i));
                // 存储
                NormalizationWeightExample nExample = new NormalizationWeightExample();
                nExample.createCriteria().andProjectIdEqualTo(normalizationWeight.getProjectId())
                        .andUserIdEqualTo(normalizationWeight.getUserId())
                        .andValueEqualTo(normalizationWeight.getValue())
                        .andNextValueEqualTo(normalizationWeight.getNextValue());
                List<NormalizationWeight> normalizationWeights = normalizationWeightMapper.selectByExample(nExample);
                if (normalizationWeights.size()>0){
                    // 找到
                    // 更新
                    normalizationWeight.setId(normalizationWeights.get(0).getId());
                    normalizationWeightMapper.updateByPrimaryKey(normalizationWeight);
                    // 清空ID
                    normalizationWeight.setId(null);
                }else {
                    // 未找到
                    // 插入
                    normalizationWeightMapper.insert(normalizationWeight);
                }


            }


        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setReviews("网络出错请重试");
            return result;
        }
        result.setFlag(true);
        result.setReviews("保存矩阵信息成功");
        return result;
    }
}
