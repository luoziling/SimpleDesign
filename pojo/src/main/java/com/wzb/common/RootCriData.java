package com.wzb.common;

import com.wzb.pojo.TreeNodeContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/16 14:54
 * @description:
 */
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
@Data               //提供类所有属性的get和set
@Accessors(chain = true) //链式访问setter方法返回this
public class RootCriData {
    private Double[][] data;
    private String projectName;
    private List<String> nextList;

}
