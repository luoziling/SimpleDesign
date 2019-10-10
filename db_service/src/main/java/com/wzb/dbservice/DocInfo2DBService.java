package com.wzb.dbservice;

import com.wzb.pojo.DocInfo2;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/10 19:00
 * @description:
 */
public interface DocInfo2DBService {
    /**
     * 查询N位医生信息
     * @param count
     * @return
     */
    List<DocInfo2> findCountDocInfo(int count);
}
