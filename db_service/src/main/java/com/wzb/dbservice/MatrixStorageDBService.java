package com.wzb.dbservice;

import com.wzb.common.CommonResult;
import com.wzb.common.MatrixResult;
import com.wzb.common.NWResult;
import com.wzb.pojo.MatrixStorage;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 19:38
 * @description:
 */
public interface MatrixStorageDBService {
    /**
     * 查询当前表格是否已经保存过
     * 根据点击的节点值查找在数据库中是否有记录
     * 如果存储过则要加载数据库中的数据
     * @param nodeValue
     * @return
     */
    List<MatrixStorage> selByNode(String nodeValue);

    /**
     * 要使用insertOrReplace
     * 根据其他的所有属性查询有没有相同的ID
     * @param i
     * @param j
     * @param nodeValue
     * @param username
     * @return
     */
    MatrixStorage selByNodeValue(int i,int j,String nodeValue,String username);

    /**
     * 要使用insertOrReplace
     * 根据其他的所有属性查询有没有相同的ID
     * @param i
     * @param j
     * @param nodeValue
     * @param projectName
     * @return
     */
    MatrixStorage selByNodeValue1(int i,int j,String nodeValue,String projectName);

    /**
     * 插入或更新一条记录
     * @param matrixStorage
     * @return
     */
    int insOrUpdByMS(MatrixStorage matrixStorage);

    /**
     * 测试用
     * @return
     */
    String test11();

    /**
     * 根据ID与点击的节点的内容查询判断矩阵数据
     * @param matrixStorage
     * @return
     */
    MatrixResult selByIV(MatrixStorage matrixStorage);

    /**
     * 插入或更新整个矩阵
     * @param nwResult
     * @return
     */
    CommonResult insOrUpdByNW(NWResult nwResult);
}
