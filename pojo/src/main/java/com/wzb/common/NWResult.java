package com.wzb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

/**
 * @author Satsuki
 * @time 2019/10/1 20:26
 * @description:
 */
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
@Data               //提供类所有属性的get和set
@Accessors(chain = true) //链式访问setter方法返回this
public class NWResult {
    Double[][] data;
    Double[] w;
    Double CR;

    @Override
    public String toString() {
        return "NWResult{" +
                "data=" + Arrays.toString(data) +
                ", w=" + Arrays.toString(w) +
                ", CR=" + CR +
                '}';
    }
}
