package com.wzb.common;



import com.wzb.pojo.MatrixStorage;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 2020/4/14
 * Author:Satsuki
 * Description:
 * 封装矩阵保存的数据（传递给后端
 */
public class MatrixWrapper {
	// 二维矩阵数据
	Double[][] data;
	// 两个ID确认比较通用多种情况
//	Integer projectID;
//	Integer userID;

	// 数据封装
	MatrixStorage matrixStorage;
	// 多加一条数据 保存对应这一层准则的权重值
	List<String> nextList;

	public MatrixWrapper() {
	}

	public MatrixWrapper(Double[][] data, MatrixStorage matrixStorage, List<String> nextList) {
		this.data = data;
		this.matrixStorage = matrixStorage;
		this.nextList = nextList;
	}

	public List<String> getNextList() {
		return nextList;
	}

	public void setNextList(List<String> nextList) {
		this.nextList = nextList;
	}

	public Double[][] getData() {
		return data;
	}

	public void setData(Double[][] data) {
		this.data = data;
	}

	public MatrixStorage getMatrixStorage() {
		return matrixStorage;
	}

	public void setMatrixStorage(MatrixStorage matrixStorage) {
		this.matrixStorage = matrixStorage;
	}

	@Override
	public String toString() {
		return "MatrixWrapper{" +
				"data=" + Arrays.toString(data) +
				", matrixStorage=" + matrixStorage +
				", nextList=" + nextList +
				'}';
	}
}
